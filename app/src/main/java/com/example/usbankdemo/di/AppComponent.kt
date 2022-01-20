package com.example.usbankdemo.di

import com.example.usbankdemo.MainActivity
import com.example.usbankdemo.ui.album.AlbumFragment
import com.example.usbankdemo.ui.photo.PhotoActivity
import com.example.usbankdemo.viewmodels.AlbumViewModel
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, ApiModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(albumFragment: AlbumFragment)
    fun inject(photoActivity: PhotoActivity)


}