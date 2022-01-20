package com.example.usbankdemo.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.usbankdemo.api.Api
import com.example.usbankdemo.model.Album
import com.example.usbankdemo.model.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(api: Api, context: Context) {

    val api:Api
    val context:Context
    internal var albumResult = MutableLiveData<List<Album>>()
    internal var photoResult = MutableLiveData<List<Photo>>()

    init {
        this@Repository.api = api
        this@Repository.context = context
    }

    fun getAlbum(): MutableLiveData<List<Album>> {

        api.getAlbum().enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                println("Response = " + response.body()!!)
                if (response.isSuccessful()) {
                    albumResult.setValue(response.body())
                }

            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                println("failure $t")
                albumResult.setValue(null)
            }
        })
        return albumResult
    }

    fun getPhoto(): MutableLiveData<List<Photo>> {

        api.getPhoto().enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                println("Response = " + response.body()!!)
                if (response.isSuccessful()) {
                    photoResult.setValue(response.body())
                }

            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                println("failure $t")
                photoResult.setValue(null)
            }
        })
        return photoResult
    }

}