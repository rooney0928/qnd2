package com.app.qunadai.http;

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

public interface CplApi {



    //v5
    @GET("user/browsinghistory/current/all")
    Observable<BaseBean<ExploreBean>> getExplore(@Query("access_token") String access_token);

    @PUT("user/browsinghistory/current/all")
    Observable<BaseBean> clearExplore(@Query("access_token") String access_token);

}
