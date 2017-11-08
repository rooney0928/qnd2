package com.app.qunadai.http;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.base.CplBase;
import com.app.qunadai.bean.cpl.CToken;
import com.app.qunadai.bean.v5.ExploreBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wayne on 2017/5/10.
 */

public interface CplApi {

    @POST("app/reqToken")
    Observable<CplBase<CToken>> reqToken(@Body RequestBody body);

    @POST("app/credit/basic/submit")
    Observable<CplBase> setUserInfo(@Body RequestBody body);


    //v5
    @GET("user/browsinghistory/current/all")
    Observable<BaseBean<ExploreBean>> getExplore(@Query("access_token") String access_token);

    @PUT("user/browsinghistory/current/all")
    Observable<BaseBean> clearExplore(@Query("access_token") String access_token);

}
