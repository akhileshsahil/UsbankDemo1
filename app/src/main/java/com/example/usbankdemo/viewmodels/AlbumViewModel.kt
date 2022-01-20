package com.example.usbankdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.usbankdemo.model.Album
import com.example.usbankdemo.repository.Repository

class AlbumViewModel(repo: Repository) : ViewModel() {
    // TODO: Implement the ViewModel
    private var repository: Repository = repo

    public var album = repository.getAlbum()

    fun getAlbum(): LiveData<List<Album>> {
        return album
    }
}
