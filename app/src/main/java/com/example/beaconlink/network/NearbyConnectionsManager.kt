package com.example.beaconlink.network

import android.content.Context
import android.os.Build
import android.os.ParcelFileDescriptor
import android.util.Log
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.AdvertisingOptions
import com.google.android.gms.nearby.connection.ConnectionInfo
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.ConnectionResolution
import com.google.android.gms.nearby.connection.ConnectionsClient
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo
import com.google.android.gms.nearby.connection.DiscoveryOptions
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import com.google.android.gms.nearby.connection.Payload
import com.google.android.gms.nearby.connection.PayloadCallback
import com.google.android.gms.nearby.connection.PayloadTransferUpdate
import com.google.android.gms.nearby.connection.Strategy
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.io.InputStream
import java.nio.charset.StandardCharsets

class NearbyConnectionsManager(private val context: Context) {

    private val connectionsClient: ConnectionsClient = Nearby.getConnectionsClient(context)
    private val STRATEGY = Strategy.P2P_CLUSTER
    private val SERVICE_ID = "com.example.beaconlink.SERVICE_ID"
    private val TAG = "NearbyConnections"

    private val connectedEndpoints = mutableSetOf<String>()

    private val _incomingMessages = MutableSharedFlow<MessageEvent>()
    val incomingMessages: SharedFlow<MessageEvent> = _incomingMessages.asSharedFlow()

    private val payloadCallback = object : PayloadCallback() {
        override fun onPayloadReceived(endpointId: String, payload: Payload) {
            if (payload.type == Payload.Type.BYTES) {
                val bytes = payload.asBytes() ?: return
                val messageStr = String(bytes, StandardCharsets.UTF_8)
                Log.d(TAG, "Payload received from $endpointId: $messageStr")
                
                // Parse message (Format: "TXT:msg" or "SOS:msg")
                when {
                    messageStr.startsWith("TXT:") -> {
                        val text = messageStr.substringAfter("TXT:")
                        _incomingMessages.tryEmit(MessageEvent.Text(endpointId, text))
                    }
                    messageStr.startsWith("SOS:") -> {
                        val alert = messageStr.substringAfter("SOS:")
                        _incomingMessages.tryEmit(MessageEvent.Sos(endpointId, alert))
                    }
                    else -> {
                        _incomingMessages.tryEmit(MessageEvent.Text(endpointId, messageStr))
                    }
                }
            } else if (payload.type == Payload.Type.STREAM) {
                payload.asStream()?.asInputStream()?.let { inputStream ->
                    _incomingMessages.tryEmit(MessageEvent.AudioStream(endpointId, inputStream))
                }
            }
        }

        override fun onPayloadTransferUpdate(endpointId: String, update: PayloadTransferUpdate) {
            // Monitor transfer updates if needed
        }
    }

    private val connectionLifecycleCallback = object : ConnectionLifecycleCallback() {
        override fun onConnectionInitiated(endpointId: String, connectionInfo: ConnectionInfo) {
            Log.d(TAG, "Connection initiated: $endpointId, name: ${connectionInfo.endpointName}")
            // Automatically accept connections for mesh
            connectionsClient.acceptConnection(endpointId, payloadCallback)
                .addOnSuccessListener {
                    Log.d(TAG, "Accepted connection successfully: $endpointId")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Failed to accept connection: $endpointId", e)
                }
        }

        override fun onConnectionResult(endpointId: String, result: ConnectionResolution) {
            if (result.status.isSuccess) {
                Log.d(TAG, "Connection successful: $endpointId")
                connectedEndpoints.add(endpointId)
                _incomingMessages.tryEmit(MessageEvent.ConnectionStatus(endpointId, true))
            } else {
                Log.e(TAG, "Connection failed: $endpointId")
                connectedEndpoints.remove(endpointId)
            }
        }

        override fun onDisconnected(endpointId: String) {
            Log.d(TAG, "Disconnected: $endpointId")
            connectedEndpoints.remove(endpointId)
            _incomingMessages.tryEmit(MessageEvent.ConnectionStatus(endpointId, false))
        }
    }

