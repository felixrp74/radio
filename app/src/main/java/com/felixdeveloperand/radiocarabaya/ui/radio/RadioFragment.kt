package com.felixdeveloperand.radiocarabaya.ui.radio


import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.felixdeveloperand.radiocarabaya.R
import com.felixdeveloperand.radiocarabaya.databinding.FragmentHomeBinding

class RadioFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var mediaPlayer: MediaPlayer
    private val url = "https://24943.live.streamtheworld.com/CRP_LI_SC?csegid=20001&dist=20001&ttag=20001";
    //        val url = "https://stream.radiocarabaya.com/radio/8000/radio?1586364355"



    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val radioViewModel =
            ViewModelProvider(this).get(RadioViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }


        mediaPlayer.setOnPreparedListener {
            setOnClickListeners()
        }

/*
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

 */
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    private fun setOnClickListeners() {
        binding.buttonPlay.setOnClickListener {
//            mediaPlayer.prepareAsync()
            mediaPlayer.start()
            Toast.makeText(activity, "Play Buscando Radio || Reproduciendo... ", Toast.LENGTH_SHORT).show()
        }

        binding.buttonPause.setOnClickListener {
//            mediaPlayer.prepareAsync()
            mediaPlayer.pause()
            Toast.makeText(activity, "En Pausa...", Toast.LENGTH_SHORT).show()
        }
        binding.buttonCloseApp.setOnClickListener {
            mediaPlayer.stop()
            activity?.apply {
                moveTaskToBack(true)
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        mediaPlayer.prepareAsync()
        mediaPlayer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        activity?.apply {
            moveTaskToBack(true)
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
    }
}