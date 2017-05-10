package com.app.qunadai.http;

import com.app.qunadai.bean.HomeRecommend;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by wayne on 2017/5/10.
 */

public interface QndApi {

    @GET("loan/products/getHomeData")
    Observable<HomeRecommend> getRecommend();


}
