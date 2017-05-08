package com.app.qunadai.content;

import android.app.Application;
import android.content.Context;


/**
 * Created by wayne on 2017/1/5.
 */

public class MyApplication extends Application {
    public static Context context;

    //umen

    //bugly
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
