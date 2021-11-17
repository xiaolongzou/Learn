package com.example.mymvvm.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mymvvm.api.NewsChannelsBean;
import com.example.mymvvm.newslist.NewListFragment;

import java.util.List;

public class HomeFragmentAdapter extends FragmentPagerAdapter {

    private List<NewsChannelsBean.ChannelList>  mChannels;

    public HomeFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setChannels(List<NewsChannelsBean.ChannelList> channels) {
        mChannels = channels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NewListFragment.newInstance(mChannels.get(position).channelId, mChannels.get(position).channelName);
    }

    @Override
    public int getCount() {
        return mChannels == null ? 0 : mChannels.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).name;
    }
}
