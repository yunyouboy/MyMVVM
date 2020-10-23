package com.xiangxue.news.homefragment.newslist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.xiangxue.base.customerview.BaseCustomerViewModel;
import com.xiangxue.base.mvvm.viewmodel.BaseMvvmViewModel;

public class NewsListViewModel extends BaseMvvmViewModel<NewsListModel, BaseCustomerViewModel> {
    private String mChannelId;
    private String mChannelName;

    private NewsListViewModel(String channelId, String channelName) {
        mChannelId = channelId;
        mChannelName = channelName;
    }

    @Override
    public NewsListModel createModel() {
        return new NewsListModel(mChannelId, mChannelName);
    }

    public static class NewsListViewModelFactory implements ViewModelProvider.Factory {
        private String mChannelId;
        private String mChannelName;

        public NewsListViewModelFactory(String mChannelId, String mChannelName) {
            this.mChannelId = mChannelId;
            this.mChannelName = mChannelName;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new NewsListViewModel(mChannelId, mChannelName);
        }
    }

}
