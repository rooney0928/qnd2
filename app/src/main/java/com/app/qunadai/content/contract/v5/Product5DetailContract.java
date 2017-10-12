package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.NewReply;
import com.app.qunadai.bean.v5.ProComments;
import com.app.qunadai.bean.v5.Product5DetailBean;
import com.app.qunadai.bean.v5.ProductsFilter;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface Product5DetailContract {
    interface View extends BaseView {
        void getProduct5Detail(BaseBean<Product5DetailBean> bean);
        void getProduct5DetailFail(String error);
        void getProduct5Comments(BaseBean<ProComments> bean);
        void getProduct5CommentsMore(BaseBean<ProComments> bean);
        void getProduct5CommentsFail(String error);

        void sendNewReply(BaseBean<NewReply> bean);
        void sendNewReplyFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getProduct5Detail(String pid,String token);
        void getProduct5Comments(String pid,int page,int size);
        void applyOrder(String token,String productId);
        void sendNewReply(String token,String cid,String content);
    }

    interface Model extends BaseModel {
        void getProduct5Detail(String pid,String token);
        void getProduct5Comments(String pid,int page,int size);
        void applyOrder(String token,String productId);
        void sendNewReply(String token,String cid,String content);
    }
}
