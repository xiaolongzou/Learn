package com.example.mymvvm.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymvvm.R;
import com.example.mymvvm.ViewModel.HomeViewModel;
import com.example.mymvvm.ViewModel.HomeViewModleFactory;
import com.example.mymvvm.api.NewsChannelsBean;
import com.example.mymvvm.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    FragmentHomeBinding mBinding;
    private HomeFragmentAdapter homeFragmentAdapter;
    private HomeViewModel homeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        homeFragmentAdapter = new HomeFragmentAdapter(getChildFragmentManager());
        mBinding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);  //固定/滚动
        mBinding.viewpager.setAdapter(homeFragmentAdapter); //设置viewPager
        mBinding.tablayout.setupWithViewPager(mBinding.viewpager); //绑定viewpager和tablayout
        mBinding.viewpager.setOffscreenPageLimit(1); //设置viewpager

        homeViewModel = new ViewModelProvider(this, new HomeViewModleFactory()).get(HomeViewModel.class);

        load();

        return  mBinding.getRoot();
    }

    private void load() {
        homeViewModel.getNewsChannels().observe(this, new Observer<NewsChannelsBean>() {
            @Override
            public void onChanged(NewsChannelsBean newsChannelsBean) {
                homeFragmentAdapter.setChannels(newsChannelsBean.showapiResBody.channelList);
            }
        });
    }
}
