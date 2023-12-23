package com.example.marsphotosstarter

import android.app.Application
import com.example.marsphotosstarter.data.AppContainer
import com.example.marsphotosstarter.data.DefaultAppContainer

class MarsPhotoApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }

}