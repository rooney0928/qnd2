package com.app.qunadai.http;

import com.app.qunadai.bean.AllCity;
import com.app.qunadai.bean.ApplyBean;
import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.FindCity;
import com.app.qunadai.bean.CreditCard;
import com.app.qunadai.bean.CreditStrategy;
import com.app.qunadai.bean.NickBean;
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
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.bbs.CommentList;
import com.app.qunadai.bean.bbs.HotCity;
import com.app.qunadai.bean.bbs.PostBean;
import com.app.qunadai.bean.bbs.PostListBean;
import com.app.qunadai.bean.bbs.PostNewBean;
import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.bean.bbs.SendCommentBean;
import com.app.qunadai.bean.bbs.StrategyBean;
import com.app.qunadai.bean.bbs.TalentBean;
import com.app.qunadai.bean.v5.AddComment;
import com.app.qunadai.bean.v5.FeedBack;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.ExploreBean;
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

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
     * 获取登录短信验证码5
     *
     * @param mobileNumber
     * @return
     */
    @FormUrlEncoded
    @POST("token/sms")
    Observable<BaseBean<SmsBean>> getLogin5Sms(@Field("mobileNumber") String mobileNumber);

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
     * 获取注册短信验证码5
     *
     * @param mobileNumber
     * @return
     */
    @FormUrlEncoded
    @POST("users/signup")
    Observable<BaseBean<SmsBean>> getRegSms(@Field("mobileNumber") String mobileNumber);


    /**
     * 获取忘记短信验证码
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
     * 获取忘记短信验证码5
     *
     * @param mobileNumber
     * @return
     */
    @FormUrlEncoded
    @POST("users")
    Observable<BaseBean<SmsBean>> getForgetSms5(@Field("filter") String filter
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
     * 重置密码
     *
     * @return
     */
    @PUT("users")
    Observable<BaseBean<Token>> reset5(@Query("filter") String filter,
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

    /**
     * 注册
     *
     * @return
     */
    @PUT("users/activate")
    Observable<BaseBean<Token>> register5(@Query("filter") String filter,
                                          @Query("mobileNumber") String mobileNumber,
                                          @Query("smsActivateCode") String sms,
                                          @Query("sha1password") String pwd,
                                          @Query("registerSource") String source);

    @FormUrlEncoded
    @POST("token")
    Observable<BaseBean<Token>> loginByPwd(@Field("filter") String filter,
                                           @Field("mobileNumber") String mobileNumber,
                                           @Field("sha1password") String pwd);

    @FormUrlEncoded
    @POST("token")
    Observable<BaseBean<Token>> loginByPwd(@Field("filter") String filter,
                                           @Field("mobileNumber") String mobileNumber,
                                           @Field("sha1password") String pwd,
                                           @Field("imei") String imei);

    @FormUrlEncoded
    @POST("token")
    Observable<BaseBean<Token>> loginBySms(@Field("filter") String filter,
                                           @Field("mobileNumber") String mobileNumber,
                                           @Field("smsCode") String sms,
                                           @Field("imei") String imei);

    @GET("users/signup/mobile")
    Observable<BaseBean<IsExist>> checkMobile(@Query("mobileNumber") String mobileNumber);

    //home
    @GET("home/personalvalue")
    Observable<PersonBean> getPersonValue(@Query("access_token") String access_token);

    @GET("loan/products/getHomeFloors")
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


    @POST("home/loan/orders/createH5Order")
    Observable<ApplyBean> apply(@Query("access_token") String access_token, @Body RequestBody body);

    @GET("loan/products/{vdoing}/page")
    Observable<BaseBean<ProductsFilter>> getProductFilter(@Path("vdoing") String vdoing,
                                                          @Query("page") int page,
                                                          @Query("size") int size);

    @GET("loan/products/{pid}")
    Observable<BaseBean<Product5DetailBean>> getProduct5Detail(@Path("pid") String pid, @Query("access_token") String token);

    @GET("loan/products/{productId}/comments")
    Observable<BaseBean<ProComments>> getProduct5Comments(@Path("productId") String productId,
                                                          @Query("page") int page,
                                                          @Query("size") int size);

    @POST("loan/products/{productId}/newcomment")
    Observable<BaseBean<AddComment>> addComment(@Path("productId") String productId,
                                                @Query("access_token") String access_token,
                                                @Body RequestBody body);

    @GET("loan/products/comments/{cid}/replies")
    Observable<BaseBean<Replies>> getRepliesByComment(@Path("cid") String cid,
                                                      @Query("page") int page,
                                                      @Query("size") int size);

    @POST("loan/products/comments/{id}")
    Observable<BaseBean<NewReply>> sendNewReply(@Path("id") String cid, @Query("access_token") String access_token, @Body RequestBody body);


    @GET("loan/products/latestProducts")
    Observable<BaseBean<ProductsNew>> getProductsNew();

    @PUT("users/browsedLatestProducts")
    Observable<BaseBean> lookProductsNew(@Query("access_token") String access_token);



    //credit card

    @GET("creditCard/strategies")
    Observable<CreditStrategy> creditStrategy(@Query("page") int page, @Query("size") int size);

    @GET("bank/pageListByCName")
    Observable<CreditCard> creditCardList(@Query("cName") String cName, @Query("page") int page, @Query("size") int size);

    @GET("global/basicdata/getHotRegionList")
    Observable<HotCity> hotCity();

    @GET("global/basicdata/getAllCityList")
    Observable<AllCity> allCity();

    @GET("global/basicdata/getDistrictListByNameAndType")
    Observable<FindCity> findCity(@Query("name") String cName, @Query("levelType") int type);

    @GET("banners/available")
    Observable<BannerBean> getBanner(@Query("bannerType") String bannerType);


    //    v5---start
    @GET("home/floor/getHomeFloors")
    Observable<BaseBean<Floors>> getHomeFloor();

    @GET("loan/products/home")
    Observable<BaseBean<Products>> getHomeProducts();


    //limit
    @FormUrlEncoded
    @POST("home/personalvalue/updateStatus")
    Observable<StatusBean> updateStatus(@Field("mobileNumber") String mobileNumber,
                                        @Field("businessId") String businessId,
                                        @Field("access_token") String access_token);

    //bbs
    @GET("forum/articles/postings")
    Observable<TalentBean> getTalent(@Query("page") int page, @Query("size") int size);

    @GET("forum/articles/strategies")
    Observable<StrategyBean> getStrategies(@Query("page") int page, @Query("size") int size);

    @POST("forum/articles/newArticle")
    Observable<PostNewBean> postNew(@Query("access_token") String access_token, @Body RequestBody body);

    @GET("forum/articles/mine")
    Observable<PostListBean> getMyPostList(@Query("access_token") String token, @Query("page") int page, @Query("size") int size);

    @GET("forum/comments/article/listForUser")
    Observable<CommentList> getCommentList(@Query("access_token") String token, @Query("aid") String aid, @Query("page") int page, @Query("size") int size);

    @GET("forum/comments/article/list")
    Observable<CommentList> getCommentListNoUser(@Query("aid") String aid, @Query("page") int page, @Query("size") int size);

    @GET("forum/articles/detailForUser/{articleId}")
    Observable<PostBean> getPostDetail(@Path("articleId") String aid, @Query("access_token") String token);

    @GET("forum/articles/detail/{articleId}")
    Observable<PostBean> getPostDetailNoUser(@Path("articleId") String aid);

    @POST("forum/comments/newComment/{articleId}")
    Observable<SendCommentBean> sendComment(@Path("articleId") String articleId, @Query("access_token") String token, @Body RequestBody body);

    @PUT("forum/articles/{articleId}/thumbUps")
    Observable<PraiseBean> praisePost(@Path("articleId") String articleId, @Query("access_token") String token);

    @DELETE("forum/articles/{articleId}/thumbUps")
    Observable<PraiseBean> cancelPraisePost(@Path("articleId") String articleId, @Query("access_token") String token);

    @PUT("forum/comments/{commentId}/thumbUps")
    Observable<PraiseBean> praiseComment(@Path("commentId") String commentId, @Query("access_token") String token);

    @DELETE("forum/comments/{commentId}/thumbUps")
    Observable<PraiseBean> cancelPraiseComment(@Path("commentId") String commentId, @Query("access_token") String token);

    //me
    @GET("users/current")
    Observable<MeBean> getMeCurrent(@Query("access_token") String access_token);

    @POST("home/avatar")
    Observable<AvatarBean> uploadAvatar(@Query("access_token") String access_token, @Body RequestBody body);

    @PUT("users/current/nick")
    Observable<NickBean> uploadNickname(@Query("access_token") String access_token, @Query("nick") String nickname);

    @GET("home/creditinfo")
    Observable<PersonInfo> getPersonInfo(@Query("access_token") String access_token);

    @PUT("home/creditinfo")
    Observable<PersonInfo> setPersonInfo(@Query("access_token") String access_token, @Body RequestBody body);

    @GET("home/requirement")
    Observable<BankcardBean> getBankcardInfo(@Query("access_token") String access_token);

    @POST("home/requirement")
    Observable<BankcardBean> setBankcardInfo(@Query("access_token") String access_token, @Body RequestBody body);

    @POST("feedbacks")
    Observable<BaseBean<FeedBack>> addFeedback(@Query("access_token") String access_token, @Query("message") String message);

    @GET("loan/products/comments/replied")
    Observable<BaseBean<ReplyMessages>> getReplyMessages(@Query("access_token") String access_token, @Query("page") int page, @Query("size") int size);


    //v5
    @GET("user/browsinghistory/current/all")
    Observable<BaseBean<ExploreBean>> getExplore(@Query("access_token") String access_token);

    @PUT("user/browsinghistory/current/all")
    Observable<BaseBean> clearExplore(@Query("access_token") String access_token);

}
