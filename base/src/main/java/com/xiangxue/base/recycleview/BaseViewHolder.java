package com.xiangxue.base.recycleview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiangxue.base.customerview.IBaseCustomerView;
import com.xiangxue.base.customerview.BaseCustomerViewModel;

/**
 * Author:qyg
 * DATE:2020/10/20 11:08
 * Description：
 **/
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private IBaseCustomerView itemView;

    public BaseViewHolder(@NonNull IBaseCustomerView itemView) {
        super((View) itemView);
        this.itemView = itemView;
    }

    public void bind(BaseCustomerViewModel viewModel) {
        this.itemView.setData(viewModel);
    }
}
