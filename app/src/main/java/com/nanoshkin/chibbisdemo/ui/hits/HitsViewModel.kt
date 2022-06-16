package com.nanoshkin.chibbisdemo.ui.hits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanoshkin.chibbisdemo.data.model.Hit
import com.nanoshkin.chibbisdemo.data.model.Restaurant
import com.nanoshkin.chibbisdemo.data.repository.hits.HitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HitsViewModel @Inject constructor(
    private val hitsRepository: HitsRepository
) : ViewModel() {

    private val cash = mutableListOf<Hit>()

    private val _dataHits = MutableSharedFlow<List<Hit>>()
    val dataHits: SharedFlow<List<Hit>> = _dataHits.asSharedFlow()

    init {
        viewModelScope.launch {
            updateData()
        }
    }

    private suspend fun updateData() {
        val data = hitsRepository.getHits()
        cash.clear()
        cash.addAll(data)
        _dataHits.emit(cash)
    }
}