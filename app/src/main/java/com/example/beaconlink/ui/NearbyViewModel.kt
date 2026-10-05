package com.example.beaconlink.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.beaconlink.audio.AudioStreamManager
import com.example.beaconlink.data.local.BeaconLinkDatabase
import com.example.beaconlink.data.local.entity.MessageEntity
import com.example.beaconlink.data.local.entity.SOSLogEntity
import com.example.beaconlink.network.MessageEvent
import com.example.beaconlink.network.NearbyConnectionsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NearbyViewModel(application: Application) : AndroidViewModel(application) {

    private val nearbyManager = NearbyConnectionsManager(application)
    private val audioManager = AudioStreamManager()
    
    private val database = BeaconLinkDatabase.getDatabase(application)
    private val messageDao = database.messageDao()
    private val sosLogDao = database.sosLogDao()

    private val _uiState = MutableStateFlow(NearbyUiState())
    val uiState: StateFlow<NearbyUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            messageDao.getAllMessages().collect { msgs ->
                val strMsgs = msgs.map { if (it.isSent) "You: ${it.content}" else "${it.senderId}: ${it.content}" }
                _uiState.update { it.copy(messages = strMsgs) }
            }
        }
        viewModelScope.launch {
            nearbyManager.incomingMessages.collect { event ->
                when (event) {
                    is MessageEvent.Text -> {
                        val entity = MessageEntity(
                            content = event.message,
                            senderId = event.endpointId,
                            receiverId = "Me",
                            timestamp = System.currentTimeMillis(),
                            isSent = false
                        )
                        messageDao.insertMessage(entity)
                    }
                    is MessageEvent.Sos -> {
                        val entity = SOSLogEntity(
                            timestamp = System.currentTimeMillis(),
                            latitude = null,
                            longitude = null,
                            message = "SOS from ${event.endpointId}: ${event.alert}"
                        )
                        sosLogDao.insertSOSLog(entity)
                    }
                    is MessageEvent.ConnectionStatus -> {
                        // Optionally update UI for connection status, right now we just use a log
                        // or we could add it to a separate status state
                        // we won't clutter messages with connection status if we are loading from db
                    }
                    is MessageEvent.AudioStream -> {
                        audioManager.startPlaying(event.inputStream)
                    }
                }
            }
        }
    }

    fun startAdvertising() {
        nearbyManager.startAdvertising()
        _uiState.update { it.copy(isAdvertising = true) }
    }

    fun stopAdvertising() {
        nearbyManager.stopAdvertising()
        _uiState.update { it.copy(isAdvertising = false) }
    }

    fun startDiscovery() {
        nearbyManager.startDiscovery()
        _uiState.update { it.copy(isDiscovering = true) }
    }

    fun stopDiscovery() {
        nearbyManager.stopDiscovery()
        _uiState.update { it.copy(isDiscovering = false) }
    }

    fun sendText(msg: String) {
        nearbyManager.sendText(msg)
        viewModelScope.launch {
            val entity = MessageEntity(
                content = msg,
                senderId = "Me",
                receiverId = "Broadcast", // Or specific ID if known
                timestamp = System.currentTimeMillis(),
                isSent = true
            )
            messageDao.insertMessage(entity)
        }
    }

    fun sendSos(alert: String) {
        nearbyManager.sendSos(alert)
        viewModelScope.launch {
            val entity = SOSLogEntity(
                timestamp = System.currentTimeMillis(),
                latitude = null,
                longitude = null,
                message = "Sent SOS: $alert"
            )
            sosLogDao.insertSOSLog(entity)
        }
    }

    fun startRecording() {
        val pfd = audioManager.startRecording()
        if (pfd != null) {
            nearbyManager.sendStream(pfd)
            _uiState.update { it.copy(isRecording = true) }
        }
    }

    fun stopRecording() {
        audioManager.stopRecording()
        _uiState.update { it.copy(isRecording = false) }
    }
    
    override fun onCleared() {
        super.onCleared()
        audioManager.stopRecording()
        audioManager.stopPlaying()
        nearbyManager.stopAll()
    }
}

data class NearbyUiState(
    val isAdvertising: Boolean = false,
    val isDiscovering: Boolean = false,
    val isRecording: Boolean = false,
    val messages: List<String> = emptyList()
)
