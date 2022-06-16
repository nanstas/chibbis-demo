package com.nanoshkin.chibbisdemo.data.repository.restaurant

import com.nanoshkin.chibbisdemo.data.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurants(): List<Restaurant>
}