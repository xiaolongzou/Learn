package com.example.myviewpager;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyAdapter extends PagerAdapter {

    private List<View> mListView;

    public MyAdapter(List<View> mListView) {  //构造函数
        this.mListView = mListView;
    }

    /*
    * 1.将给定位置的view添加到ViewGroup中，创建并显示出来
    * 2.返回一个代表新增页面的object（key），通常都是直接返回view本身就可以了，当然你也可以自定义
    * 自己的key，但是key和每个View要一一对应的关系
    * */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mListView.get(position), 0);
        return mListView.get(position);
    }

    /*
    * 获取ViewPager中有多少个view
    * */
    @Override
    public int getCount() {
        return mListView.size();
    }

    /*
    * 判断iinstantiateItem函数返回的key与一个页面视图是否是代表的同一个视图
    * （即他俩是否是相对应的，对应的表示同一个view），通常我们直接return view == onject
    * */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /*
     * 移除一个给定位置的页面。适配器有责任从容器中删除这个视图。这是为了确保在
     * finishUpdate（ViewGroup）返回时视图能够被移除。
     * 而另外2个方式则是涉及到一个key的东东
     * */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView(mListView.get(position ));
    }
}
