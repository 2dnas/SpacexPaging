package com.example.shiptracker.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shiptracker.api.ApiService
import com.example.shiptracker.api.ApiUtils
import com.example.shiptracker.models.ShipModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DataSource(val api: ApiService) : PagingSource<Int,ShipModel>() {
    override fun getRefreshKey(state: PagingState<Int, ShipModel>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShipModel> {
        return try {
            var offset = params.key ?: 0
            val response = api.getShips(offset = offset)
            offset += 10
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = null,
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }





}


