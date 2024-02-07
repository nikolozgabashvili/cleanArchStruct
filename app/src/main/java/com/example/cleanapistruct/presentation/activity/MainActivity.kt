package com.example.cleanapistruct.presentation.activity


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.cleanapistruct.R
import com.example.cleanapistruct.databinding.ActivityMainBinding
import com.example.cleanapistruct.presentation.ConnectivityObserver
import com.example.cleanapistruct.presentation.NetworkConObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.function.LongFunction


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        connectivityObserver = NetworkConObserver(applicationContext)
        connectivityObserver.observe().onEach {
            if (it == ConnectivityObserver.Status.AVAILABLE) {
                println("wifi on")

                val navController = findNavController(R.id.my_nav_host_fragment)
                navController.popBackStack(R.id.buttonFragment, true)

                navController.navigate(R.id.listFragment)

            }
            if (it == ConnectivityObserver.Status.LOST) {
                println("wifi off")

            }


        }.launchIn(lifecycleScope)


    }

}