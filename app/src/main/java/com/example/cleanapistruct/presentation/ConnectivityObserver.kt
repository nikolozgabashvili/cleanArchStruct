package com.example.cleanapistruct.presentation

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {
    fun observe():Flow<Status>

    enum class Status{
        AVAILABLE,UNAVAILABLE,LOOSING,LOST,ONLYDATA
    }
}