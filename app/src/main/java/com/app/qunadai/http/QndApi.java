package com.app.qunadai.http;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.Token;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    /**
     * 获取注册短信验证码
     * @param mobileNumber
     * @return
     */
    @FormUrlEncoded
    @POST("users/signup")
    Observable<Message> getRegisterSms(@Field("mobileNumber") String mobileNumber);
    /**
     * 注册
     * @return
     */
//    @PUT("users/activate")
//    Observable<String> register(@Query("filter") String filter,
//                                @Query("mobileNumber") String mobileNumber,
//                                @Query("smsActivateCode") String sms,
//                                @Query("sha1password") String pwd);
    @FormUrlEncoded
    @PUT("users/activate")
    Observable<String> register(@Path("filter") String filter,
                                @Path("mobileNumber") String mobileNumber,
                                @Path("smsActivateCode") String sms,
                                @Path("sha1password") String pwd);



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
