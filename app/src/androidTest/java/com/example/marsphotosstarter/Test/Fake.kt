package com.example.marsphotosstarter.Test

import com.example.marsphotosstarter.network.MarsPhoto


object FakeDataSource {

    const val idOne = "img1"
    const val idTwo = "img2"
    const val imgOne = "url.1"
    const val imgTwo = "url.2"
    val photosList = listOf(
        MarsPhoto(
            id = idOne,
            img_src = imgOne
        ),
        MarsPhoto(
            id = idTwo,
            img_src = imgTwo
        )
    )
}