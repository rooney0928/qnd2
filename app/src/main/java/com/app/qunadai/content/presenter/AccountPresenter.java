package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.content.contract.AccountContract;
import com.app.qunadai.content.model.AccountModelImpl;

/**
 * Created by wayne on 2017/5/15.
 */

public class AccountPresenter implements AccountContract.Presenter {
    private AccountContract.View view;
    private AccountContract.Model model;

    public AccountPresenter(AccountContract.View iview) {
        this.view = iview;
        model = new AccountModelImpl(new AccountModelImpl.OnReturnDataListener() {
            @Override
            public void tokenFail() {
                view.tokenFail();
            }

            @Override
            public void uploadAvatar(AvatarBean bean) {
                view.uploadAvatar(bean);
            }

            @Override
            public void uploadAvatarFail(String error) {
                view.uploadAvatarFail(error);
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
    public void uploadAvatar(String token, String base64) {
        model.uploadAvatar(token,base64);
    }
}
