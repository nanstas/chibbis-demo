package com.nanoshkin.chibbisdemo.data.repository.restaurants

import com.nanoshkin.chibbisdemo.data.exeptions.ApiException
import com.nanoshkin.chibbisdemo.data.exeptions.ServerException
import com.nanoshkin.chibbisdemo.data.exeptions.UnknownException
import com.nanoshkin.chibbisdemo.data.model.Restaurant
import com.nanoshkin.chibbisdemo.data.network.api.RestaurantsApi
import com.nanoshkin.chibbisdemo.data.network.mapers.toRestaurant
import java.io.IOException
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val restaurantsApi: RestaurantsApi
) : RestaurantsRepository {
    override suspend fun getRestaurants(): List<Restaurant> {
        try {
            val response = restaurantsApi.getRestaurants()
            return response.body()?.map { it.toRestaurant() } ?: throw ApiException(response.code(), response.message())
        } catch (e: IOException) {
            throw ServerException
        } catch (e: Exception) {
            throw UnknownException
        }
    }
}