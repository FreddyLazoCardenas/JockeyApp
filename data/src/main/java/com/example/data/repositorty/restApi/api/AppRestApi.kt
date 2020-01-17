package com.example.data.repositorty.restApi.api

import com.example.data.BuildConfig
import com.example.data.exception.NonException
import com.example.data.repositorty.restApi.util.YoyResponse
import com.example.data.repositorty.restApi.YoyRestApi
import com.example.data.repositorty.restApi.response.MovieResponse
import com.example.domain.repository.restApi.AppRestApiRepository
import com.example.entity.Movie

class AppRestApi(private val api: YoyRestApi) : AppRestApiRepository {

    @Throws(Exception::class)
    override fun getMovies(): List<Movie> {
        val call = api.getMovies(BuildConfig.YOY_API_KEY)
        val response = YoyResponse(call.execute())
        if (response.exception !is NonException) {
            throw response.exception
        }
        return MovieResponse.toMovies(response.response!!.results)
    }

}