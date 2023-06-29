package com.example.android.marsphotos.network

import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Json

data class TicketData(
    @Json(name = "id_card") val idCard: String?,
    @Json(name = "license_vehicle") val license: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "username") val username: String?,
    @Json(name = "parking_name") val parkingName: String?,
    @Json(name = "time") val time: String?,
    @Json(name = "cost") val cost: Long?
)
