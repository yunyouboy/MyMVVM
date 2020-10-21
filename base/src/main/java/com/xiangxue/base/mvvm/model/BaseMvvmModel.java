package com.xiangxue.base.mvvm.model;

import com.google.gson.Gson;
import com.xiangxue.base.preference.BasicDataPreferenceUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Author:qyg
 * DATE:2020/10/21 15:38
 * Description：
 **/
public abstract class BaseMvvmModel<NETWORK_DATA, RESULT_DATA> {
    protected WeakReference<IBaseModelListener> mReferenceIBaseModelListener;

    protected int mPage = 1;
    private boolean mIsPaging;//是否分页
    private final int INIT_PAGE_NUMBER;
    private boolean mIsLoading;//是否正在请求网络

    private String mCachedPreferenceKey;

    public BaseMvvmModel(boolean isPaging, String cachedPreferenceKey, int... initPageNumber) {
        mIsPaging = isPaging;
        if (isPaging && initPageNumber != null && initPageNumber.length > 0) {
            INIT_PAGE_NUMBER = initPageNumber[0];
        } else {
            INIT_PAGE_NUMBER = -1;
        }
        mCachedPreferenceKey = cachedPreferenceKey;
    }

    public void register(IBaseModelListener listener) {
        if (null != listener) {
            mReferenceIBaseModelListener = new WeakReference<>(listener);
        }
    }

    public void refresh() {
        // Need to throw exception if register is not called;
        if (!mIsLoading) {
            if (mIsPaging) {
                mPage = INIT_PAGE_NUMBER;
            }
            mIsLoading = true;
            load();
        }
    }

    public void loadNextPage() {
        // Need to throw exception if register is not called;
        if (!mIsLoading) {
            mIsLoading = true;
            load();
        }
    }

    protected abstract void load();

    protected void notifyResultToListener(NETWORK_DATA networkData,RESULT_DATA resultData) {
        IBaseModelListener listener = mReferenceIBaseModelListener.get();
        if (listener != null) {
            // notify
            if (mIsPaging) {
                listener.onLoadSuccess(this, resultData, new PagingResult(mPage == INIT_PAGE_NUMBER, resultData == null ? true : ((List) resultData).isEmpty(), ((List) resultData).size() > 0));
            } else {
                listener.onLoadSuccess(this, resultData);
            }

            // save resultData to preference
            if(mIsPaging) {
                if(mCachedPreferenceKey != null && mPage == INIT_PAGE_NUMBER){
                    saveDataToPreference(networkData);
                }
            } else {
                if(mCachedPreferenceKey != null){
                    saveDataToPreference(networkData);
                }
            }

            // update page number
            if (mIsPaging) {
                if (resultData != null && ((List) resultData).size() > 0) {
                    mPage++;
                }
            }
        }
        mIsLoading = false;
    }

    protected void loadFail(final String errorMessage) {
        IBaseModelListener listener = mReferenceIBaseModelListener.get();
        if (listener != null) {
            if (mIsPaging) {
                listener.onLoadFail(this, errorMessage, new PagingResult(mPage == INIT_PAGE_NUMBER, true, false));
            } else {
                listener.onLoadFail(this, errorMessage);
            }
        }
        mIsLoading = false;
    }

    protected void saveDataToPreference(NETWORK_DATA data) {
        if (data != null) {
            BaseCachedData<NETWORK_DATA> cachedData = new BaseCachedData<>();
            cachedData.data = data;
            cachedData.updateTimeInMillis = System.currentTimeMillis();
            BasicDataPreferenceUtil.getInstance().setString(mCachedPreferenceKey, new Gson().toJson(cachedData));
        }
    }
}
