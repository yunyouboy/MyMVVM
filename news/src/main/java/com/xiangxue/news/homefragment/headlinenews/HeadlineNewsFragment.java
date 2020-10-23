package com.xiangxue.news.homefragment.headlinenews;

import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.xiangxue.base.mvvm.view.BaseMvvmFragment;
import com.xiangxue.news.R;
import com.xiangxue.news.databinding.FragmentHomeBinding;
import com.xiangxue.news.homefragment.api.NewsChannelsBean;

import java.util.List;

public class HeadlineNewsFragment extends BaseMvvmFragment<FragmentHomeBinding, HeadlineNewsViewModel, NewsChannelsBean.ChannelList> {
    public HeadlineNewsFragmentAdapter adapter;

    @Override
    protected String getFragmentTag() {
        return "HeadlineNewsFragment";
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HeadlineNewsViewModel getViewModel() {
        return new HeadlineNewsViewModel();
    }

    @Override
    protected View getLoadSirView() {
        return viewDataBinding.tablayout;
    }


    @Override
    public void onNetworkResponded(List list, boolean isDataUpdated) {
        if (null != list && list.size() > 0 && isDataUpdated) {
            adapter.setChannels(list);
        }
    }

    @Override
    protected void onViewCreated() {
        adapter = new HeadlineNewsFragmentAdapter(getChildFragmentManager());
        viewDataBinding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewDataBinding.viewpager.setAdapter(adapter);
        viewDataBinding.tablayout.setupWithViewPager(viewDataBinding.viewpager);
        viewDataBinding.viewpager.setOffscreenPageLimit(1);
    }
}
