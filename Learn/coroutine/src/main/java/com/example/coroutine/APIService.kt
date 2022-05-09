package com.example.coroutine

import retrofit2.http.GET

interface APIService {
    @GET("banner/json")
    suspend fun getFindData(): BaseResponse<List<DataBean>>
}

