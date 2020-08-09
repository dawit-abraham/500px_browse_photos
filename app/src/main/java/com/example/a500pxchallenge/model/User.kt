package com.example.a500pxchallenge.model

import com.google.gson.annotations.SerializedName
import java.util.*

/*
 * Represents the Author of a Photo
 */
class User {
    var id: Int? = 0

    @SerializedName("username")
    var userName: String? = null

    @SerializedName("fullname")
    var fullName: String? = null

    @SerializedName("registration_date")
    var regDate: Date? = null

    @SerializedName("userpic_url")
    var userPicUrl: String? = null
}