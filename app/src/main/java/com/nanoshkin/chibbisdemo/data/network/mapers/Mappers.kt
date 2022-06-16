package com.nanoshkin.chibbisdemo.data.network.mapers

import com.nanoshkin.chibbisdemo.data.model.Hit
import com.nanoshkin.chibbisdemo.data.model.Restaurant
import com.nanoshkin.chibbisdemo.data.model.Review
import com.nanoshkin.chibbisdemo.data.network.dto.hits.HitsResponseItem
import com.nanoshkin.chibbisdemo.data.network.dto.restaurants.RestaurantsResponseItem
import com.nanoshkin.chibbisdemo.data.network.dto.reviews.ReviewsResponseItem

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

fun HitsResponseItem.toHit(): Hit {
    return Hit(
        name = ProductName,
        image = ProductImage,
        price = ProductPrice,
        description = ProductDescription,
        restaurantName = RestaurantName,
        restaurantLogo = RestaurantLogo
    )

}

fun ReviewsResponseItem.toReview(): Review {
    return Review(
        user = UserFIO,
        date = DateAdded,
        restaurantName = RestaurantName,
        message = Message,
        isPositive = IsPositive
    )
}
