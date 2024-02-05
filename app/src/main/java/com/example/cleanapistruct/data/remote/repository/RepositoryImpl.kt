package com.example.cleanapistruct.data.remote.repository

import android.app.Application
import com.example.cleanapistruct.data.remote.mapper.toColor
import com.example.cleanapistruct.data.remote.services.Api
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.domain.repository.Repository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val api: Api,
    @ApplicationContext context:Application
) : Repository {
    override suspend fun getColors(): List<Color> {

        return api.getColors().map { it.toColor() }

    }
}