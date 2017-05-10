package com.app.qunadai.utils;

import android.content.Context;
import android.widget.ImageView;

import com.app.qunadai.third.GlideCircleTransform;
import com.bumptech.glide.Glide;

/**
 * Created by wayne on 2017/5/10.
 */

public class ImgUtil {

    public static void loadRound(Context context, int id, ImageView iv){
        Glide.with(context).load(id).transform(new GlideCircleTransform(context)).into(iv);
    }
}
