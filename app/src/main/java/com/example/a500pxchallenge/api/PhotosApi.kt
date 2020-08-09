package com.example.a500pxchallenge.api

import com.example.a500pxchallenge.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * An interface that provides methods for consuming 500px remote api
 */
interface PhotosApi {
    /*
     * GET HTTP call with 2 query params
     * @param feature: Query String param, ex "popular"
     * @param consumerKey: Query String param
     */
    @GET("photos/")
    fun getPhotos(
        @Query("feature") feature: String?,
        @Query("consumer_key") consumeKey: String?
    ): Call<PhotosResponse>?
}