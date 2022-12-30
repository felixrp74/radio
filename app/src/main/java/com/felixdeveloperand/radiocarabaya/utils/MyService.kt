package com.felixdeveloperand.radiocarabaya.utils

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {

    private lateinit var mMediaPlayer:MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        showToast("Service created")
        mMediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(RADIO_URL)
            prepare()
            setVolume(1f, 1f)
            isLooping = false
        }
    }

//    @Deprecated("Deprecated in Java")
//    override fun onStart(intent: Intent?, startId: Int) {
//        super.onStart(intent, startId)
//        showToast("Service started")
//        mMediaPlayer.start()
//    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showToast("Service started")
        mMediaPlayer.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("Service stopped")
        mMediaPlayer.stop()
    }
}