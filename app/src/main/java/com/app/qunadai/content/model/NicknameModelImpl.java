package com.app.qunadai.content.model;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.bean.NickBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.NicknameContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/15.
 */

public class NicknameModelImpl implements NicknameContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public NicknameModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void uploadNickname(NickBean bean);

        void uploadNicknameFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void uploadNickname(String token, String nickname) {
//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("nick", nickname);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());

        Observable<NickBean> request = RxHttp.uploadNickname(token, nickname);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<NickBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.uploadNicknameFail(ex.getDisplayMessage());
                        if(ex.isTokenFail()){
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(NickBean bean) {
                        onReturnDataListener.uploadNickname(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
