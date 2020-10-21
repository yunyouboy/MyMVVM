package com.xiangxue.base.customerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Author:qyg
 * DATE:2020/10/20 18:25
 * Description：
 **/
public abstract class BaseCustomerView<VIEW extends ViewDataBinding, DATA extends BaseCustomerViewModel> extends LinearLayout  implements IBaseCustomerView<DATA>{

    protected VIEW binding;
    protected DATA data;

    public BaseCustomerView(Context context) {
        this(context,null);
    }

    public BaseCustomerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseCustomerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public BaseCustomerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),this,false);
        binding.getRoot().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onRootClicked(v);
            }
        });
        addView(binding.getRoot());
    }

    protected abstract void onRootClicked(View v);

    protected abstract int getLayoutId();

    @Override
    public void setData(DATA data) {
        this.data = data;
        setDataToView(data);
        binding.executePendingBindings();
    }

    protected abstract void setDataToView(DATA data);
}
