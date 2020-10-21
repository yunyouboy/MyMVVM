package com.xiangxue.common.views.databinding;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class CommonBindingAdapters {

    @BindingAdapter("loadImageUrl")
    public static void loadImageUrl(ImageView imageView, String pictureUrl) {
        if (TextUtils.isEmpty(pictureUrl)) {
            return;
        }
        Glide.with(imageView.getContext()).load(pictureUrl).transition(withCrossFade()).into(imageView);
    }
}
