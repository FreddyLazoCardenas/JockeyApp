package com.example.data.repositorty.restApi

import com.example.data.repositorty.restApi.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoyRestApi {

    // app
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String): Call<MoviesResponse>
}