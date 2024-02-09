package com.example.cleanapistruct.DI

import com.example.cleanapistruct.data.remote.repository.RepositoryImpl
import com.example.cleanapistruct.data.remote.services.Api
import com.example.cleanapistruct.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Set log level as needed
    }


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
            .client(client)
            .baseUrl("https://www.colourlovers.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }




    @Provides
    @Singleton
    fun provideRepositoryInterface(api: Api): Repository {
        return RepositoryImpl(api)
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()



}