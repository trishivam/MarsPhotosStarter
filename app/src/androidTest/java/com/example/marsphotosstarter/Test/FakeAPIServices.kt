package com.example.marsphotosstarter.Test

import com.example.marsphotosstarter.network.MarsApiService
import com.example.marsphotosstarter.network.MarsPhoto

class FakeAPIServices: MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}