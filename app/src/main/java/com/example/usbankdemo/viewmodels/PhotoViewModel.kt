package com.example.usbankdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.usbankdemo.model.Photo
import com.example.usbankdemo.repository.Repository

class PhotoViewModel(repo: Repository) : ViewModel() {
    // TODO: Implement the ViewModel
    private var repository: Repository = repo

    private val photos = repository.getPhoto()

    fun getAllPhoto(): LiveData<List<Photo>> {
        return photos
    }
}
