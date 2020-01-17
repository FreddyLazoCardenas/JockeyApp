package com.example.jockeyapp.features.main.view

import androidx.navigation.fragment.navArgs
import coil.api.load
import com.example.jockeyapp.R
import com.example.jockeyapp.features.BaseFragment
import com.example.jockeyapp.features.BaseViewModel
import com.example.jockeyapp.BuildConfig
import com.example.jockeyapp.features.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment() {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MainViewModel by viewModel(clazz = MainViewModel::class)


    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun getLayout(): Int {
        return R.layout.fragment_movie_detail
    }

    override fun setUpView() {
        val model  = args.model
        title.text = model.title
        content.text = model.overview
        image.load(BuildConfig.YOY_PREFIX_IMAGE + model.posterPath)
        rating.rating = model.voteAverage.toFloat()/2
    }

    override fun observeViewModel() {
    }
}