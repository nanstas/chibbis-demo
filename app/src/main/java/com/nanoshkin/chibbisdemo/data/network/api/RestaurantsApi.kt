package com.nanoshkin.chibbisdemo.data.network.api

import com.nanoshkin.chibbisdemo.data.network.dto.restaurants.RestaurantsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantsApi {
    @GET("restaurants")
    suspend fun getRestaurants(): Response<RestaurantsResponse>
}