package com.app.qunadai.content.presenter.bbs;

import com.app.qunadai.bean.bbs.PostListBean;
import com.app.qunadai.content.contract.bbs.PostMyContract;
import com.app.qunadai.content.model.bbs.PostMyModelImpl;

/**
 * Created by wayne on 2017/7/4.
 */

public class PostMyPresenter implements PostMyContract.Presenter {

    private PostMyContract.View view;
    private PostMyContract.Model model;

    public PostMyPresenter(PostMyContract.View iview) {
        this.view = iview;

        model = new PostMyModelImpl(new PostMyModelImpl.OnReturnDataListener() {
            @Override
            public void getPostList(PostListBean bean) {
                view.postList(bean);
            }

            @Override
            public void getPostListMore(PostListBean bean) {
                view.postListMore(bean);

            }

            @Override
            public void getPostListFail(String error) {
                view.postListFail(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }

            @Override
            public void tokenFail() {
                view.tokenFail();
            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getPostList(String token, int page, int size) {
        model.getPostList(token, page, size);
    }

}
