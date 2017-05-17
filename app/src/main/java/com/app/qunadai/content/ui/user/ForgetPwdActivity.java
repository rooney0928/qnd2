package com.app.qunadai.content.ui.user;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.ResetBean;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.ForgetContract;
import com.app.qunadai.content.presenter.ForgetPresenter;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/11.
 */

public class ForgetPwdActivity extends BaseActivity implements ForgetContract.View{
    private ForgetPresenter forgetPresenter;

    @BindView(R.id.et_forget_phone)
    EditText et_forget_phone;
    @BindView(R.id.et_forget_sms)
    EditText et_forget_sms;
    @BindView(R.id.et_forget_pwd)
    EditText et_forget_pwd;


    @BindView(R.id.iv_forget_phone_clear)
    ImageView iv_forget_phone_clear;
    @BindView(R.id.iv_forget_pwd_clear)
    ImageView iv_forget_pwd_clear;

    @BindView(R.id.bt_get_sms)
    Button bt_get_sms;
    @BindView(R.id.bt_reset)
    Button bt_reset;
    boolean isRequest;
    private TimeCount time;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitle("重置密码");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_forget,null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        forgetPresenter = new ForgetPresenter(this);
        time = new TimeCount(60000, 1000);

    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            iv_forget_phone_clear.setVisibility(View.GONE);
            iv_forget_pwd_clear.setVisibility(View.GONE);

            switch (v.getId()) {
                case R.id.et_forget_phone:
                    if (et_forget_phone.getText().toString().trim().length() != 0) {
                        iv_forget_phone_clear.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.et_forget_pwd:
                    if (et_forget_pwd.getText().toString().trim().length() != 0) {
                        iv_forget_pwd_clear.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };


    @Override
    public void initViewData() {
        bt_get_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRequest) {
                    ToastUtil.showToast(ForgetPwdActivity.this, "请稍后");
                    return;
                }
                isRequest = true;
                String phone = et_forget_phone.getText().toString().trim();
                if (phone.length() == 0) {
                    isRequest = false;
                    ToastUtil.showToast(ForgetPwdActivity.this, "请填写正确的手机号");
                    return;
                }
                //请求发送短信
                forgetPresenter.requestForgetSms(phone);
            }

        });
        et_forget_phone.setOnFocusChangeListener(onFocusChangeListener);
        et_forget_pwd.setOnFocusChangeListener(onFocusChangeListener);
        et_forget_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_forget_phone.getText().toString().trim().length()==0){
                    iv_forget_phone_clear.setVisibility(View.GONE);
                }else{
                    iv_forget_phone_clear.setVisibility(View.VISIBLE);
                }
                updateBtStatus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_forget_sms.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateBtStatus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_forget_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_forget_pwd.getText().toString().trim().length() == 0) {
                    iv_forget_pwd_clear.setVisibility(View.GONE);
                }else{
                    iv_forget_pwd_clear.setVisibility(View.VISIBLE);
                }

                updateBtStatus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iv_forget_phone_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_forget_phone.setText("");
            }
        });
        iv_forget_pwd_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_forget_pwd.setText("");
            }
        });

        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtil.checkNetwork(ForgetPwdActivity.this)) {
                    String phone = et_forget_phone.getText().toString().trim();
                    String sms = et_forget_sms.getText().toString().trim();
                    String pwd = et_forget_pwd.getText().toString().trim();
                    LogU.t("pwd?"+CommUtil.shaEncrypt(pwd));
                    if (phone.length() == 0 || sms.length() == 0 || pwd.length() == 0) {
                        ToastUtil.showToast(ForgetPwdActivity.this,"信息未写全");
                    }
                    forgetPresenter.reset(phone,sms, CommUtil.shaEncrypt(pwd));
                }

            }
        });
    }
    private void updateBtStatus(){
        String phone = et_forget_phone.getText().toString().trim();
        String sms = et_forget_sms.getText().toString().trim();
        String pwd = et_forget_pwd.getText().toString().trim();

        if (phone.length() == 0 || sms.length() == 0 || pwd.length() == 0) {
            bt_reset.setEnabled(false);
        } else {
            bt_reset.setEnabled(true);
        }
    }

    @Override
    public void getForgetSms(Message msg) {
        ToastUtil.showToastLong(this,msg.getDetail());
        if (time != null) {
            time.start();
        }
        bt_get_sms.setEnabled(false);
    }

    @Override
    public void getForgetSmsFail(String error) {
        ToastUtil.showToastLong(this,error);

    }

    @Override
    public void resetDone(ResetBean bean) {
        ToastUtil.showToastLong(this,bean.getDetail());
        enterLogin();
    }

    @Override
    public void resetFail(String error) {
        ToastUtil.showToast(this,error);
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
        isRequest = false;
//        ProgressBarUtil.hideLoadDialogDelay(this);
        hideLoading();
    }

    public void enterLogin(){
        Intent intentLogin = new Intent(this,LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }

    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示
            if (bt_get_sms != null) {
                bt_get_sms.setText(millisUntilFinished / 1000 + "s");
            }
        }

        @Override
        public void onFinish() {
            // 计时完毕时触发
            if (bt_get_sms != null) {
                bt_get_sms.setText("获取验证码");
                bt_get_sms.setEnabled(true);
            }
        }
    }
}
