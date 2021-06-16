package com.example.shiptracker.api

import retrofit2.Retrofit

object ApiUtils {
        private const val BASE_URL = "https://api.spacexdata.com/v3/"

    val apiService : ApiService?
    get() = RetrofitClient.getClient(BASE_URL)?.create(ApiService::class.java)


}