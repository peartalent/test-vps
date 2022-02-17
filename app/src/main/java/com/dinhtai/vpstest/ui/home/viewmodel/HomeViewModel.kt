package com.dinhtai.vpstest.ui.home.viewmodel

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dinhtai.vpstest.data.Item

class HomeViewModel : ViewModel() {
    companion object {
        private lateinit var instance: HomeViewModel

        @MainThread
        fun getInstance(): HomeViewModel {
            instance = if (::instance.isInitialized) instance else HomeViewModel()
            return instance
        }
    }

    private val _products = MutableLiveData<List<Item>>()
    val products: LiveData<List<Item>>
        get() = _products

    private val _banners = MutableLiveData<List<Item>>()
    val banners: LiveData<List<Item>>
        get() = _banners

    private val _services = MutableLiveData<List<Item>>()
    val services: LiveData<List<Item>>
        get() = _services

    private val _isEditing = MutableLiveData<Boolean>(false)
    val isEditing: LiveData<Boolean>
        get() = _isEditing

    fun setProducts(data: List<Item>) {
        _products.value = data
    }

    fun setBanners(data: List<Item>) {
        _banners.value = data
    }

    fun setServices(data: List<Item>) {
        _services.value = data
    }

    fun setEditing(data: Boolean) {
        _isEditing.value = data
    }

}