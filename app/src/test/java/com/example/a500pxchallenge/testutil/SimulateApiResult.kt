package com.example.a500pxchallenge.testutil

import androidx.lifecycle.MutableLiveData
import com.example.a500pxchallenge.model.PhotosResponse
import com.google.gson.Gson

/*
 * This sample json string was copied from the 500px api documentation
 * and is considered a correct response sample
 * @return a string with json format
 */
fun getCorrectResultSample(): String {
    return "{\n" +
            "  \"feature\": \"popular\",\n" +
            "  \"filters\": {\n" +
            "      \"category\": false,\n" +
            "      \"exclude\": false\n" +
            "  },\n" +
            "  \"current_page\": 1,\n" +
            "  \"total_pages\": 250,\n" +
            "  \"total_items\": 5000,\n" +
            "  \"photos\": [\n" +
            "    {\n" +
            "      \"id\": 4910421,\n" +
            "      \"name\": \"Orange or lemon\",\n" +
            "      \"description\": \"\",\n" +
            "      \"times_viewed\": 709,\n" +
            "      \"rating\": 97.4,\n" +
            "      \"created_at\": \"2012-02-09T02:27:16-05:00\",\n" +
            "      \"category\": 0,\n" +
            "      \"privacy\": false,\n" +
            "      \"width\": 472,\n" +
            "      \"height\": 709,\n" +
            "      \"votes_count\": 88,\n" +
            "      \"comments_count\": 58,\n" +
            "      \"nsfw\": false,\n" +
            "      \"image_url\": \"http://pcdn.500px.net/4910421/c4a10b46e857e33ed2df35749858a7e45690dae7/2.jpg\",\n" +
            "      \"user\": {\n" +
            "        \"id\": 386047,\n" +
            "        \"username\": \"Lluisdeharo\",\n" +
            "        \"firstname\": \"Lluis \",\n" +
            "        \"lastname\": \"de Haro Sanchez\",\n" +
            "        \"city\": \"Sabadell\",\n" +
            "        \"country\": \"Catalunya\",\n" +
            "        \"fullname\": \"Lluis de Haro Sanchez\",\n" +
            "        \"userpic_url\": \"http://acdn.500px.net/386047/f76ed05530afec6d1d0bd985b98a91ce0ce49049/1.jpg?0\",\n" +
            "        \"upgrade_status\": 0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 4905955,\n" +
            "      \"name\": \"R E S I G N E D\",\n" +
            "      \"description\": \"From the past of Tagus River, we have History and memories, some of them abandoned and disclaimed in their margins ...\",\n" +
            "      \"times_viewed\": 842,\n" +
            "      \"rating\": 97.4,\n" +
            "      \"created_at\": \"2012-02-08T19:00:13-05:00\",\n" +
            "      \"category\": 0,\n" +
            "      \"privacy\": false,\n" +
            "      \"width\": 750,\n" +
            "      \"height\": 500,\n" +
            "      \"votes_count\": 69,\n" +
            "      \"comments_count\": 29,\n" +
            "      \"nsfw\": false,\n" +
            "      \"image_url\": \"http://pcdn.500px.net/4905955/7e1a6be3d8319b3b7357c6390289b20c16a26111/2.jpg\",\n" +
            "      \"user\": {\n" +
            "        \"id\": 350662,\n" +
            "        \"username\": \"cresendephotography\",\n" +
            "        \"firstname\": \"Carlos\",\n" +
            "        \"lastname\": \"Resende\",\n" +
            "        \"city\": \"Forte da Casa\",\n" +
            "        \"country\": \"Portugal\",\n" +
            "        \"fullname\": \"Carlos Resende\",\n" +
            "        \"userpic_url\": \"http://acdn.500px.net/350662.jpg\",\n" +
            "        \"upgrade_status\": 0\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}

/*
 * @return: a LiveData object from a correct response sample json string
 */
fun getExpectedSuccessLiveDataResponse(): MutableLiveData<PhotosResponse> {
    return MutableLiveData(Gson().fromJson(getCorrectResultSample(), PhotosResponse::class.java))
}