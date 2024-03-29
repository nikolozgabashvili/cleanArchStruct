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


    override suspend fun getColors(): Flow<Resource<List<Color>>> = flow {
        try {

            // todo ეს ქვედა კომენტარი მოხსენი აბა და რა მოხდება კოდში?
           // val a = 5/0

            val response = api.getColors()

            if (response.isSuccessful) {
                emit(Resource.success(response.body()?.map { it.toColor() }))
            } else {
                emit(Resource.error("error", null))

            }
        }catch (e:Exception){

            Log.e("interror", "getColors: no internet", )
        }




    }

    override suspend fun getColorsForName(key: String): Flow<Resource<List<Color>>> = flow {

        try {
            // todo ეს ქვედა კომენტარი მოხსენი აბა და რა მოხდება კოდში?
          //  val a = 5/0
            val response = api.getColorsForName(key)

            if (response.isSuccessful) {
                emit(Resource.success(response.body()?.map { it.toColor() }))
            } else {
                emit(Resource.error("error", null))

            }
        }catch (e:Exception){
            Log.e("interror", "getColorsForName: interneterror", )
        }
    }

}
