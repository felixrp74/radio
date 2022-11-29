package com.felixdeveloperand.radiocarabaya.ui.radio


import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.felixdeveloperand.radiocarabaya.databinding.FragmentRadioBinding

class RadioFragment : Fragment() {

    private lateinit var _binding: FragmentRadioBinding
//    private lateinit var mediaPlayer: MediaPlayer

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

//    private lateinit var radioViewModel: RadioViewModel
    private val radioViewModel: RadioViewModel by viewModels()

    private val url = "https://24943.live.streamtheworld.com/CRP_LI_SC?csegid=20001&dist=20001&ttag=20001"

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

//        radioViewModel.mediaPlayer.observe(viewLifecycleOwner){
//
//        }

    }

    private fun setOnClickListeners() {
        binding.buttonPlay.setOnClickListener {
//            mediaPlayer.prepareAsync()
//            mediaPlayer.start()
            Toast.makeText(activity, "Play Buscando Radio || Reproduciendo... ", Toast.LENGTH_SHORT).show()
        }

        binding.buttonPause.setOnClickListener {
//            mediaPlayer.prepareAsync()
//            mediaPlayer.pause()
            Toast.makeText(activity, "En Pausa...", Toast.LENGTH_SHORT).show()
        }
        binding.buttonCloseApp.setOnClickListener {
//            mediaPlayer.stop()
            activity?.apply {
                moveTaskToBack(true)
                finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
//        mediaPlayer.stop()
//        activity?.apply {
//            moveTaskToBack(true)
//            finish()
//        }
    }

}