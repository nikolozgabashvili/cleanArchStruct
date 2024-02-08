package com.example.cleanapistruct.domain.repository

import com.example.cleanapistruct.common.Resource.Resource
import com.example.cleanapistruct.domain.model.Color
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getColors(key:String? = ""): Flow<Resource<List<Color>>>

}