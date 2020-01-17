package com.example.jockeyapp.modules

import com.example.domain.usecase.GetMovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMovieUseCase(get()) }

}