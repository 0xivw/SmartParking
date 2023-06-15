package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class SignUpData(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
    @Json(name = "role") val role: String
)
