package com.felixdeveloperand.radiocarabaya.ui.llamada

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LlamadaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}