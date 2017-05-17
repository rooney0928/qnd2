package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.Recommend;
import com.app.qunadai.content.contract.RecommendContract;
import com.app.qunadai.content.model.RecommendModelImpl;

/**
 * Created by wayne on 2017/5/12.
 */

public class RecommendPresenter implements RecommendContract.Presenter {


    private RecommendContract.View view;
    private RecommendContract.Model model;

    public RecommendPresenter(RecommendContract.View iview) {
        this.view = iview;

        model = new RecommendModelImpl(new RecommendModelImpl.OnReturnDataListener() {
            @Override
            public void tokenFail() {
                view.tokenFail();
            }

            @Override
            public void getRecommend(Recommend bean) {
                view.getRecommend(bean);
            }

            @Override
            public void getRecommendMore(Recommend bean) {
                view.getRecommendMore(bean);
            }

            @Override
            public void getRecommendFail(String error) {
                view.getRecommendFail(error);
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
    public void getRecommend(int page, int pageSize) {
        model.getRecommend(page, pageSize);
    }

    @Override
    public void getRecommendMore(int page, int pageSize) {
        model.getRecommendMore(page,pageSize);
    }
}
