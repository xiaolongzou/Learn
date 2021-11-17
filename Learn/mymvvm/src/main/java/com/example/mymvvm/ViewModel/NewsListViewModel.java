package com.example.mymvvm.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymvvm.api.ApiService;
import com.example.mymvvm.api.NewsApiInterface;
import com.example.mymvvm.api.NewsListBean;

public class NewsListViewModel extends ViewModel {

    public LiveData<NewsListBean> getNewsList(String channelId,
                                              String channelName,
                                              String page) {
        return ApiService.create(NewsApiInterface.class)
                .getNewsList(channelId, channelName, page);
    }
}
