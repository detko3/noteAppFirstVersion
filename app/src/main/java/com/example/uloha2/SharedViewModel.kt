package com.example.uloha2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class SharedViewModel : ViewModel() {
    val message = MutableLiveData<String>()

    fun sendMessage(data: String) {
        message.value = data
    }
}