package com.example.coroutine

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServiceManager {
    private val okHttpClient: OkHttpClient
    private val retrofit: Retrofit

    private const val DEFAULT_CONNECT_TIME = 10
    private const val DEFAULT_WRITE_TIME = 30
    private const val DEFAULT_READ_TIME = 30
    private const val REQUEST_PATH = "https://www.wanandroid.com/"

    init {
        okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(DEFAULT_CONNECT_TIME.toLong(), TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIME.toLong(), TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIME.toLong(), TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(REQUEST_PATH)
            .addConverterFactory(GsonConverterFactory.create()) //添加转化库，默认是Gson
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}