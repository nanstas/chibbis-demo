package com.nanoshkin.chibbisdemo.data.network

import com.nanoshkin.chibbisdemo.data.model.Restaurant
import com.nanoshkin.chibbisdemo.data.network.dto.restaurants.RestaurantsResponseItem

fun RestaurantsResponseItem.toRestaurant(): Restaurant {
    return Restaurant(
        name = Name,
        logo = Logo,
        minCost = MinCost,
        deliveryCost = DeliveryCost,
        deliveryTime = DeliveryTime,
        positiveReviews = PositiveReviews,
        reviewsCount = ReviewsCount,
        specializations = Specializations.map { it.Name }
    )
}