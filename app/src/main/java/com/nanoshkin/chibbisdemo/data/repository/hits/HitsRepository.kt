package com.nanoshkin.chibbisdemo.data.repository.hits

import com.nanoshkin.chibbisdemo.data.model.Hit

interface HitsRepository {
    suspend fun getHits(): List<Hit>
}