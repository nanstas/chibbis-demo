package com.nanoshkin.chibbisdemo.ui.restaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanoshkin.chibbisdemo.data.model.Restaurant
import com.nanoshkin.chibbisdemo.data.repository.restaurants.RestaurantsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) : ViewModel() {

    private val cash = mutableListOf<Restaurant>()

    private val _dataRestaurants = MutableSharedFlow<List<Restaurant>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val dataRestaurants: SharedFlow<List<Restaurant>> = _dataRestaurants.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            updateData()
        }
    }

    suspend fun search(text: String) {
        val list = cash.filter { it.name.lowercase().contains(text) }
        _dataRestaurants.emit(list)
    }

    suspend fun updateData() {
        val data = restaurantsRepository.getRestaurants()
        cash.clear()
        cash.addAll(data)
        _dataRestaurants.emit(cash)
    }
}