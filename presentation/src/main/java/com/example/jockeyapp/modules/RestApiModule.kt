package com.example.jockeyapp.modules

import com.example.data.repositorty.restApi.YoyRestApi
import com.example.data.repositorty.restApi.api.AppRestApi
import com.example.domain.repository.restApi.AppRestApiRepository
import com.example.jockeyapp.BuildConfig
import com.example.jockeyapp.utils.BASE_URL
import com.example.jockeyapp.utils.TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val restApiModule = module {
    single { providerHttpLoggingInterceptor() }
    single { providerOkHttpClient(get()) }
    single { providerRetrofit(getProperty(BASE_URL), get()) }
    single { providerApi(get()) }
    single<AppRestApiRepository> { AppRestApi(get()) }

}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

fun providerOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val client = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
    client.addInterceptor(httpLoggingInterceptor)
    return client.build()
}

fun providerRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build()
}

fun providerApi(retrofit: Retrofit): YoyRestApi {
    return retrofit.create(YoyRestApi::class.java)
}
