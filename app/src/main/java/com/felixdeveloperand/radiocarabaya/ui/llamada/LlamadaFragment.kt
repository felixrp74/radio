package com.felixdeveloperand.radiocarabaya.ui.llamada

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.felixdeveloperand.radiocarabaya.databinding.FragmentLlamadaBinding
import com.felixdeveloperand.radiocarabaya.utils.NetworkConnection
import com.felixdeveloperand.radiocarabaya.utils.showToast
import com.google.android.material.snackbar.Snackbar

class LlamadaFragment : Fragment() {

    private lateinit var _binding: FragmentLlamadaBinding
//    private lateinit var mediaPlayer: MediaPlayer

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding

    companion object {
        fun newInstance() = LlamadaFragment()
    }

    private lateinit var viewModel: LlamadaViewModel
    private lateinit var networkConnection: NetworkConnection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLlamadaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LlamadaViewModel::class.java)

        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + "949607936")
        startActivity(dialIntent)

        binding.btnCall.setOnClickListener {
            startActivity(dialIntent)
        }

        networkConnection = NetworkConnection(view.context)
        networkConnection.observe(viewLifecycleOwner) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                if(it){
                    showToast(it.toString())
                    val snack = Snackbar.make(view,"SI HAY INTERNET",Snackbar.LENGTH_LONG)
                    snack.show()
                }else{
                    val snack = Snackbar.make(view,"NO HAY INTERNET",Snackbar.LENGTH_LONG)
                    snack.show()
                }
            }
        }


    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}