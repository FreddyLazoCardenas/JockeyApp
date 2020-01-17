package com.example.domain.repository.restApi

import com.example.entity.Movie

interface AppRestApiRepository {

    @Throws(Exception::class)
    fun getMovies(): List<Movie>
}