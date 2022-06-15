package com.nanoshkin.chibbisdemo.data.network.api

import com.nanoshkin.chibbisdemo.data.network.dto.hits.HitsResponse
import retrofit2.Response
import retrofit2.http.GET

interface HitsApi {
    @GET("hits")
    suspend fun getHits(): Response<HitsResponse>
}