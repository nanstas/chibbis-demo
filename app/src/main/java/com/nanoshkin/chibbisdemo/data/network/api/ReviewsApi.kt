package com.nanoshkin.chibbisdemo.data.network.api

import com.nanoshkin.chibbisdemo.data.network.dto.reviews.ReviewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ReviewsApi {
    @GET("reviews")
    suspend fun getReviews(): Response<ReviewsResponse>
}