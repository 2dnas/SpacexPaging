package com.example.shiptracker.api

import com.example.shiptracker.models.ShipModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("ships")
    suspend fun getShips(@Query("offset")offset : Int) : List<ShipModel>



}