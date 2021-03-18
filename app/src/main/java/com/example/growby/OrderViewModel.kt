package com.example.growby

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel(price:Int,count: Int) :ViewModel() {
    private var total = MutableLiveData<Int>()
    val totalPrice: LiveData<Int>
    get() = total
    private var _count = MutableLiveData<Int>()
    val totalCount: LiveData<Int>
    get() = _count
    private var fixePrice = MutableLiveData<Int>()

    init {
        total.value = price
        fixePrice.value = price
        _count.value = count
    }

    fun sub(){
        _count.value = (_count.value)?.minus(1)
        total.value =  fixePrice.value!! * _count.value!!
    }
    fun add(){
        _count.value = (_count.value)?.plus(1)
        total.value =  fixePrice.value!! * _count.value!!
    }


}