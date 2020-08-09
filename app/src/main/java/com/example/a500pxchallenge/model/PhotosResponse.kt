package com.example.a500pxchallenge.model

import com.google.gson.annotations.SerializedName

/*
 * Represents the api response with list of photos
 */
class PhotosResponse {
    @SerializedName("current_page")
    var currentPage: String? = null

    @SerializedName("total_pages")
    var totalPages: String? = null

    @SerializedName("total_items")
    var totalItems: String? = null

    var feature: String? = null

    var photos: List<Photo>? = null
}