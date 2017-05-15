package com.app.qunadai.content.ui.user;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.qunadai.R;
import com.app.qunadai.bean.RegBean;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.RegisterContract;
import com.app.qunadai.content.presenter.RegisterPresenter;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ProgressBarUtil;
import com.app.qunadai.utils.ToastUtil;


import butterknife.BindView;

/**
 * Created by wayne on 2017/5/11.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    private RegisterPresenter registerPresenter;

    @BindView(R.id.et_register_phone)
    EditText et_register_phone;
    @BindView(R.id.et_register_sms)
    EditText et_register_sms;
    @BindView(R.id.et_register_pwd)
    EditText et_register_pwd;


    @BindView(R.id.bt_get_sms)
    Button bt_get_sms;
    @BindView(R.id.bt_register)
    Button bt_register;

    @BindView(R.id.ll_login)
    LinearLayout ll_login;

    @BindView(R.id.iv_register_phone_clear)
    ImageView iv_register_phone_clear;
    @BindView(R.id.iv_register_pwd_clear)
    ImageView iv_register_pwd_clear;


    boolean isRequest;
    private TimeCount time;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitle("注册");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_register, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        registerPresenter = new RegisterPresenter(this);
        time = new TimeCount(60000, 1000);

    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            iv_register_phone_clear.setVisibility(View.GONE);
            iv_register_pwd_clear.setVisibility(View.GONE);

            switch (v.getId()) {
                case R.id.et_register_phone:
                    if (et_register_phone.getText().toString().trim().length() != 0) {
                        iv_register_phone_clear.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.et_register_pwd:
                    if (et_register_pwd.getText().toString().trim().length() != 0) {
                        iv_register_pwd_clear.setVisibility(View.VISIBLE);
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
                    ToastUtil.showToast(RegisterActivity.this, "请稍后");
                    return;
                }
                isRequest = true;
                String phone = et_register_phone.getText().toString().trim();
                if (phone.length() == 0) {
                    isRequest = false;
                    ToastUtil.showToast(RegisterActivity.this, "请填写正确的手机号");
                    return;
                }
                //请求发送短信
                if (NetworkUtil.checkNetwork(RegisterActivity.this)) {
                    registerPresenter.requestRegisterSms(phone);
                }else{
                    isRequest=false;
                }
            }
        });
        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterLogin();
            }
        });


        et_register_phone.setOnFocusChangeListener(onFocusChangeListener);
        et_register_pwd.setOnFocusChangeListener(onFocusChangeListener);
        et_register_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_register_phone.getText().toString().trim().length() == 0) {
                    iv_register_phone_clear.setVisibility(View.GONE);
                } else {
                    iv_register_phone_clear.setVisibility(View.VISIBLE);
                }
                updateBtStatus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_register_sms.addTextChangedListener(new TextWatcher() {
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
        et_register_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_register_pwd.getText().toString().trim().length() == 0) {
                    iv_register_pwd_clear.setVisibility(View.GONE);
                } else {
                    iv_register_pwd_clear.setVisibility(View.VISIBLE);
                }
                updateBtStatus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iv_register_phone_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_register_phone.setText("");
            }
        });
        iv_register_pwd_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_register_pwd.setText("");
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et_register_phone.getText().toString().trim();
                String sms = et_register_sms.getText().toString().trim();
                String pwd = et_register_pwd.getText().toString().trim();
                registerPresenter.register(phone, sms, CommUtil.shaEncrypt(pwd));
            }
        });
    }

    private void updateBtStatus() {
        String phone = et_register_phone.getText().toString().trim();
        String sms = et_register_sms.getText().toString().trim();
        String pwd = et_register_pwd.getText().toString().trim();

        if (phone.length() == 0 || sms.length() == 0 || pwd.length() == 0) {
            bt_register.setEnabled(false);
        } else {
            bt_register.setEnabled(true);
        }
    }

    @Override
    public void getRegisterSms(String msg) {
        ToastUtil.showToastLong(this, msg);
        if (time != null) {
            time.start();
        }
        bt_get_sms.setEnabled(false);
    }

    @Override
    public void getRegisterSmsFail(String error) {
        ToastUtil.showToastLong(this, error);
    }

    @Override
    public void registerDone(RegBean bean) {
        ToastUtil.showToast(this, bean.getDetail());
        enterLogin();
    }

    public void enterLogin() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }

    @Override
    public void registerFail(String error) {
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
        ProgressBarUtil.showLoadDialog(this);
    }

    @Override
    public void requestEnd() {
        isRequest = false;
        ProgressBarUtil.hideLoadDialogDelay(this);
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
