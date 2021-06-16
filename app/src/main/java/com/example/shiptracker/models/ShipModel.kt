package com.example.shiptracker.models

import com.google.gson.annotations.SerializedName

data class ShipModel(
    @SerializedName("ship_id")
    val shipId : String?,
    @SerializedName("ship_name")
    val shipName : String?,
    @SerializedName("ship_type")
    val shipType : String?,
    @SerializedName("home_port")
    val homePort : String?,
    @SerializedName("image")
    val shipImage : String?
)