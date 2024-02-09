package com.example.cleanapistruct


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanapistruct.common.Resource.Status
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.domain.repository.Repository
import com.example.cleanapistruct.presentation.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository


) : ViewModel() {

    private val debounceDelay = 400L

    private val _success = MutableStateFlow<List<Color>>(emptyList())
    val success: MutableStateFlow<List<Color>> = _success

    private val _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> = _loading

    private val _error = MutableStateFlow("")
    val error: MutableStateFlow<String> = _error

    private var filteredColor: Color? = null
    val myColor get() = filteredColor


    private var debounceJob: Job? = null

    private var searchStateText: String = ""
    fun setStateText(string: String) {
        searchStateText = string
    }

    fun getStateText(): String = searchStateText
    private val _textFlow = MutableStateFlow("")
    val textFlow: MutableStateFlow<String> = _textFlow

    fun setTextFlow(key: String) {
        _textFlow.value = key
    }

    fun cancelDebounceJob(){
        debounceJob?.cancel()
    }


    private var previousNetState: ConnectivityObserver.Status = ConnectivityObserver.Status.LOST
    fun setPrevStatus(status: ConnectivityObserver.Status) {
        previousNetState = status
    }

    fun getPrevStatus() = previousNetState



    init {
        getAllColor()



    }


    fun getAllColor(key: String = "") {

        _loading.value = true

        viewModelScope.launch {
            repository.getColors(key).collect { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                            if (key.length > 2)

                                _success.value = writeInMap(it)
                            else
                                _success.value = it
                            _loading.value = false

                        }
                    }

                    Status.ERROR -> {
                        println("status error returned")
                        _error.value = resource.message.toString()
                        _loading.value = false
                    }

                    Status.LOADING -> _loading.value = true
                }


            }

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

    fun startDebounceJob() {
        debounceJob = viewModelScope.launch {
            println("hehehehe")
            println(textFlow.value)
            textFlow.debounce(debounceDelay).collect {
                getAllColor(it)
                println("hehehehe")
            }

        }
    }


}