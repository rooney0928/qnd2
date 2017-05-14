package com.app.qunadai.http;

import com.app.qunadai.QNDFactory;
import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.bean.PersonInfo;
import com.app.qunadai.bean.ProductsBean;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.MeBean;
import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.Recommend;
import com.app.qunadai.bean.RegBean;
import com.app.qunadai.bean.ResetBean;
import com.app.qunadai.bean.StatusBean;
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
    public static final String ROOT = "https://mapit.qunadai.com/";
//    public static final String ROOT = "http://192.168.13.132:8080/";
//    public static final String ROOT = "http://192.168.13.203:8282/";


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

    //忘记密码短信
    public static Observable<Message> getForgetSms(String phone) {
        return qndApi.getForgetSms("mobileNumber", "sms", phone);
    }

    //注册
    public static Observable<RegBean> register(String phone, String sms, String pwd) {
        return qndApi.register("mobile", phone, sms, pwd);
    }

    //重置密码
    public static Observable<ResetBean> reset(String phone, String sms, String pwd) {
        return qndApi.reset("mobileNumber", "pwd", phone, sms, pwd);
    }

    //登录by密码
    public static Observable<Token> loginByPwd(String phone, String pwd) {
        return qndApi.loginByPwd("password", phone, pwd);
    }

    //登录by短信
    public static Observable<Token> loginBySms(String phone, String sms) {
        return qndApi.loginBySms("sms", phone, sms);
    }

    //获取个人额度及信息
    public static Observable<PersonBean> getPersonValue(String access_token) {
        return qndApi.getPersonValue(access_token);
    }

    //获取首页推荐
    public static Observable<HomeRecommend> getHomeRecommend() {
        return qndApi.getHomeRecommend();
    }


    //获取过滤式贷款列表
    public static Observable<ProductsBean> getFilterLoan(int page, int pageSize, String tagName,
                                                         String amount, String term) {
        return qndApi.getFilterLoan(page, pageSize, tagName, amount, term);
    }

    //获取推荐贷款
    public static Observable<Recommend> getRecommend(int page, int pageSize) {
        return qndApi.getRecommend("tagName", "推荐", page, pageSize);
    }

    //limit

    //更新业务状态
    public static Observable<StatusBean> updateStatus(String mobileNumber, String businessId, String token) {
        return qndApi.updateStatus(mobileNumber, businessId, token);
    }
    //me

    //个人页面头像昵称
    public static Observable<MeBean> getMeCurrent(String token) {
        return qndApi.getMeCurrent(token);
    }

    //获取真实信息
    public static Observable<PersonInfo> getPersonInfo(String token) {
        return qndApi.getPersonInfo(token);
    }

    //设置真实信息
    public static Observable<PersonInfo> setPersonInfo(String token, RequestBody body) {
        return qndApi.setPersonInfo(token, body);
    }

    //获取银行卡
    public static Observable<BankcardBean> getBankcard(String token) {
        return qndApi.getBankcardInfo(token);
    }

    //设置银行卡
    public static Observable<BankcardBean> setBankcard(String token, RequestBody body) {
        return qndApi.setBankcardInfo(token, body);
    }

}
