package com.example.cleanapistruct

import androidx.lifecycle.ViewModel
import com.example.cleanapistruct.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository

):ViewModel() {

}