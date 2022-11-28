package com.felixdeveloperand.radiocarabaya.ui.radio

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RadioViewModel : ViewModel() {



    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    private val url = "https://24943.live.streamtheworld.com/CRP_LI_SC?csegid=20001&dist=20001&ttag=20001";

    private var _mediaPlayer: MutableLiveData<MediaPlayer> = MutableLiveData<MediaPlayer>()
    var mediaPlayer:LiveData<MediaPlayer> = _mediaPlayer

    fun play(){



    }


}