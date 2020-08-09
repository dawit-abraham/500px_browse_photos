package com.example.a500pxchallenge.model

import com.google.gson.annotations.SerializedName
import java.util.*

/*
 * Represents a photo in a list of photos
 * There could be multiple photos in a PhotosResponse
 */
class Photo {
    var id = 0

    var name: String? = null

    var description: String? = null

    @SerializedName("created_at")
    var createdAt: Date? = null

    var rating: Double = 0.0

    @SerializedName("comments_count")
    var commentsCount: Int? = 0

    @SerializedName("positive_votes_count")
    var likesCount: Int? = 0

    @SerializedName("times_viewed")
    var timesViewed: Int? = 0

    var user: User? = null

    var images: List<Image>? = null
}