package com.example.feed_design.di

import com.example.feed_design.data.api.ApiConstants.BASE_URL
import com.example.feed_design.data.api.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageApiModule {
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): ImageApi {
        return builder
            .build()
            .create(ImageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}