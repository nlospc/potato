package com.example.nlospc.potatoapp.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.nlospc.potatoapp.R;


public class ImageLoaderManager {
    public static RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.default_img)
            .dontAnimate()
            .error(R.mipmap.default_img)
            .diskCacheStrategy(DiskCacheStrategy.ALL);



    public static void LoadImage(Context context, String url, ImageView imageView){

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);

    }
}
