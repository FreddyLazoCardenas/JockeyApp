package com.example.domain.usecase

import com.example.domain.repository.restApi.AppRestApiRepository
import com.example.entity.Movie


class GetMovieUseCase(
    private val appNetworkRestApiRepository: AppRestApiRepository): BaseUseCase<List<Movie>>() {
    @Throws(Exception::class)
    override fun execute(): List<Movie> {
        return appNetworkRestApiRepository.getMovies()
    }

}