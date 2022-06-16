package com.nanoshkin.chibbisdemo.data.repository.reviews

import com.nanoshkin.chibbisdemo.data.model.Review

interface ReviewsRepository {
    suspend fun getReviews(): List<Review>
}