    private val endpointDiscoveryCallback = object : EndpointDiscoveryCallback() {
        override fun onEndpointFound(endpointId: String, info: DiscoveredEndpointInfo) {
            Log.d(TAG, "Endpoint found: $endpointId, name: ${info.endpointName}")
            // Automatically request connection
            connectionsClient.requestConnection(
                Build.MODEL,
                endpointId,
                connectionLifecycleCallback
            ).addOnSuccessListener {
                Log.d(TAG, "Requesting connection to $endpointId")
            }.addOnFailureListener { e ->
                Log.e(TAG, "Failed to request connection to $endpointId", e)
            }
        }

        override fun onEndpointLost(endpointId: String) {
            Log.d(TAG, "Endpoint lost: $endpointId")
        }
    }

    fun startAdvertising() {
        val options = AdvertisingOptions.Builder().setStrategy(STRATEGY).build()
        connectionsClient.startAdvertising(
            Build.MODEL,
            SERVICE_ID,
            connectionLifecycleCallback,
            options
        ).addOnSuccessListener {
            Log.d(TAG, "Advertising started successfully")
        }.addOnFailureListener { e ->
            Log.e(TAG, "Failed to start advertising", e)
        }
    }

    fun stopAdvertising() {
        connectionsClient.stopAdvertising()
        Log.d(TAG, "Advertising stopped")
    }

    fun startDiscovery() {
        val options = DiscoveryOptions.Builder().setStrategy(STRATEGY).build()
        connectionsClient.startDiscovery(
            SERVICE_ID,
            endpointDiscoveryCallback,
            options
        ).addOnSuccessListener {
            Log.d(TAG, "Discovery started successfully")
        }.addOnFailureListener { e ->
            Log.e(TAG, "Failed to start discovery", e)
        }
    }

    fun stopDiscovery() {
        connectionsClient.stopDiscovery()
        Log.d(TAG, "Discovery stopped")
    }
    
    fun stopAll() {
        connectionsClient.stopAllEndpoints()
        connectedEndpoints.clear()
        stopAdvertising()
        stopDiscovery()
    }

    fun sendText(message: String) {
        val payloadStr = "TXT:$message"
        sendPayloadToAll(payloadStr)
    }

    fun sendSos(alert: String) {
        val payloadStr = "SOS:$alert"
        sendPayloadToAll(payloadStr)
    }

    private fun sendPayloadToAll(payloadStr: String) {
        if (connectedEndpoints.isEmpty()) {
            Log.d(TAG, "No connected endpoints to send payload")
            return
        }
        val bytes = payloadStr.toByteArray(StandardCharsets.UTF_8)
        val payload = Payload.fromBytes(bytes)
        
        connectionsClient.sendPayload(connectedEndpoints.toList(), payload)
            .addOnSuccessListener {
                Log.d(TAG, "Payload sent successfully")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to send payload", e)
            }
    }

    fun sendStream(pfd: ParcelFileDescriptor) {
        if (connectedEndpoints.isEmpty()) {
            Log.d(TAG, "No connected endpoints to send stream payload")
            return
        }
        val payload = Payload.fromStream(pfd)
        connectionsClient.sendPayload(connectedEndpoints.toList(), payload)
            .addOnSuccessListener {
                Log.d(TAG, "Stream Payload sent successfully")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to send Stream payload", e)
            }
    }
}

sealed class MessageEvent {
    data class Text(val endpointId: String, val message: String) : MessageEvent()
    data class Sos(val endpointId: String, val alert: String) : MessageEvent()
    data class ConnectionStatus(val endpointId: String, val connected: Boolean) : MessageEvent()
    data class AudioStream(val endpointId: String, val inputStream: InputStream) : MessageEvent()
}
