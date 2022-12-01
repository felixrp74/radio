package com.felixdeveloperand.radiocarabaya.ui.radio


import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.felixdeveloperand.radiocarabaya.databinding.FragmentRadioBinding
import com.felixdeveloperand.radiocarabaya.utils.RADIO_URL


class RadioFragment : Fragment() {

    private lateinit var _binding: FragmentRadioBinding
//    private lateinit var mediaPlayer: MediaPlayer

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding

    private val radioViewModel: RadioViewModel by viewModels()

    var isStreaming:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRadioBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioViewModel.loading.observe(viewLifecycleOwner){ visible ->
            binding.pbCircular.visibility = if (visible) View.VISIBLE else View.GONE
        }

        binding.buttonPlay.setOnClickListener {

            isStreaming = if (isStreaming) {
                radioViewModel.stopPlaying()
                false
            } else {
                radioViewModel.startAudioStream()
                true
            }
        }
    }

}