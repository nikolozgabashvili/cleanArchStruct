package com.example.cleanapistruct.data.remote.repository

import com.example.cleanapistruct.common.Resource
import com.example.cleanapistruct.data.remote.mapper.toColor
import com.example.cleanapistruct.data.remote.services.Api
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val api: Api
) : Repository {
    override suspend fun getColors(): Flow<Resource<List<Color>>> = flow {

        try {
            emit(Resource.Loading(true))
            val response = api.getColors()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.map { it.toColor() }!!))
            } else {
                emit(Resource.Error("Something Went Wrong !"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }


    }
}