package com.nanoshkin.chibbisdemo.ui.hits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HitsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is hits Fragment"
    }
    val text: LiveData<String> = _text
}