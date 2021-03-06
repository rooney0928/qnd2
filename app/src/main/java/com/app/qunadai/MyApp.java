package com.app.qunadai;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.app.qunadai.utils.LogU;
import com.pgyersdk.Pgy;
import com.pgyersdk.update.PgyUpdateManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by wayne on 2017/1/5.
 */

public class MyApp extends Application {
    public static Context context;

    //测试
//    public static final String MX_KEY = "3ef6ede77e524c038765a874e95ce2ad";
//    public static final String MX_TOKEN = "a4c70f4239b8488eb4f08b4378438d21";

    //上线
    public static final String MX_KEY = "191424e222e54baf8ec241394f8882f9";
    public static final String MX_TOKEN = "399efb697cef4e219c1365b096ac669d";


    //bugly
    public static final String BUGLY_ID = "33b9e0aeb0";
    //umeng
    public static final String UMENG_KEY = "56aecffe67e58e68ac00017b";

    //pgy 蒲公英
    public static final String PGY_KEY = "48a92fa2e222dd69f83f4bc1684237c6";

    private static final String WX_ID = "wx02d89c8c3173cb34";
    public static IWXAPI api;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        init();
    }

    private void init() {
        CrashReport.initCrashReport(getApplicationContext(), BUGLY_ID, false);
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
        Pgy.init(this, PGY_KEY);

        //umeng
//        MobclickAgent.setDebugMode(false);
//        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(
//                context, UMENG_KEY, "qunadai", MobclickAgent.EScenarioType.E_UM_NORMAL, false);
//        MobclickAgent.startWithConfigure(config);

        //WX
        api = WXAPIFactory.createWXAPI(this,WX_ID,true);
        api.registerApp(WX_ID);
        api.getWXAppSupportAPI();


        //talkingData
        TCAgent.LOG_ON = false;
        TCAgent.setReportUncaughtExceptions(false);
        TCAgent.init(this);


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
