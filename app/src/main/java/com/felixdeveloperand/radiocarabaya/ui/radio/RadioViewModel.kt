package com.felixdeveloperand.radiocarabaya.ui.radio

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RadioViewModel : ViewModel() {



    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    private val url = "https://24943.live.streamtheworld.com/CRP_LI_SC?csegid=20001&dist=20001&ttag=20001";

    private val _mediaPlayer: MutableLiveData<MediaPlayer> = MutableLiveData<MediaPlayer>()
    val mediaPlayer:LiveData<MediaPlayer> = _mediaPlayer

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    init {

        viewModelScope.launch {
            _loading.value = true
            val mediaPlayer2 = withContext(Dispatchers.IO) {
                MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(url)
                    prepare()
                    start()

                }
            }
            _mediaPlayer.value = mediaPlayer2
            _loading.value = false

        }

//        viewLi .launch {
//            whenStarted {
//                mediaPlayer = withContext(Dispatchers.IO) {
//                    MediaPlayer().apply {
//                        setAudioAttributes(
//                            AudioAttributes.Builder()
//                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                                .setUsage(AudioAttributes.USAGE_MEDIA)
//                                .build()
//
//                        )
//                        setDataSource(url)
//                        prepare() // might take long! (for buffering, etc)
//                        start()
//                    }
//                }
//
//                mediaPlayer.setOnPreparedListener {
//                    setOnClickListeners()
//                }
//            }
        // This line runs only after the whenStarted block above has completed.



    }


}