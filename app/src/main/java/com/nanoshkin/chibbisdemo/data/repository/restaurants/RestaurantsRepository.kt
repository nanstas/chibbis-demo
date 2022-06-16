package com.nanoshkin.chibbisdemo.data.repository.restaurants

import com.nanoshkin.chibbisdemo.data.model.Restaurant

interface RestaurantsRepository {
    suspend fun getRestaurants(): List<Restaurant>
}