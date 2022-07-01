package com.example.feed_design.data.repository

import com.example.feed_design.data.api.ImageApi
import com.example.feed_design.data.api.model.Image
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val imageApi: ImageApi
) {
    suspend fun getImages(): List<Image> {
        return imageApi.getImage()
    }
}