package com.example.entity

data class Movie(
    var id: Int = 0,
    var voteAverage: Double = 0.0,
    var title: String = "",
    var overview: String = "",
    var posterPath: String = "",
    var releaseDate: String = ""
) {
}