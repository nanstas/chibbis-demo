package com.nanoshkin.chibbisdemo.data.network.dto.reviews

data class ReviewsResponseItem(
    val DateAdded: String,
    val IsPositive: Boolean,
    val Message: String,
    val RestaurantName: String,
    val UserFIO: String
)