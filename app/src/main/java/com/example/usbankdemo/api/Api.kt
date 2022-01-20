package com.example.usbankdemo.api

import com.example.usbankdemo.model.Album
import com.example.usbankdemo.model.Photo
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("albums")
    fun getAlbum(): Call<List<Album>>

    @GET("photos")
    fun getPhoto(): Call<List<Photo>>

}

