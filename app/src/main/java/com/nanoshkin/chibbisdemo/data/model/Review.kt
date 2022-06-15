package com.nanoshkin.chibbisdemo.data.model

data class Review(
    val user: String,
    val date: String,
    val restaurantName: String,
    val message: String,
    val isPositive: Boolean
)
