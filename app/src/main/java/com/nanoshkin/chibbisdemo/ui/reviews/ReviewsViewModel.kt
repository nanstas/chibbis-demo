package com.nanoshkin.chibbisdemo.ui.reviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReviewsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reviews Fragment"
    }
    val text: LiveData<String> = _text
}