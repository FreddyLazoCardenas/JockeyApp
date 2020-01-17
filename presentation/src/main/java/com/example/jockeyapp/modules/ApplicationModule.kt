package com.example.jockeyapp.modules

import com.example.jockeyapp.features.main.viewmodel.MainViewModel
import com.example.jockeyapp.navigator.Navigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { provideNavigator() }
    viewModel { MainViewModel(get(), get()) }
}

fun provideNavigator(): Navigator {
    return Navigator()
}