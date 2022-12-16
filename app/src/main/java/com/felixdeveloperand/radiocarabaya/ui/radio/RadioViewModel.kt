package com.felixdeveloperand.radiocarabaya.ui.radio

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.*
import com.felixdeveloperand.radiocarabaya.utils.RADIO_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RadioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _mediaPlayer: MutableLiveData<MediaPlayer> = MutableLiveData<MediaPlayer>()
    val mediaPlayer:LiveData<MediaPlayer> = _mediaPlayer

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun startAudioStream() {

        viewModelScope.launch {
            _loading.value = true
            try {
                val mediaPlayer2 = withContext(Dispatchers.IO) {
                    MediaPlayer().apply {
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
                        start()
                    }
                }

                _mediaPlayer.value = mediaPlayer2

            }catch (e:Exception){

                Log.d("mylog", "Error playing in SoundHandler: $e")
            }

            _loading.value = false

        }

    }


    fun stopPlaying() {
            _mediaPlayer.value!!.stop()
            _mediaPlayer.value!!.release()
            _mediaPlayer.value = MediaPlayer()
            _mediaPlayer.value!!.reset()
    }

}