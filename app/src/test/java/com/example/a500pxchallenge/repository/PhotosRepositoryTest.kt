package com.example.a500pxchallenge.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.a500pxchallenge.api.PhotosApi
import com.example.a500pxchallenge.model.PhotosResponse
import com.example.a500pxchallenge.testutil.getCorrectResultSample
import com.example.a500pxchallenge.testutil.getExpectedSuccessLiveDataResponse
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/*
 * Test objectives:
 * 1) Retrofit was correctly configured with okHttp, both success and failure scenarios were considered
 * 2) Response is correctly parsed to PhotoResponse object, and LiveData object has the correct value
 *
 * Note: We are not testing consuming actual 500px api,
 * we are using MockWebServer from okHttp to mock test url and responses
 *
 * A correct response sample was copied from 500px documentation for mocking
 */
class PhotosRepositoryTest {
    //ensures that test is run synchronously so that our LiveData is handled properly
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockWebServer = MockWebServer()
    private var photosApi: PhotosApi? = null
    private var call: Call<PhotosResponse>? = null
    private var apiResponse: Response<PhotosResponse?>? = null
    private val photosResponseLiveData: MutableLiveData<PhotosResponse> = MutableLiveData()

    @Before
    fun setup() {
        //initialize MockWebServer
        mockWebServer.start()
    }

    @After
    fun destroy() {
        //shut down MockWebServer
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulBrowsePhotos() {
        call = mockSuccessPhotosApi(true)?.getPhotos("any_thing", "any_key")
        call?.enqueue(object : Callback<PhotosResponse?> {
            override fun onResponse(call: Call<PhotosResponse?>, response: Response<PhotosResponse?>) {
                if (response.isSuccessful) {
                    photosResponseLiveData.value = response.body()
                }
                apiResponse = response
            }
            override fun onFailure(call: Call<PhotosResponse?>, t: Throwable) {
                photosResponseLiveData.value = null
            }
        })
        /*
         * blocking the thread for 1 second to wait callback response,
         * since the tests are synchronous
         */
        CountDownLatch(1).await(1, TimeUnit.SECONDS)

        /*
         * The below 3 tests should pass to say we made a successful api call with success response
         */

        // 1) check api response is not null, this means there was a response from the api and it didn't fail
        assertNotNull(apiResponse)

        // 2) a successful api call was made
        apiResponse?.isSuccessful?.let { assertTrue(it) }

        // 3) test the LiveData object to make sure it has the correct value
        // expected and actual values are converted to JSON, so we can easily compare values than objects
        assertTrue(Gson().toJson(photosResponseLiveData.value) == Gson().toJson(getExpectedSuccessLiveDataResponse().value))
    }

    @Test
    fun testFailureBrowsePhotos() {
        call = mockSuccessPhotosApi(false)?.getPhotos("any_thing", "any_key")
        call?.enqueue(object : Callback<PhotosResponse?> {
            override fun onResponse(call: Call<PhotosResponse?>, response: Response<PhotosResponse?>) {
                if (response.isSuccessful) {
                    photosResponseLiveData.value = response.body()
                }
                apiResponse = response
            }
            override fun onFailure(call: Call<PhotosResponse?>, t: Throwable) {
                photosResponseLiveData.value = null
            }
        })
        /*
         * blocking the thread for 1 second to wait callback response,
         * since the tests are synchronous
         */
        CountDownLatch(1).await(1, TimeUnit.SECONDS)

        // assert the callback response and the LiveData value are null
        assertNull(apiResponse?.body())
        assertNull(photosResponseLiveData.value)
    }

    private fun mockSuccessPhotosApi(success: Boolean): PhotosApi? {
        val mockResponse = when(success) {
            true -> MockResponse().setBody(getCorrectResultSample()).setResponseCode(200)
            false -> MockResponse().setResponseCode(404)
        }

        mockWebServer.enqueue(mockResponse)

        //we should build retrofit with the same gson converter and client as the actual implementation
        //replace our baseUrl with MockWebServer's url, because we are not here trying to test the remote api itself
        photosApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotosApi::class.java)

        return photosApi
    }
}