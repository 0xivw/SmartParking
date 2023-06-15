package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class JwtData(
    @Json(name = "jwt") val token: String)
