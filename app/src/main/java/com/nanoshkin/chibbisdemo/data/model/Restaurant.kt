package com.nanoshkin.chibbisdemo.data.model

data class Restaurant(
    val name: String,
    val logo: String,
    val minCost: String,
    val deliveryCost: Int,
    val deliveryTime: Int,
    val positiveReviews: Int,
    val reviewsCount: Int,
    val specializations: List<String>
)
