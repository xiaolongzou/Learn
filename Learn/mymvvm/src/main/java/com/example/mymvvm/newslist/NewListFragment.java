package com.example.mymvvm.newslist;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mymvvm.R;
import com.example.mymvvm.ViewModel.NewsListViewModel;
import com.example.mymvvm.ViewModel.NewsListViewModelFactory;
import com.example.mymvvm.api.NewsListBean;
import com.example.mymvvm.databinding.FragmentNewsBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class NewListFragment extends Fragment {

    FragmentNewsBinding fragmentNewsBinding;

    private NewsListRecycleViewAdapter newsListRecycleViewAdapter;
    private NewsListViewModel newsListViewModel;

    private final static String CHANNEL_ID = "channel_id";
    private final static String CHANNEL_NAME = "channel_name";

    public static NewListFragment newInstance(String channelId, String channelName) {
        NewListFragment newsListFragment = new NewListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CHANNEL_ID, channelId);
        bundle.putString(CHANNEL_NAME, channelName);
        newsListFragment.setArguments(bundle);
        return newsListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNewsBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_news, container, false);

        newsListRecycleViewAdapter =
                new NewsListRecycleViewAdapter(getContext());

        fragmentNewsBinding.listview.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentNewsBinding.listview.setAdapter(newsListRecycleViewAdapter);
        newsListViewModel = new ViewModelProvider(this, new NewsListViewModelFactory()).get(NewsListViewModel.class);

        load();

        fragmentNewsBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        fragmentNewsBinding.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mpage = 0;
                load();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                load();
            }
        });

        return  fragmentNewsBinding.getRoot();

    }

    private  int mpage = 1;
    private List<NewsListBean.Contentist> contentists = new ArrayList<>();

    private void load() {
        newsListViewModel.getNewsList(getArguments().getString(CHANNEL_ID),
                getArguments().getString(CHANNEL_NAME),String.valueOf(mpage))
                .observe(this, new Observer<NewsListBean>() {
                    @Override
                    public void onChanged(NewsListBean newsListBean) {
                        if (mpage == 1) {
                            contentists.clear();
                        }
                        contentists.addAll(newsListBean.showapiResBody.pageBean.contentlist);
                        newsListRecycleViewAdapter.setData(contentists);
                        mpage++;
                        fragmentNewsBinding.refreshLayout.finishRefresh();
                        fragmentNewsBinding.refreshLayout.finishLoadMore();
                    }
                });
    }
}
