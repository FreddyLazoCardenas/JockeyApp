package com.example.jockeyapp.features.main.view

import com.example.jockeyapp.R
import com.example.jockeyapp.features.BaseActivity
import com.example.jockeyapp.features.BaseViewModel
import com.example.jockeyapp.features.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel(clazz = MainViewModel::class)

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun setUpView() {

    }
}
