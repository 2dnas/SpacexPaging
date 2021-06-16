package com.example.shiptracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.shiptracker.api.ApiService
import com.example.shiptracker.paging.DataSource

class ShipViewModel(api : ApiService) : ViewModel() {

    val ships = Pager(PagingConfig(pageSize = 10)){
        DataSource(api)
    }.flow.cachedIn(viewModelScope)
}