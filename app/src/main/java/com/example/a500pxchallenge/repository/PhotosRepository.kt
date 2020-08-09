package com.example.a500pxchallenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a500pxchallenge.api.PhotosApi
import com.example.a500pxchallenge.config.ApiConfig
import com.example.a500pxchallenge.model.PhotosResponse
import io.github.cdimascio.dotenv.dotenv
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
 * Represents a repository that makes synchronous consuming of 500px remote api.
 * Updates the LiveData object with the api response,
 * and provides a method to get the updated LiveData<PhotosResponse> result.
 */
class PhotosRepository {

    companion object {
        const val FEATURE_PARAM = "popular"
    }

    private val photosResponseLiveData: MutableLiveData<PhotosResponse> = MutableLiveData()

    // configure retrofit with the BASE_URL and default okhttp client + GSON converter
    private val photosApi: PhotosApi = Retrofit.Builder()
        .baseUrl(ApiConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PhotosApi::class.java)

    /*
     * - Retrieves CONSUMER_KEY from /assets/env file
     * - Calls the api in synchronous and updates the LiveData object value (photosResponseLiveData) on response
     *
     * @param feature: the feature been passed to the api, ex "popular"
     */
    fun browsePhotos(feature: String?) {
        // configure dotenv to retrieve CONSUMER_KEY
        val dotenv = dotenv {
            directory = "/assets"
            filename = "env"
        }

        val call = photosApi.getPhotos(feature, dotenv["CONSUMER_KEY"])
        call?.enqueue(object : Callback<PhotosResponse?> {
            override fun onResponse(call: Call<PhotosResponse?>, response: Response<PhotosResponse?>) {
                if (response.isSuccessful) {
                    photosResponseLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<PhotosResponse?>, t: Throwable) {
                photosResponseLiveData.value = null
            }
        })
    }

    /*
     * @return the updated LiveData response
     */
    fun getPhotosResponseLiveData(): LiveData<PhotosResponse> {
        return photosResponseLiveData
    }
}