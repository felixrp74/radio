package com.felixdeveloperand.radiocarabaya.ui.radio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.felixdeveloperand.radiocarabaya.R
import com.felixdeveloperand.radiocarabaya.databinding.FragmentRadioBinding
import com.felixdeveloperand.radiocarabaya.utils.showToast

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

        binding.buttonPlayPause.setOnClickListener {
            isStreaming = if (isStreaming) {
                radioViewModel.stopPlaying()
                binding.buttonPlayPause.setBackgroundResource(R.drawable.ic_play)
                showToast("En pausa...")
                false
            } else {
                radioViewModel.startAudioStream()
                binding.buttonPlayPause.setBackgroundResource(R.drawable.ic_pause)
                showToast( "Play Buscando Radio || Reproduciendo... ")
                true
            }
        }
    }
}