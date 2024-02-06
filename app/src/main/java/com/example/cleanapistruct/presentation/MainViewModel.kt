package com.example.cleanapistruct.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanapistruct.common.Resource
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.domain.usecases.GetColorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(var getColorsUseCase: GetColorsUseCase) :
    ViewModel() {

    private val _colorsData = MutableStateFlow<List<Color>>(emptyList())
    val colorsData = _colorsData.asStateFlow()

    private val _errorMsg = MutableStateFlow<List<Color>>(emptyList())
    val errorMsg = _errorMsg.asStateFlow()

    private val _isLoading = MutableStateFlow<List<Color>>(emptyList())
    val isLoading = _isLoading.asStateFlow()


    fun getData() {
        viewModelScope.launch {
            getColorsUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Success -> {
                         response.data?.let {
                             _colorsData.value =response.data
                         }
                    }
                    is Resource.Error -> {
                        _errorMsg.value = listOf()
                    }
                    else -> {Unit}
                }
            }
        }
    }
}