package com.nanoshkin.chibbisdemo.ui.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanoshkin.chibbisdemo.data.model.Hit
import com.nanoshkin.chibbisdemo.data.model.Review
import com.nanoshkin.chibbisdemo.data.repository.reviews.ReviewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val reviewsRepository: ReviewsRepository
) : ViewModel() {

    private val cash = mutableListOf<Review>()

    private val _dataReviews = MutableSharedFlow<List<Review>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val dataReviews: SharedFlow<List<Review>> = _dataReviews.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            updateData()
        }
    }

    private suspend fun updateData() {
        val data = reviewsRepository.getReviews()
        cash.clear()
        cash.addAll(data)
        _dataReviews.emit(cash)
    }
}