package com.example.mymvvm.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymvvm.api.ApiService;
import com.example.mymvvm.api.NewsApiInterface;
import com.example.mymvvm.api.NewsChannelsBean;

public class HomeViewModel extends ViewModel {

    public LiveData<NewsChannelsBean> getNewsChannels() {
        return ApiService.create(NewsApiInterface.class).getNewsChannels();
    }
}
