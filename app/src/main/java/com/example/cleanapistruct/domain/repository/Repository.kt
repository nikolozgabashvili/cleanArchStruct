package com.example.cleanapistruct.domain.repository

import com.example.cleanapistruct.common.Resource
import com.example.cleanapistruct.domain.model.Color
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getColors(): Flow<Resource<List<Color>>>
}