package com.example.cleanapistruct.DI

import com.example.cleanapistruct.data.remote.repository.RepositoryImpl
import com.example.cleanapistruct.data.remote.services.Api
import com.example.cleanapistruct.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.colourlovers.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }




    @Provides
    @Singleton
    fun provideRepositoryInterface(api: Api): Repository {
        return RepositoryImpl(api)
    }


}