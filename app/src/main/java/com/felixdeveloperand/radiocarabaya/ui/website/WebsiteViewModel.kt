package com.felixdeveloperand.radiocarabaya.ui.website

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebsiteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    fun onBtnRollPressed(){
        val randomNumer = Math.random()
//        val randomNumberFrom0to100 = randomNumer.toInt()
        _text.value = randomNumer.toString()
    }

    override fun onCleared() {
        super.onCleared()
    }
}