package com.example.a500pxchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.a500pxchallenge.model.PhotosResponse
import com.example.a500pxchallenge.repository.PhotosRepository

class PhotosViewModel: ViewModel() {
    private val photosRepository: PhotosRepository = PhotosRepository()
    private val photosResponseLiveData: LiveData<PhotosResponse> = photosRepository.getPhotosResponseLiveData()

    fun browsePhotos(feature: String) {
        photosRepository.browsePhotos(feature)
    }

    fun getPhotosResponseLiveData(): LiveData<PhotosResponse> {
        return photosResponseLiveData
    }
}