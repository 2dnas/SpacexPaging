package com.example.shiptracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shiptracker.R
import com.example.shiptracker.databinding.ShipListItemBinding
import com.example.shiptracker.models.ShipModel

class PagingShipAdapter() : PagingDataAdapter<ShipModel,PagingShipAdapter.ShipViewHolder>(REPO_COMPARATOR) {



    inner class ShipViewHolder(val binding: ShipListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ShipModel) = with(binding) {
            Glide.with(itemView)
                .load(item.shipImage)
                .centerCrop()
                .placeholder(R.drawable.placeholder_elon)
                .into(binding.shipImage)

            shipName.text = item.shipName
            shipType.text = item.shipType
            homePort.text = item.homePort
        }
    }




    override fun onBindViewHolder(holder: ShipViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipViewHolder {
        return ShipViewHolder(ShipListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }





    object REPO_COMPARATOR : DiffUtil.ItemCallback<ShipModel>(){
        override fun areItemsTheSame(oldItem: ShipModel, newItem: ShipModel): Boolean {
            return oldItem.shipId == newItem.shipId
        }

        override fun areContentsTheSame(oldItem: ShipModel, newItem: ShipModel): Boolean {
            return oldItem == newItem
        }

    }
}