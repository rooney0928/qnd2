package com.app.qunadai.content;

import android.app.Application;
import android.content.Context;


/**
 * Created by wayne on 2017/1/5.
 */

public class MyApp extends Application {
    public static Context context;

    //测试
    public static final String MX_KEY = "3ef6ede77e524c038765a874e95ce2ad";
    public static final String MX_TOKEN = "a4c70f4239b8488eb4f08b4378438d21";

    //umen

    //bugly
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
