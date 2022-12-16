package com.felixdeveloperand.radiocarabaya.ui.llamada

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.felixdeveloperand.radiocarabaya.R

class LlamadaFragment : Fragment() {

    companion object {
        fun newInstance() = LlamadaFragment()
    }

    private lateinit var viewModel: LlamadaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_llamada, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LlamadaViewModel::class.java)

        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + "949607936")
        startActivity(dialIntent)

    }
}