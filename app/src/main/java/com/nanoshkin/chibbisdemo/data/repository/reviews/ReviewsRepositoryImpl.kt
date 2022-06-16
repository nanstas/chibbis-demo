package com.nanoshkin.chibbisdemo.data.repository.reviews

import com.nanoshkin.chibbisdemo.data.exeptions.ApiException
import com.nanoshkin.chibbisdemo.data.exeptions.ServerException
import com.nanoshkin.chibbisdemo.data.exeptions.UnknownException
import com.nanoshkin.chibbisdemo.data.model.Review
import com.nanoshkin.chibbisdemo.data.network.api.HitsApi
import com.nanoshkin.chibbisdemo.data.network.api.ReviewsApi
import com.nanoshkin.chibbisdemo.data.network.mapers.toHit
import com.nanoshkin.chibbisdemo.data.network.mapers.toReview
import java.io.IOException
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private val reviewsApi: ReviewsApi
) : ReviewsRepository {
    override suspend fun getReviews(): List<Review> {
        try {
            val response = reviewsApi.getReviews()
            return response.body()?.map { it.toReview() } ?: throw ApiException(
                response.code(),
                response.message()
            )
        } catch (e: IOException) {
            throw ServerException
        } catch (e: Exception) {
            throw UnknownException
        }
    }
}