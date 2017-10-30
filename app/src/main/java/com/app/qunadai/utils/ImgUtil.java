package com.app.qunadai.utils;

import android.content.Context;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by wayne on 2017/5/10.
 */

public class ImgUtil {

    private static final int ERROR_IMG = R.mipmap.moren;

    public static void loadRound(Context context, int id, ImageView iv) {
        Glide.with(context).load(id)
                .apply(new RequestOptions().circleCrop().error(ERROR_IMG))
                .into(iv);

    }

    public static void loadRound(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().circleCrop().error(ERROR_IMG))
                .into(iv);
    }

    public static void loadImgAvatar(Context context, int id, ImageView iv) {
        Glide.with(context).load(id)
                .apply(new RequestOptions().circleCrop().error(ERROR_IMG))
                .into(iv);
    }

    public static void loadImgAvatar(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().circleCrop().error(ERROR_IMG))
                .into(iv);
    }

    public static void loadImg(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().error(ERROR_IMG))
                .into(iv);
    }

    public static void loadImg(Context context, int id, ImageView iv) {
        Glide.with(context).load(id)
                .apply(new RequestOptions().error(ERROR_IMG))
                .into(iv);
    }

    public static void loadBanner(Context context, int id, ImageView iv) {
        Glide.with(context).load(id)
                .apply(new RequestOptions().error(ERROR_IMG))
                .into(iv);
    }

    public static void loadBanner(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().error(ERROR_IMG))
                .into(iv);

    }
}
