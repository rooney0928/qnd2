package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.content.contract.NicknameContract;
import com.app.qunadai.content.model.NicknameModelImpl;

/**
 * Created by wayne on 2017/5/15.
 */

public class NicknamePresenter implements NicknameContract.Presenter {
    private NicknameContract.View view;
    private NicknameContract.Model model;

    public NicknamePresenter(NicknameContract.View iview) {
        this.view = iview;
        model = new NicknameModelImpl(new NicknameModelImpl.OnReturnDataListener() {
            @Override
            public void uploadNickname(AvatarBean bean) {
                view.uploadNickname(bean);
            }

            @Override
            public void uploadNicknameFail(String error) {
                view.uploadNicknameFail(error);
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
    public void uploadNickname(String token, String nickname) {
        model.uploadNickname(token, nickname);
    }
}
