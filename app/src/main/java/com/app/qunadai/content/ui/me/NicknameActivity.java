package com.app.qunadai.content.ui.me;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.bean.NickBean;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.NicknameContract;
import com.app.qunadai.content.presenter.NicknamePresenter;
import com.app.qunadai.third.eventbus.EventMe;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wayne on 2017/5/15.
 */

public class NicknameActivity extends BaseActivity implements NicknameContract.View {

    private NicknamePresenter nicknamePresenter;
    @BindView(R.id.et_nickname_text)
    TextView et_nickname_text;
    @BindView(R.id.iv_nickname_clear)
    ImageView iv_nickname_clear;
    InputMethodManager manager;


    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus(TITLE_ON_RIGHT_ON);
        setTitleRight("保存");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_nickname, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        nicknamePresenter = new NicknamePresenter(this);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        String nickname = getIntent().getStringExtra("nickname");
        et_nickname_text.setText(nickname);

    }

    @Override
    public void initViewData() {
        setTitleRightEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CommUtil.getText(et_nickname_text).equals("")){
                    ToastUtil.showToast(NicknameActivity.this,"昵称不能为空");
                    return;
                }

                if(NetworkUtil.checkNetwork(NicknameActivity.this)){
                    nicknamePresenter.uploadNickname(
                            PrefUtil.getString(NicknameActivity.this, PrefKey.TOKEN, ""), CommUtil.getText(et_nickname_text));
                }

            }
        });
        iv_nickname_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nickname_text.setText("");
            }
        });
        et_nickname_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (CommUtil.getText(et_nickname_text).length() > 0) {
                    iv_nickname_clear.setVisibility(View.VISIBLE);
                } else {
                    iv_nickname_clear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void uploadNickname(NickBean bean) {

//        EventBus.getDefault().post(new EventMe());
        EventBus.getDefault().post(new EventMe(CommUtil.getText(et_nickname_text)));
//        EventBus.getDefault().post(new EventMe());

        //延迟500毫秒关闭swipe
        Observable.timer(200, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        });
                    }
                }
        );

    }

    @Override
    public void uploadNicknameFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
//        ProgressBarUtil.showLoadDialog(this);
        showLoading();


    }


    @Override
    public void requestEnd() {
//        ProgressBarUtil.hideLoadDialogDelay(this);
        hideLoading();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        hideKeyboard(event);
        return super.onTouchEvent(event);
    }

    /**
     * 点击其他地方隐藏键盘
     *
     * @param event
     */
    private void hideKeyboard(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
