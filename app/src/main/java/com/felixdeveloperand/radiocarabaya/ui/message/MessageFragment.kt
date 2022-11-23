package com.felixdeveloperand.radiocarabaya.ui.message

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.felixdeveloperand.radiocarabaya.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val messageViewModel =
            ViewModelProvider(this)[MessageViewModel::class.java]

        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        messageViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCardViewWhatsapp.setOnClickListener{

            val url = "https://api.whatsapp.com/send?phone=+51926538932"

            val openWhatsappIntent = Intent(Intent.ACTION_VIEW)
            openWhatsappIntent.data = Uri.parse(url)
            startActivity(openWhatsappIntent)
        }

        binding.btnCardViewTelegram.setOnClickListener{

            val urlTele = "https://t.me/devexpertocanal"

            val openTelegramIntent = Intent(Intent.ACTION_VIEW)
            openTelegramIntent.data = Uri.parse(urlTele)
            startActivity(openTelegramIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}