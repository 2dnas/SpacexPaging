package com.example.shiptracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shiptracker.adapter.PagingShipAdapter
import com.example.shiptracker.api.ApiUtils
import com.example.shiptracker.databinding.ActivityMainBinding
import com.example.shiptracker.viewmodel.ShipViewModel
import com.example.shiptracker.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val SECOND = 1000L
class MainActivity : AppCompatActivity() {
    private var coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    private lateinit var binding : ActivityMainBinding
    private var shipViewModel : ShipViewModel? = null
    private var adapter = PagingShipAdapter()
    private lateinit var recyclerView : RecyclerView
    private var notTouched = true
    private val snapHelper = PagerSnapHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        val factory = ViewModelFactory(ApiUtils.apiService!!)
        shipViewModel = ViewModelProvider(this,factory).get(ShipViewModel::class.java)



        var position = 0
        coroutineScope.launch {
            while(notTouched){
                position++
                delay(5* SECOND)
                recyclerView.smoothScrollToPosition(position)
            }
        }

        lifecycleScope.launch {
            shipViewModel?.ships?.collectLatest {
                adapter.submitData(it)
            }
        }
    }






    private fun initRecyclerView() {
        recyclerView = binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
            adapter = this@MainActivity.adapter
            snapHelper.attachToRecyclerView(this)
        }
    }
}