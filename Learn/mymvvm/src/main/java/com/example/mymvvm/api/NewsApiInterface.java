package com.example.mymvvm.api;

import androidx.lifecycle.LiveData;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {

    @GET("release/news")
    LiveData<NewsListBean> getNewsList(@Query("channelId") String channelId,
                                       @Query("channelName") String channelName,
                                       @Query("page") String page);

    @GET("release/channel")
    LiveData<NewsChannelsBean> getNewsChannels();
}
