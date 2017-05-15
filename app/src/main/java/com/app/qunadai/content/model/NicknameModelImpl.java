package com.app.qunadai.content.model;

import com.app.qunadai.bean.AvatarBean;
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

    public interface OnReturnDataListener {
        void uploadNickname(AvatarBean bean);

        void uploadNicknameFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void uploadNickname(String token, String nickname) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("nick", nickname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());

        Observable<AvatarBean> request = RxHttp.uploadNickname(token, body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<AvatarBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.uploadNicknameFail(ex.getMessage());
                    }

                    @Override
                    protected void onOk(AvatarBean bean) {
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
