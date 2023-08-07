package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class PaymentData(
    @Json(name = "valid") val status: String?
)
