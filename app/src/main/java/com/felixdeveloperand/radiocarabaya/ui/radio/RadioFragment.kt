package com.felixdeveloperand.radiocarabaya.ui.radio


import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.felixdeveloperand.radiocarabaya.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RadioFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var mediaPlayer: MediaPlayer

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var radioViewModel: RadioViewModel

    private val url = "https://24943.live.streamtheworld.com/CRP_LI_SC?csegid=20001&dist=20001&ttag=20001"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        radioViewModel = ViewModelProvider(this)[RadioViewModel::class.java]

        radioViewModel.mediaPlayer.observe(viewLifecycleOwner){
            mediaPlayer = it
        }

        lifecycleScope.launch {
            whenStarted {
                mediaPlayer = withContext(Dispatchers.IO) {
                    MediaPlayer().apply {
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
                }

                mediaPlayer.setOnPreparedListener {
                    setOnClickListeners()
                }
            }
            // This line runs only after the whenStarted block above has completed.


        }

        return binding.root
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

}