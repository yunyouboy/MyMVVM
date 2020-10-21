package com.xiangxue.common.views.picturetitleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.xiangxue.base.customerview.BaseCustomerView;
import com.xiangxue.common.R;
import com.xiangxue.common.databinding.PictureTitleViewBinding;
import com.xiangxue.webview.WebviewActivity;


/**
 * Author:qyg
 * DATE:2020/10/20 10:12
 * Description：
 **/
public class PictureTitleView extends BaseCustomerView<PictureTitleViewBinding, PictureTitleViewModel> {

    public PictureTitleView(Context context) {
        this(context, null);
    }

    public PictureTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PictureTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PictureTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onRootClicked(View v) {
        WebviewActivity.startCommonWeb(getContext(), "News", data.jumpUri);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.picture_title_view;
    }


    @Override
    protected void setDataToView(PictureTitleViewModel pictureTitleViewModel) {
        binding.setViewModel(pictureTitleViewModel);
    }
}
