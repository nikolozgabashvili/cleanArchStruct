package com.example.cleanapistruct


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.domain.repository.Repository
import com.example.cleanapistruct.presentation.ConnectivityObserver
import com.example.cleanapistruct.presentation.NetworkConObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository


) : ViewModel() {

    private val _success = MutableStateFlow<List<Color>>(emptyList())
    val success: MutableStateFlow<List<Color>> = _success

    private val _loading = MutableStateFlow<List<Boolean>>(emptyList())
    val loading: MutableStateFlow<List<Boolean>> = _loading

    private val _error = MutableStateFlow<List<String>>(emptyList())
    val error: MutableStateFlow<List<String>> = _error

    private var filteredColor: Color? = null
    val myColor get() = filteredColor



    init {
        getAllColors()


    }



     fun getAllColors() {
         try {
             viewModelScope.launch {
                 repository.getColors().collect { resource ->
                     resource.data?.let {

                         Log.d("request", "getAllColors: ${it}")
                         _success.value = it
                     }

                 }

             }
         }catch (e:Exception){
             Log.e("error", "getAllColors: error", )
         }

    }

    fun getAllColorName(key: String) {
        try {


            viewModelScope.launch {
                repository.getColorsForName(key).collect { resource ->

                    resource.data?.let {
                        if (key.length > 2)

                            _success.value = writeInMap(it)
                        else
                            _success.value = it


                    }

                }

            }
        }catch (e:Exception){
            Log.e("error", "getAllColorName: error", )
        }

    }

    private fun writeInMap(data: List<Color>): List<Color> {
        val map1: MutableMap<String?, Color> = mutableMapOf()
        data.map {
            map1[it.userName] = it
        }

        return map1.map { it.value }

    }

    fun filterColorById(id: Long) {
        success.value.map {
            if (it.id == id) {
                filteredColor = it
            }

        }

    }


}