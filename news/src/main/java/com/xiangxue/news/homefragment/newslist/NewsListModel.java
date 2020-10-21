package com.xiangxue.news.homefragment.newslist;

import com.xiangxue.base.customerview.BaseCustomerViewModel;
import com.xiangxue.base.mvvm.model.BaseMvvmModel;
import com.xiangxue.common.views.picturetitleview.PictureTitleViewModel;
import com.xiangxue.common.views.titleview.TitleViewModel;
import com.xiangxue.network.TecentNetworkApi;
import com.xiangxue.network.observer.BaseObserver;
import com.xiangxue.news.homefragment.api.NewsApiInterface;
import com.xiangxue.news.homefragment.api.NewsListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:qyg
 * DATE:2020/10/21 14:06
 * Description：
 **/
public class NewsListModel extends BaseMvvmModel<NewsListBean, List<BaseCustomerViewModel>> {
    private String mChannelId;
    private String mChannelName;

    public NewsListModel(String channelId, String channelName) {
        super(true, channelId + channelName + "_preference_key", 1);
        this.mChannelId = channelId;
        this.mChannelName = channelName;
    }

    @Override
    public void load() {
        TecentNetworkApi.getService(NewsApiInterface.class)
                .getNewsList(mChannelId, mChannelName, String.valueOf(mPage))
                .compose(TecentNetworkApi.getInstance().applySchedulers(new BaseObserver<NewsListBean>() {
                    @Override
                    public void onSuccess(NewsListBean newsListBean) {
                        List<BaseCustomerViewModel> viewModels = new ArrayList<>();
                        for (NewsListBean.Contentlist contentList : newsListBean.showapiResBody.pagebean.contentlist) {
                            if (contentList.imageurls != null && contentList.imageurls.size() > 0) {
                                PictureTitleViewModel pictureTitleViewModel = new PictureTitleViewModel();
                                pictureTitleViewModel.pictureUrl = contentList.imageurls.get(0).url;
                                pictureTitleViewModel.jumpUri = contentList.link;
                                pictureTitleViewModel.title = contentList.title;
                                viewModels.add(pictureTitleViewModel);
                            } else {
                                TitleViewModel titleViewModel = new TitleViewModel();
                                titleViewModel.jumpUri = contentList.link;
                                titleViewModel.title = contentList.title;
                                viewModels.add(titleViewModel);
                            }
                        }
                        notifyResultToListener(newsListBean, viewModels);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        loadFail(e.getMessage());
                    }
                }));
    }
}
