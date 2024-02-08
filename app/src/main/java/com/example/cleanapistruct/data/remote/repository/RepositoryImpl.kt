package com.example.cleanapistruct.data.remote.repository

import android.util.Log
import com.example.cleanapistruct.common.Resource.Resource
import com.example.cleanapistruct.data.remote.mapper.toColor
import com.example.cleanapistruct.data.remote.services.Api
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {


    override suspend fun getColors(key: String?): Flow<Resource<List<Color>>> = flow {

        try {
            val response = api.getColors(key)
            emit(Resource.loading(null))

            if (response.isSuccessful) {
                emit(Resource.success(response.body()?.map { it.toColor() }))
            } else {
                emit(Resource.error("error", null))

            }
        }

        catch (e:Exception){
            emit(Resource.error("$e", null))

        }
    }

}
