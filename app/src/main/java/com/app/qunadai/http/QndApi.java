package com.app.qunadai.http;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.Token;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wayne on 2017/5/10.
 */

public interface QndApi {

    /**
     * 登录
     */

    /**
     * 获取登录短信验证码
     * @param mobileNumber
     * @return
     */
    @FormUrlEncoded
    @POST("token/sms")
    Observable<Message> getLoginSms(@Field("mobileNumber") String mobileNumber);

    @FormUrlEncoded
    @POST("token")
    Observable<Token> loginByPwd(@Field("filter") String filter,
                                 @Field("mobileNumber") String mobileNumber,
                                 @Field("sha1password") String pwd);
    @FormUrlEncoded
    @POST("token")
    Observable<Token> loginBySms(@Field("filter") String filter,
                                 @Field("mobileNumber") String mobileNumber,
                                 @Field("smsCode") String sms);


    @GET("loan/products/getHomeData")
    Observable<HomeRecommend> getRecommend();


}
