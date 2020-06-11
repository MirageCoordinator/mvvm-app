package ru.dellirium.mvvmapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val counter: MutableLiveData<Int?> = MutableLiveData()

    init {
        counter.value = 0
    }

    fun valueCounter(): LiveData<Int?> = counter

    fun onClick() {
        counter.value = counter.value?.plus(1)
    }

}