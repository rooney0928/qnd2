package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.contract.HomeContract;
import com.app.qunadai.content.model.HomeModelImpl;

/**
 * Created by wayne on 2017/5/10.
 */

public class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View view;
    private HomeContract.Model model;

    public HomePresenter(HomeContract.View iview) {
        this.view = iview;
        model = new HomeModelImpl(new HomeModelImpl.OnReturnDataListener() {
            @Override
            public void getHomeRecommend(HomeRecommend bean) {
                view.getHomeRecommend(bean);
            }

            @Override
            public void getHomeRecommendError(String error) {
                view.getHomeRecommendFail(error);
            }

            @Override
            public void getPersonValue(PersonBean bean) {
                view.getPersonValue(bean);
            }

            @Override
            public void getPersonValueFail(String error) {
                view.getPersonValueFail(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }
        });



    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getHomeRecommend() {
        model.getHomeRecommend();
    }

    @Override
    public void requestPersonValue(String token) {
        model.requestPersonValue(token);
    }
}
