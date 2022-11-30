package com.felixdeveloperand.radiocarabaya.services

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.felixdeveloperand.radiocarabaya.utils.RADIO_URL

class BackgroundMusicService : Service() {

    private var player: MediaPlayer = MediaPlayer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (player == null) player = MediaPlayer()

        try {
            Log.d("mylog", "Playing: $RADIO_URL")
            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
            player.setDataSource(RADIO_URL)
            //descriptor.close();
            player.prepare()
            player.setVolume(1f, 1f)
            player.isLooping = false
            player.start()
        } catch (e: Exception) {
            Log.d("mylog", "Error playing in SoundHandler: $e")
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun  stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        player.release()
    }

}