package com.app.qunadai.http;

import com.app.qunadai.QNDFactory;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/10.
 */

public class RxHttp {

    //    private static final String ROOT = "https://mapi.qunadai.com/";
    public static final String ROOT = "http://192.168.13.132:8080/";


    static QndApi qndApi;
    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    private static class SingletonHolder {
        //单例
        private static RxHttp rxHttp = new RxHttp();
    }

    private RxHttp() {
        //超时时间设置
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .readTimeout(12, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)// 失败重发
                ;
        if (QNDFactory.isDebug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }


        OkHttpClient client = httpClient.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(ROOT)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit gankRest = builder.build();
        qndApi = gankRest.create(QndApi.class);
    }

    public static RxHttp getInstance() {
        return SingletonHolder.rxHttp;
    }

    //用户部分
    //登录短信
    public static Observable<Message> getLoginSms(String phone) {
        return qndApi.getLoginSms(phone);
    }

    //注册短信
    public static Observable<Message> getRegisterSms(String phone) {
        return qndApi.getRegisterSms(phone);
    }

    //注册
    public static Observable<String> register(String phone, String sms, String pwd) {
        return qndApi.register("mobile", phone, sms, pwd);
    }

    //登录by密码
    public static Observable<Token> loginByPwd(String phone, String pwd) {
        return qndApi.loginByPwd("password", phone, pwd);
    }

    //登录by短信
    public static Observable<Token> loginBySms(String phone, String sms) {
        return qndApi.loginBySms("sms", phone, sms);
    }


    public static Observable<HomeRecommend> getRecommend() {
        return qndApi.getRecommend();
    }

}
