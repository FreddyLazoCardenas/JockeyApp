package com.example.data.repositorty.restApi.response

import com.google.gson.annotations.SerializedName

class MoviesResponse(
    @SerializedName("page")
    var page:Int,
    @SerializedName("results")
    var results :List<MovieResponse> = arrayListOf()
) {
    fun isNullOrEmpty() : Boolean { return results.isNullOrEmpty() }
}