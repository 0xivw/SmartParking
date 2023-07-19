/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.network

import com.example.android.marsphotos.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.net.Authenticator

private const val BASE_URL = "https://a0c7-2402-800-61cb-9fb3-785e-a969-8206-241b.ngrok-free.app/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val client = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request: Request = chain.request().newBuilder()
            .addHeader("Authorization", "${Constant.JWT_TOKEN}")
            .build()
        chain.proceed(request)
    }
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

/**
 * A public interface that exposes the [getPhotos] method
 */
interface MarsApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("get-all-parking")
    suspend fun getPhotos(): List<MarsPhoto>

    @GET("get-sort-distance")
    suspend fun getNearLocation(
        @Query("longtitude") longtitude: Double?,
        @Query("latitude") latitude: Double?
    ): List<MarsPhoto>

    @FormUrlEncoded
    @POST("login-user?")
    suspend fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Response<JwtData>

    @FormUrlEncoded
    @POST("signup-user?")
    suspend fun signUp(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Response<SignUpData>

    @POST("post_month_ticket")
    suspend fun addTicket(@Body requestBody: TicketData): Response<TicketData>


    @GET("get_month_ticket")
    suspend fun getTicket(@Query("username") username: String?): List<TicketData>

    @GET("check_month_ticket_user?")
    suspend fun getCost(@Body requestBody: TicketData): Response<TicketData>

    @GET("find_ticket")
    suspend fun findTicket(@Query("id_card") id: String?): Response<TicketData>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
