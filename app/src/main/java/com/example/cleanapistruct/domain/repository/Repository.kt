package com.example.cleanapistruct.domain.repository

import com.example.cleanapistruct.domain.model.Color

interface Repository {
    suspend fun getColors():List<Color>
}