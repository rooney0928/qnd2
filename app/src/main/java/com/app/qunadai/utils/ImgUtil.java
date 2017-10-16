package com.app.qunadai.utils;

import android.content.Context;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.third.GlideCircleTransform;
import com.bumptech.glide.Glide;

/**
 * Created by wayne on 2017/5/10.
 */

public class ImgUtil {

    public static void loadRound(Context context, int id, ImageView iv) {
        Glide.with(context).load(id).error(R.mipmap.moren).transform(new GlideCircleTransform(context)).into(iv);
    }

    public static void loadRound(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).error(R.mipmap.moren).transform(new GlideCircleTransform(context)).into(iv);
    }

    public static void loadImgAvatar(Context context, int id, ImageView iv) {
        Glide.with(context).load(id).error(R.mipmap.moren).transform(new GlideCircleTransform(context)).into(iv);
    }

    public static void loadImgAvatar(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).error(R.mipmap.moren).transform(new GlideCircleTransform(context)).into(iv);
    }

    public static void loadImg(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).error(R.mipmap.moren).into(iv);
    }

    public static void loadImg(Context context, int id, ImageView iv) {
        Glide.with(context).load(id).error(R.mipmap.moren).into(iv);
    }
    public static void loadBanner(Context context, int id, ImageView iv) {
        Glide.with(context).load(id).centerCrop().error(R.mipmap.moren).into(iv);
    }
    public static void loadBanner(Context context,  String url, ImageView iv) {
        Glide.with(context).load(url).centerCrop().error(R.mipmap.moren).into(iv);
    }
}
