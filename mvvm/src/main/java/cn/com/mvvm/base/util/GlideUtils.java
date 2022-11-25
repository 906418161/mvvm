package cn.com.mvvm.base.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class GlideUtils {
    @BindingAdapter("loadImg")
    public static void loadImg(ImageView img,String url){
        Glide.with(img.getContext())
                .load(url)
                .into(img);
    }
}
