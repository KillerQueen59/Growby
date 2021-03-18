package com.example.growby

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OrderViewModelFactory(private val price:Int , private val count: Int):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)){
            return OrderViewModel(price,count) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}