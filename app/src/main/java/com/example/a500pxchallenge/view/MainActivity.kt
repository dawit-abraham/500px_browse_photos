package com.example.a500pxchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a500pxchallenge.R
import com.example.a500pxchallenge.model.PhotosResponse
import com.example.a500pxchallenge.repository.PhotosRepository
import com.example.a500pxchallenge.viewmodel.PhotosViewModel

/*
 * An empty activity just to test our MVVM architecture + repository + LiveData object (check if onChanged() is triggered)
 */
class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "Debug LiveData Value"
        const val SUCCESS_RESULT = "LiveData value updated"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)

        viewModel.getPhotosResponseLiveData().observe(this, Observer<PhotosResponse?>{
            photosResponse ->
            if(photosResponse != null) {
                Log.d(TAG, SUCCESS_RESULT)
            }
        })

        viewModel.browsePhotos(PhotosRepository.FEATURE_PARAM)
    }
}