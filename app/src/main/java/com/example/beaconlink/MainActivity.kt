package com.example.beaconlink

import android.Manifest
import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.beaconlink.ui.NearbyViewModel
import com.example.beaconlink.ui.theme.BeaconLinkTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class WebAppInterface(private val context: Context, private val viewModel: NearbyViewModel) {
    private var currentRingtone: Ringtone? = null

    @JavascriptInterface
    fun triggerSos() {
        viewModel.sendSos("Emergency SOS from HTML Prototype!")
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, "SOS Broadcast Triggered", Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun startWalkieTalkie() {
        viewModel.startRecording()
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, "Walkie-Talkie Recording...", Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun stopWalkieTalkie() {
        viewModel.stopRecording()
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, "Walkie-Talkie Stopped", Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun sendMessage(msg: String) {
        viewModel.sendText(msg)
    }

    @JavascriptInterface
    fun startDiscovery() {
        viewModel.startDiscovery()
    }

    @JavascriptInterface
    fun startAdvertising() {
        viewModel.startAdvertising()
    }

    @JavascriptInterface
    fun playAlarm() {
        Handler(Looper.getMainLooper()).post {
            try {
                if (currentRingtone?.isPlaying == true) {
                    currentRingtone?.stop()
                    currentRingtone = null
                    Toast.makeText(context, "Alarm Stopped", Toast.LENGTH_SHORT).show()
                } else {
                    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                        ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    currentRingtone = RingtoneManager.getRingtone(context, uri)
                    currentRingtone?.play()
                    Toast.makeText(context, "Sound Alarm Activated", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeaconLinkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NearbyScreen(innerPadding = innerPadding)
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NearbyScreen(innerPadding: PaddingValues, viewModel: NearbyViewModel = viewModel()) {
    val permissions = mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.RECORD_AUDIO
    )
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        permissions.add(Manifest.permission.BLUETOOTH_ADVERTISE)
        permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
        permissions.add(Manifest.permission.BLUETOOTH_SCAN)
    }
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissions.add(Manifest.permission.NEARBY_WIFI_DEVICES)
    }

    val permissionsState = rememberMultiplePermissionsState(permissions = permissions)
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if (!permissionsState.allPermissionsGranted) {
            permissionsState.launchMultiplePermissionRequest()
        }
    }

    if (permissionsState.allPermissionsGranted) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    
                    // Enable file access and mixed content
                    settings.allowFileAccess = true
                    settings.allowContentAccess = true
                    settings.allowFileAccessFromFileURLs = true
                    settings.allowUniversalAccessFromFileURLs = true
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    
                    webChromeClient = WebChromeClient()
                    
                    addJavascriptInterface(WebAppInterface(context, viewModel), "Android")
                    loadUrl("file:///android_asset/frontend/offgrid_home_dashboard/code.html")
                }
            },
            update = { webView ->
                val messagesJson = uiState.messages.joinToString(
                    prefix = "[",
                    separator = ",",
                    postfix = "]"
                ) { "'${it.replace("'", "\\'")}'" }
                
                webView.evaluateJavascript("""
                    if (window.updateMessages) {
                        window.updateMessages($messagesJson);
                    }
                    if (window.updateStatus) {
                        window.updateStatus(${uiState.isAdvertising}, ${uiState.isDiscovering});
                    }
                """.trimIndent(), null)
            }
        )
    } else {
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Permissions required for Nearby Connections.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { permissionsState.launchMultiplePermissionRequest() }) {
                Text("Request Permissions")
            }
        }
    }
}