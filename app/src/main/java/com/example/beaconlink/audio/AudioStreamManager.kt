package com.example.beaconlink.audio

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaRecorder
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.InputStream
import kotlin.concurrent.thread

class AudioStreamManager {
    private val TAG = "AudioStreamManager"
    private val sampleRate = 16000
    private val channelConfigIn = AudioFormat.CHANNEL_IN_MONO
    private val channelConfigOut = AudioFormat.CHANNEL_OUT_MONO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT

    private val minBufferSizeIn = AudioRecord.getMinBufferSize(sampleRate, channelConfigIn, audioFormat)
    private val minBufferSizeOut = AudioTrack.getMinBufferSize(sampleRate, channelConfigOut, audioFormat)

    private var audioRecord: AudioRecord? = null
    private var isRecording = false
    private var recordThread: Thread? = null

    private var audioTrack: AudioTrack? = null
    private var isPlaying = false
    private var playThread: Thread? = null

    @SuppressLint("MissingPermission")
    fun startRecording(): ParcelFileDescriptor? {
        if (isRecording) return null

        val pipe = ParcelFileDescriptor.createPipe()
        val readFd = pipe[0]
        val writeFd = pipe[1]

        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            channelConfigIn,
            audioFormat,
            minBufferSizeIn
        )

        if (audioRecord?.state != AudioRecord.STATE_INITIALIZED) {
            Log.e(TAG, "AudioRecord initialization failed")
            return null
        }

        audioRecord?.startRecording()
        isRecording = true

        recordThread = thread {
            val outputStream = ParcelFileDescriptor.AutoCloseOutputStream(writeFd)
            val buffer = ByteArray(minBufferSizeIn)
            try {
                while (isRecording) {
                    val read = audioRecord?.read(buffer, 0, buffer.size) ?: 0
                    if (read > 0) {
                        outputStream.write(buffer, 0, read)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error recording audio", e)
            } finally {
                try { outputStream.close() } catch (e: Exception) { }
            }
        }

        return readFd
    }

    fun stopRecording() {
        isRecording = false
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
        recordThread?.join()
        recordThread = null
    }

    fun startPlaying(inputStream: InputStream) {
        if (isPlaying) stopPlaying()

        audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(audioFormat)
                    .setSampleRate(sampleRate)
                    .setChannelMask(channelConfigOut)
                    .build()
            )
            .setBufferSizeInBytes(minBufferSizeOut)
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build()

        audioTrack?.play()
        isPlaying = true

        playThread = thread {
            val buffer = ByteArray(minBufferSizeOut)
            try {
                var read = 0
                while (isPlaying && inputStream.read(buffer).also { read = it } != -1) {
                    audioTrack?.write(buffer, 0, read)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error playing audio", e)
            } finally {
                stopPlaying()
                try { inputStream.close() } catch (e: Exception) { }
            }
        }
    }

    fun stopPlaying() {
        isPlaying = false
        try {
            if (audioTrack?.state == AudioTrack.STATE_INITIALIZED) {
                audioTrack?.stop()
            }
        } catch (e: Exception) {}
        audioTrack?.release()
        audioTrack = null
    }
}
