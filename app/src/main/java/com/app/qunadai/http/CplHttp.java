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

    //    public static final String ROOT = "https://mapi.qunadai.com/";
    public static final String ROOT = "https://mapit.qunadai.com/";
//    public static final String ROOT = "http://192.168.13.132:8080/";
//    public static final String ROOT = "http://192.168.7.165:8080/";


    static QndApi qndApi;
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
        Retrofit qndRest = builder.build();
        qndApi = qndRest.create(QndApi.class);
    }

    public static CplHttp getInstance() {
        return SingletonHolder.rxHttp;
    }

    //用户部分
    //登录短信
    public static Observable<Message> getLoginSms(String phone) {
        return qndApi.getLoginSms(phone);
    }

    //登录短信5
    public static Observable<BaseBean<SmsBean>> getLogin5Sms(String phone) {
        return qndApi.getLogin5Sms(phone);
    }

    //注册短信
    public static Observable<Message> getRegisterSms(String phone) {
        return qndApi.getRegisterSms(phone);
    }

    //注册短信5
    public static Observable<BaseBean<SmsBean>> getRegSms(String phone) {
        return qndApi.getRegSms(phone);
    }

    //忘记密码短信
    public static Observable<Message> getForgetSms(String phone) {
        return qndApi.getForgetSms("mobileNumber", "sms", phone);
    }

    //忘记密码短信5
    public static Observable<BaseBean<SmsBean>> getForgetSms5(String phone) {
        return qndApi.getForgetSms5("mobileNumber", "sms", phone);
    }

    //注册
    public static Observable<RegBean> register(String phone, String sms, String pwd) {
        return qndApi.register("mobile", phone, sms, pwd);
    }

    //注册
    public static Observable<BaseBean<Token>> register5(String phone, String sms, String pwd, String source) {
        return qndApi.register5("mobile", phone, sms, pwd, source);
    }

    //重置密码
    public static Observable<ResetBean> reset(String phone, String sms, String pwd) {
        return qndApi.reset("mobileNumber", "pwd", phone, sms, pwd);
    }

    //重置密码
    public static Observable<BaseBean<Token>> reset5(String phone, String sms, String pwd) {
        return qndApi.reset5("mobileNumber", "pwd", phone, sms, pwd);
    }

    //登录by密码
    public static Observable<BaseBean<Token>> loginByPwd(String phone, String pwd) {
        return qndApi.loginByPwd("password", phone, pwd);
    }

    //登录by密码imei
    public static Observable<BaseBean<Token>> loginByPwd(String phone, String pwd, String imei) {
        return qndApi.loginByPwd("password", phone, pwd, imei);
    }

    //登录by短信
    public static Observable<BaseBean<Token>> loginBySms(String phone, String sms, String imei) {
        return qndApi.loginBySms("sms", phone, sms, imei);
    }

    //检测手机有无注册
    public static Observable<BaseBean<IsExist>> checkMobile(String phone) {
        return qndApi.checkMobile(phone);
    }

    //home
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

    //获取产品详情
    public static Observable<ProductDetailBean> getLoanDetail(String pid) {
        return qndApi.getLoanDetail(pid);
    }

    //申请贷款，给后台做log
    public static Observable<ApplyBean> apply(String token, RequestBody body) {
        return qndApi.apply(token, body);
    }

    //5.0版本产品列表，带筛选
    public static Observable<BaseBean<ProductsFilter>> getProductsFilter(String properties, int page, int size) {
        return qndApi.getProductFilter(properties, page, size);
    }

    //5.0版本产品详细
    public static Observable<BaseBean<Product5DetailBean>> getProducts5Detail(String pid, String token) {
        return qndApi.getProduct5Detail(pid, token);
    }

    //5.0版本产品评论
    public static Observable<BaseBean<ProComments>> getProductComments(String pid, int page, int size) {
        return qndApi.getProduct5Comments(pid, page, size);
    }

    //5.0版本产品添加评论
    public static Observable<BaseBean<AddComment>> addComment(String pid, String token, RequestBody body) {
        return qndApi.addComment(pid, token, body);
    }

    //5.0版本产品查询评论下的二级回复
    public static Observable<BaseBean<Replies>> getRepliesByComment(String cid, int page, int size) {
        return qndApi.getRepliesByComment(cid, page, size);
    }

    //5.0版本产品二级回复
    public static Observable<BaseBean<NewReply>> sendNewReply(String cid, String token, RequestBody body) {
        return qndApi.sendNewReply(cid, token, body);
    }

    //5.1.0产品新口子
    public static Observable<BaseBean<ProductsNew>> getProductsNew() {
        return qndApi.getProductsNew();
    }

    //5.1.0看一眼新口子
    public static Observable<BaseBean> lookProductsNew(String token) {
        return qndApi.lookProductsNew(token);
    }


    //首页banner
    public static Observable<BannerBean> getBanner() {
        return qndApi.getBanner("APPLICATION");
    }


    //信用卡
    //所有热门城市
    public static Observable<HotCity> hotCity() {
        return qndApi.hotCity();
    }

    //所有城市
    public static Observable<AllCity> allCity() {
        return qndApi.allCity();
    }

    //寻找城市
    public static Observable<FindCity> findCity(String city) {
        return qndApi.findCity(city, 2);
    }

    //credit
    //攻略列表
    public static Observable<CreditStrategy> creditStrategy(int page, int size) {
        return qndApi.creditStrategy(page, size);
    }

    //银行卡列表
    public static Observable<CreditCard> creditCardList(String city, int page, int size) {
        return qndApi.creditCardList(city, page, size);
    }

    //v5
    public static Observable<BaseBean<Floors>> getHomeFloors() {
        return qndApi.getHomeFloor();
    }

    public static Observable<BaseBean<Products>> getHomeProducts() {
        return qndApi.getHomeProducts();
    }


    //limit
    //更新业务状态
    public static Observable<StatusBean> updateStatus(String mobileNumber, String businessId, String token) {
        return qndApi.updateStatus(mobileNumber, businessId, token);
    }

    //bbs

    //达人攻略
    public static Observable<TalentBean> getTalent(int page, int size) {
        return qndApi.getTalent(page, size);
    }


    //bbs首页攻略
    public static Observable<StrategyBean> getStrategies(int page, int size) {
        return qndApi.getStrategies(page, size);
    }

    //发帖
    public static Observable<PostNewBean> postNew(String token, RequestBody body) {
        return qndApi.postNew(token, body);
    }

    //我的帖子列表
    public static Observable<PostListBean> getMyPostList(String token, int page, int size) {
        return qndApi.getMyPostList(token, page, size);
    }

    public static Observable<PostBean> getPostDetail(String aid, String token) {
        return qndApi.getPostDetail(aid, token);
    }

    public static Observable<PostBean> getPostDetailNoUser(String aid) {
        return qndApi.getPostDetailNoUser(aid);
    }

    //获取帖子回复列表
    public static Observable<CommentList> getCommentList(String token, String aid, int page, int size) {
        return qndApi.getCommentList(token, aid, page, size);
    }

    //获取帖子回复列表(无登录)
    public static Observable<CommentList> getCommentListNoUser(String aid, int page, int size) {
        return qndApi.getCommentListNoUser(aid, page, size);
    }

    //发送回复
    public static Observable<SendCommentBean> sendComment(String token, String aid, RequestBody body) {
        return qndApi.sendComment(aid, token, body);
    }

    //给帖子点赞
    public static Observable<PraiseBean> praisePost(String aid, String token) {
        return qndApi.praisePost(aid, token);
    }

    //给帖子取消点赞
    public static Observable<PraiseBean> cancelPraisePost(String aid, String token) {
        return qndApi.cancelPraisePost(aid, token);
    }

    //给回复点赞
    public static Observable<PraiseBean> praiseComment(String cid, String token) {
        return qndApi.praiseComment(cid, token);
    }

    //给回复取消点赞
    public static Observable<PraiseBean> cancelPraiseComment(String cid, String token) {
        return qndApi.cancelPraiseComment(cid, token);
    }

    //me
    //个人页面头像昵称
    public static Observable<MeBean> getMeCurrent(String token) {
        return qndApi.getMeCurrent(token);
    }

    //上传头像
    public static Observable<AvatarBean> uploadAvatar(String token, RequestBody body) {
        return qndApi.uploadAvatar(token, body);
    }

    //上传昵称
    public static Observable<NickBean> uploadNickname(String token, String nickname) {
        return qndApi.uploadNickname(token, nickname);
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

    public static Observable<BaseBean<FeedBack>> addFeedback(String token, String msg) {
        return qndApi.addFeedback(token, msg);
    }

    public static Observable<BaseBean<ReplyMessages>> getReplyMessages(String token, int page, int size) {
        return qndApi.getReplyMessages(token, page, size);
    }

    //v5
    //获取浏览记录
    public static Observable<BaseBean<ExploreBean>> getExplore(String token) {
        return qndApi.getExplore(token);
    }

    //获取浏览记录
    public static Observable<BaseBean> clearExplore(String token) {
        return qndApi.clearExplore(token);
    }


}
