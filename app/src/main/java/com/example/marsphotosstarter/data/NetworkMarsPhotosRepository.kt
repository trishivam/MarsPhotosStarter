package com.example.marsphotosstarter.data

import com.example.marsphotosstarter.network.MarsApiService
import com.example.marsphotosstarter.network.MarsPhoto

interface MarsPhotosRepository  {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository (private val marsApiService: MarsApiService): MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto>  = marsApiService.getPhotos()
}