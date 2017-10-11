package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Replies;
import com.app.qunadai.content.contract.v5.RepliesContract;
import com.app.qunadai.content.model.v5.RepliesModelImpl;

/**
 * Created by wayne on 2017/10/11.
 */

public class RepliesPresenter implements RepliesContract.Presenter {
    private RepliesContract.View view;
    private RepliesContract.Model model;

    public RepliesPresenter(RepliesContract.View iview) {
        this.view = iview;
        model = new RepliesModelImpl(new RepliesModelImpl.OnReturnDataListener() {
            @Override
            public void getReplies(BaseBean<Replies> bean) {
                view.getReplies(bean);
            }

            @Override
            public void getRepliesMore(BaseBean<Replies> bean) {
                view.getRepliesMore(bean);
            }

            @Override
            public void getRepliesFail(String error) {
                view.getRepliesFail(error);
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
    public void getReplies(String cid, int page, int size) {
        model.getReplies(cid, page, size);
    }
}
