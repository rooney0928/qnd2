package com.app.qunadai.http;

import com.app.qunadai.QNDFactory;
import com.app.qunadai.bean.AllCity;
import com.app.qunadai.bean.ApplyBean;
import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.CreditCard;
import com.app.qunadai.bean.CreditStrategy;
import com.app.qunadai.bean.FindCity;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.MeBean;
import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.NickBean;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.PersonInfo;
import com.app.qunadai.bean.ProductDetailBean;
import com.app.qunadai.bean.ProductsBean;
import com.app.qunadai.bean.Recommend;
import com.app.qunadai.bean.RegBean;
import com.app.qunadai.bean.ResetBean;
import com.app.qunadai.bean.StatusBean;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.base.CplBase;
import com.app.qunadai.bean.bbs.CommentList;
import com.app.qunadai.bean.bbs.HotCity;
import com.app.qunadai.bean.bbs.PostBean;
import com.app.qunadai.bean.bbs.PostListBean;
import com.app.qunadai.bean.bbs.PostNewBean;
import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.bean.bbs.SendCommentBean;
import com.app.qunadai.bean.bbs.StrategyBean;
import com.app.qunadai.bean.bbs.TalentBean;
import com.app.qunadai.bean.cpl.CToken;
import com.app.qunadai.bean.v5.AddComment;
import com.app.qunadai.bean.v5.ExploreBean;
import com.app.qunadai.bean.v5.FeedBack;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.bean.v5.NewReply;
import com.app.qunadai.bean.v5.ProComments;
import com.app.qunadai.bean.v5.Product5DetailBean;
import com.app.qunadai.bean.v5.Products;
import com.app.qunadai.bean.v5.ProductsFilter;
import com.app.qunadai.bean.v5.ProductsNew;
import com.app.qunadai.bean.v5.Replies;
import com.app.qunadai.bean.v5.ReplyMessages;
import com.app.qunadai.bean.v5.SmsBean;
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

public class CplHttp {

    public static final String ROOT = "https://ssppoc.lianzi360.com/";


    static CplApi cplApi;
    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    private static class SingletonHolder {
        //单例
        private static CplHttp rxHttp = new CplHttp();
    }

    private CplHttp() {
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
        Retrofit cplRest = builder.build();
        cplApi = cplRest.create(CplApi.class);
    }

    public static CplHttp getInstance() {
        return SingletonHolder.rxHttp;
    }


    //获取token
    public static Observable<CplBase<CToken>> reqToken(RequestBody body) {
        return cplApi.reqToken(body);
    }

    //提交用户信息
    public static Observable<CplBase> setUserInfo(RequestBody body) {
        return cplApi.setUserInfo(body);
    }


    //获取浏览记录
//    public static Observable<BaseBean> clearExplore(String token) {
//        return qndApi.clearExplore(token);
//    }


}
