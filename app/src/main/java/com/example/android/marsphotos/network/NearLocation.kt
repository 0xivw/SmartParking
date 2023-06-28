package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class NearLocation(
    @Json(name = "parking_name") val name: String,
    @Json(name = "parking_address") val address: String,
    @Json(name = "capacity_car") val avalCarPosition: Int,
    @Json(name = "capacity_motor") val avalMotorPosition: Int,
    @Json(name = "longtitude") val longtitude: Double,
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "distance") val distance: Double
)
