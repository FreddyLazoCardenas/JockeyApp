package com.example.jockeyapp.features.main.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.GetMovieUseCase
import com.example.entity.Movie
import com.example.jockeyapp.features.BaseViewModel

class MainViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    context: Context
) : BaseViewModel(context) {

    var movieMutable = MutableLiveData<List<Movie>>()
    fun getMovies() {
        execute(getMovieUseCase, GetMoviesObservable(movieMutable))
    }
}

class GetMoviesObservable(private val movieMutable: MutableLiveData<List<Movie>>) :
    BaseViewModel.BaseObserver<List<Movie>>() {
    override fun onChanged(t: List<Movie>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(e: Throwable) {
        super.onError(e)
    }

    override fun onNext(t: List<Movie>) {
        super.onNext(t)
        movieMutable.postValue(t)
    }

}