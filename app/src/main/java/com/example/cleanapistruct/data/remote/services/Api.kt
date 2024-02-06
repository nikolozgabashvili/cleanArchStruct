package com.example.cleanapistruct.data.remote.services

import com.example.cleanapistruct.data.remote.model.ColorDto
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("api/colors/new?format=json")
    fun getColors(): Response<List<ColorDto>>

}