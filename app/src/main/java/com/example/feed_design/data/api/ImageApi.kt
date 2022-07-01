package com.example.feed_design.data.api

import com.example.feed_design.data.api.ApiConstants.END_POINT
import com.example.feed_design.data.api.model.Image
import retrofit2.http.GET

interface ImageApi {

    @GET(END_POINT)
    suspend fun getImage(): List<Image>
}