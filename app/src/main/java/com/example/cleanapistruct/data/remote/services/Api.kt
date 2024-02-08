package com.example.cleanapistruct.data.remote.services

import com.example.cleanapistruct.common.constants.Constants.GET_ITEM_WITH_NAME
import com.example.cleanapistruct.data.remote.model.ColorDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(GET_ITEM_WITH_NAME)
    suspend fun getColors(@Query ("keywords") key:String?): Response<List<ColorDto>>



}