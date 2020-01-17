package com.example.jockeyapp

import android.app.Application
import com.example.jockeyapp.modules.applicationModule
import com.example.jockeyapp.modules.restApiModule
import com.example.jockeyapp.modules.useCaseModule
import com.example.jockeyapp.utils.BASE_URL
import com.example.jockeyapp.utils.IMAGE_PREFIX
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YoyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@YoyApplication)
            modules(listOf(applicationModule, useCaseModule, restApiModule))
        }
        getKoin().setProperty(BASE_URL, BuildConfig.YOY_BASE_URL)
        getKoin().setProperty(IMAGE_PREFIX, BuildConfig.YOY_PREFIX_IMAGE)
    }
}