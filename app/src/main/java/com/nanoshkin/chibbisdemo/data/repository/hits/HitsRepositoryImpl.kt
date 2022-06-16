package com.nanoshkin.chibbisdemo.data.repository.hits

import com.nanoshkin.chibbisdemo.data.exeptions.ApiException
import com.nanoshkin.chibbisdemo.data.exeptions.ServerException
import com.nanoshkin.chibbisdemo.data.exeptions.UnknownException
import com.nanoshkin.chibbisdemo.data.model.Hit
import com.nanoshkin.chibbisdemo.data.network.api.HitsApi
import com.nanoshkin.chibbisdemo.data.network.mapers.toHit
import java.io.IOException
import javax.inject.Inject

class HitsRepositoryImpl @Inject constructor(
    private val hitsApi: HitsApi
) : HitsRepository {
    override suspend fun getHits(): List<Hit> {
        try {
            val response = hitsApi.getHits()
            return response.body()?.map { it.toHit() } ?: throw ApiException(
                response.code(),
                response.message()
            )
        } catch (e: IOException) {
            throw ServerException
        } catch (e: Exception) {
            throw UnknownException
        }
    }
}