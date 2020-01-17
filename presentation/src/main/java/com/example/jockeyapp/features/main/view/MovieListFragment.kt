package com.example.jockeyapp.features.main.view

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.entity.Movie
import com.example.jockeyapp.BuildConfig
import com.example.jockeyapp.R
import com.example.jockeyapp.features.BaseFragment
import com.example.jockeyapp.features.BaseViewModel
import com.example.jockeyapp.features.main.adapter.MoviesAdapter
import com.example.jockeyapp.features.main.viewmodel.MainViewModel
import com.example.jockeyapp.model.MovieModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : BaseFragment() {

    private val viewModel: MainViewModel by viewModel(clazz = MainViewModel::class)
    private lateinit var adapter: MoviesAdapter

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun setUpView() {
        viewModel.getMovies()
        setUpList()
    }

    private fun setUpList() {
        adapter = MoviesAdapter(BuildConfig.YOY_PREFIX_IMAGE) { clickMovie(it) }
        rv_movies.layoutManager = GridLayoutManager(activity, 2)
        rv_movies.adapter = adapter
    }

    private fun clickMovie(it: Movie) {
        val movieModel =
            MovieModel(it.id, it.voteAverage, it.title, it.overview, it.posterPath, it.releaseDate)
        val action = MovieListFragmentDirections.actionMainFragmentToMovieDetailFragment(movieModel)
        Navigation.findNavController(this.activity!!, R.id.contentFragmentProfile).navigate(action)
    }

    override fun observeViewModel() {
        viewModel.movieMutable.observe(this, Observer { movies -> bindData(movies) })
    }

    private fun bindData(movies: List<Movie>?) {
        adapter.setMovies(movies!!)
    }
}