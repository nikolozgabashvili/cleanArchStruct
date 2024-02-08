package com.example.cleanapistruct


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.domain.repository.Repository
import com.example.cleanapistruct.presentation.ConnectivityObserver

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository


) : ViewModel() {

    private val _success = MutableStateFlow<List<Color>>(emptyList())
    val success: MutableStateFlow<List<Color>> = _success

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: MutableStateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<List<String>>(emptyList())
    val error: MutableStateFlow<List<String>> = _error

    private var filteredColor: Color? = null
    val myColor get() = filteredColor

    private var searchStateText:String = ""
    fun setStateText(string: String){
        searchStateText = string
    }
    fun getStateText():String = searchStateText

    private var previousNetState:ConnectivityObserver.Status = ConnectivityObserver.Status.LOST
    fun setPrevStatus(status:ConnectivityObserver.Status){
        previousNetState = status
    }
    fun getPrevStatus() = previousNetState

    init {
        getAllColors()


    }


    fun getAllColors() {
        try {
            _loading.value = true
            viewModelScope.launch {
                repository.getColors().collect { resource ->
                    resource.data?.let {

                        Log.d("request", "getAllColors: ${it}")
                        _success.value = it
                        _loading.value=false
                    }

                }

            }
        } catch (e: Exception) {
            Log.e("error", "getAllColors: error")
        }

    }

    fun getAllColorName(key: String) {
        try {
            _loading.value = true

            viewModelScope.launch {
                repository.getColorsForName(key).collect { resource ->

                    resource.data?.let {
                        if (key.length > 2)

                            _success.value = writeInMap(it)
                        else
                            _success.value = it
                        _loading.value = false

                    }

                }

            }
        } catch (e: Exception) {
            Log.e("error", "getAllColorName: error")
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