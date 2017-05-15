package com.app.qunadai.http;

import com.app.qunadai.bean.ApplyBean;
import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.bean.PersonInfo;
import com.app.qunadai.bean.ProductDetailBean;
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

import okhttp3.RequestBody;
import retrofit2.http.Body;
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
     *
     * @param mobileNumber
     * @return
     */
    @FormUrlEncoded
    @POST("token/sms")
    Observable<Message> getLoginSms(@Field("mobileNumber") String mobileNumber);

    /**
     * 获取注册短信验证码
     *
     * @param mobileNumber
     * @return
     */
    @FormUrlEncoded
    @POST("users/signup")
    Observable<Message> getRegisterSms(@Field("mobileNumber") String mobileNumber);

    /**
     * 获取注册短信验证码
     *
     * @param mobileNumber
     * @return
     */
    @FormUrlEncoded
    @POST("users")
    Observable<Message> getForgetSms(@Field("filter") String filter
            , @Field("c") String c
            , @Field("mobileNumber") String mobileNumber);

    /**
     * 重置密码
     *
     * @return
     */
    @PUT("users")
    Observable<ResetBean> reset(@Query("filter") String filter,
                                @Query("c") String c,
                                @Query("mobileNumber") String mobileNumber,
                                @Query("verifiCode") String sms,
                                @Query("newsha1password") String pwd);

    /**
     * 注册
     *
     * @return
     */
    @PUT("users/activate")
    Observable<RegBean> register(@Query("filter") String filter,
                                 @Query("mobileNumber") String mobileNumber,
                                 @Query("smsActivateCode") String sms,
                                 @Query("sha1password") String pwd);

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

    //home
    @GET("home/personalvalue")
    Observable<PersonBean> getPersonValue(@Query("access_token") String access_token);

    @GET("loan/products/getHomeData")
    Observable<HomeRecommend> getHomeRecommend();

    @FormUrlEncoded
    @POST("loan/products/getProducts")
    Observable<ProductsBean> getFilterLoan(@Field("page") int page,
                                           @Field("size") int pageSize,
                                           @Field("tagName") String tagName,
                                           @Field("amount") String amount,
                                           @Field("term") String term);


    @GET("loan/products")
    Observable<Recommend> getRecommend(@Query("filter") String filter,
                                       @Query("tagName") String tagName,
                                       @Query("page") int page,
                                       @Query("pageSize") int pageSize);

    @GET("loan/products/{pid}")
    Observable<ProductDetailBean> getLoanDetail(@Path("pid") String pid);


    @POST("home/loan/orders")
    Observable<ApplyBean> apply(@Query("access_token") String access_token, @Body RequestBody body);


    //limit
    @FormUrlEncoded
    @POST("home/personalvalue/updateStatus")
    Observable<StatusBean> updateStatus(@Field("mobileNumber") String mobileNumber,
                                        @Field("businessId") String businessId,
                                        @Field("access_token") String access_token);

    //me
    @GET("users/current")
    Observable<MeBean> getMeCurrent(@Query("access_token") String access_token);


    @GET("home/creditinfo")
    Observable<PersonInfo> getPersonInfo(@Query("access_token") String access_token);

    @PUT("home/creditinfo")
    Observable<PersonInfo> setPersonInfo(@Query("access_token") String access_token, @Body RequestBody body);

    @GET("home/requirement")
    Observable<BankcardBean> getBankcardInfo(@Query("access_token") String access_token);

    @POST("home/requirement")
    Observable<BankcardBean> setBankcardInfo(@Query("access_token") String access_token, @Body RequestBody body);


}
