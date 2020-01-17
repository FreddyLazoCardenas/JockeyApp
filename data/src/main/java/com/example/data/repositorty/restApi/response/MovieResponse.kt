package com.example.data.repositorty.restApi.response

import com.example.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("release_date")
    var releaseDate: String = ""
) {
    fun isNullOrEmpty(): Boolean {
        return title.isEmpty()
    }

    companion object {
        private fun toMovie(response: MovieResponse?): Movie {
            return if (response!!.isNullOrEmpty()) Movie() else Movie(
                response.id ?: 0,
                response.voteAverage ?: 0.0,
                response.title ?: "",
                response.overview ?: "",
                response.posterPath ?: "",
                response.releaseDate ?: ""
            )
        }

        fun toMovies(response: List<MovieResponse>?): List<Movie> {
            val movies = ArrayList<Movie>()
            if (!response.isNullOrEmpty()) {
                for (rpt in response) {
                    movies.add(toMovie(rpt))
                }
            }
            return movies
        }
    }
}