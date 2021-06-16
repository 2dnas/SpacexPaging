package com.example.shiptracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shiptracker.api.ApiService

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val api : ApiService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShipViewModel(api) as T
    }
}