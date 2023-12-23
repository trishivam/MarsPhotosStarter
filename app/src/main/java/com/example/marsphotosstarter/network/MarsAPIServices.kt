package com.example.marsphotosstarter.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import kotlin.reflect.KProperty


//private val BASE_URL =
//    "https://android-kotlin-fun-mars-server.appspot.com"
//
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//    .baseUrl(BASE_URL)
//    .build()

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
//
//object MarsApi {
//    val retrofitService : MarsApiService by lazy {
//        retrofit.create(MarsApiService::class.java)
//    }
//}
//
//
