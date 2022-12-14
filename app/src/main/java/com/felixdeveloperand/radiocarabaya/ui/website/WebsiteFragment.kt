package com.felixdeveloperand.radiocarabaya.ui.website

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.felixdeveloperand.radiocarabaya.databinding.FragmentWebsiteBinding

class WebsiteFragment : Fragment() {

    private var _binding: FragmentWebsiteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var websiteViewModel:WebsiteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        websiteViewModel =
            ViewModelProvider(this)[WebsiteViewModel::class.java]

        _binding = FragmentWebsiteBinding.inflate(inflater, container, false)
        val root: View = binding.root
/*
        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.btnRoll.setOnClickListener {
            galleryViewModel.onBtnRollPressed()
        }

        galleryViewModel.text.observe(viewLifecycleOwner){
            binding.textGallery.text = it
        }*/

        binding.webview.loadUrl("https://radioondaazul.com/")
        binding.webview.settings.javaScriptEnabled = true

        binding.floatingActionButtonWebSite.setOnClickListener {
            val url = "https://radioondaazul.com/"
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            startActivity(urlIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}