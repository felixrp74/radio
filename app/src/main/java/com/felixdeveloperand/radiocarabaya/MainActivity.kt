package com.felixdeveloperand.radiocarabaya

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.felixdeveloperand.radiocarabaya.databinding.ActivityMainBinding
import com.felixdeveloperand.radiocarabaya.utils.MyService
import com.felixdeveloperand.radiocarabaya.utils.NetworkConnection
import com.felixdeveloperand.radiocarabaya.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_llamada
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //====================================================
        val layoutInflater = findViewById<View>(R.id.networkErrorLayout)
        val networkConnection= NetworkConnection(applicationContext)
        networkConnection.observe(this) { isConnected ->
            if (isConnected) {
//                Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
                showToast("Connected")
                layoutInflater.visibility= View.GONE
            } else {
//                Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show()
                showToast("Not Connected")
                layoutInflater.visibility= View.VISIBLE
            }
        }
        //====================================================

//        startService(Intent(applicationContext, MyService::class.java))

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // navigate to settings screen
                this@MainActivity.apply {
                    moveTaskToBack(true)
                    finish()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}