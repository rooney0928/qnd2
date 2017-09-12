package com.app.qunadai.content.ui.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.LoginContract;
import com.app.qunadai.content.presenter.LoginPresenter;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.third.eventbus.EventLogin;
import com.app.qunadai.utils.AppManager;
import com.app.qunadai.utils.CodeUtil;
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
 * Created by wayne on 2017/5/11.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginPresenter loginPresenter;

    @BindView(R.id.bt_style_pwd)
    Button bt_style_pwd;
    @BindView(R.id.bt_style_sms)
    Button bt_style_sms;

    @BindView(R.id.rl_login_pwd)
    RelativeLayout rl_login_pwd;
    @BindView(R.id.rl_login_sms)
    RelativeLayout rl_login_sms;

    @BindView(R.id.et_login_phone)
    EditText et_login_phone;
    @BindView(R.id.et_login_pwd)
    EditText et_login_pwd;
    @BindView(R.id.et_login_sms)
    EditText et_login_sms;

    @BindView(R.id.bt_get_sms)
    Button bt_get_sms;

    @BindView(R.id.iv_phone_clear)
    ImageView iv_phone_clear;
    @BindView(R.id.iv_pwd_clear)
    ImageView iv_pwd_clear;
    @BindView(R.id.cb_pwd_hide)
    CheckBox cb_pwd_hide;

    @BindView(R.id.bt_login)
    Button bt_login;

    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.tv_forget_password)
    TextView tv_forget_password;

    @BindView(R.id.rl_login_sms_pic)
    RelativeLayout rl_login_sms_pic;
    @BindView(R.id.et_login_sms_pic)
    EditText et_login_sms_pic;
    @BindView(R.id.iv_get_code)
    ImageView iv_get_code;

    //0密码，2验证码
    private int login_type;
    private static final int TYPE_PWD = 0;
    private static final int TYPE_SMS = 2;

    private boolean isRequest;
    private TimeCount time;


    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitleText("登录");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_login, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        loginPresenter = new LoginPresenter(this);
        rl_login_sms.setVisibility(View.GONE);
        rl_login_sms_pic.setVisibility(View.GONE);
        time = new TimeCount(60000, 1000);
        bt_style_pwd.setSelected(true);
        login_type = TYPE_PWD;

        //记住的密码
        String phone = PrefUtil.getString(this, PrefKey.PHONE, "");
        et_login_phone.setText(phone);
    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            iv_phone_clear.setVisibility(View.GONE);
            iv_pwd_clear.setVisibility(View.GONE);

            switch (v.getId()) {
                case R.id.et_login_phone:
                    if (et_login_phone.getText().toString().trim().length() != 0) {
                        iv_phone_clear.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.et_login_pwd:
                    if (et_login_pwd.getText().toString().trim().length() != 0) {
                        iv_pwd_clear.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };

    @Override
    public void initViewData() {
        //结束其他所有页面
//        AppManager.closeActivityWithout(this);


        bt_style_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换密码模式
                changeType(TYPE_PWD);
            }
        });
        bt_style_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换短信模式
                changeType(TYPE_SMS);
            }
        });
        et_login_phone.setOnFocusChangeListener(onFocusChangeListener);
        et_login_pwd.setOnFocusChangeListener(onFocusChangeListener);
        et_login_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone = et_login_phone.getText().toString().trim();
                String pwd = et_login_pwd.getText().toString().trim();


                if (phone.length() == 0) {
                    iv_phone_clear.setVisibility(View.GONE);
                } else {
                    iv_phone_clear.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
                    bt_login.setEnabled(false);
                } else {
                    bt_login.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_login_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone = et_login_phone.getText().toString().trim();
                String pwd = et_login_pwd.getText().toString().trim();

                if (pwd.length() == 0) {
                    iv_pwd_clear.setVisibility(View.GONE);
                } else {
                    iv_pwd_clear.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
                    bt_login.setEnabled(false);
                } else {
                    bt_login.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_login_sms.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone = et_login_phone.getText().toString().trim();
                String sms = et_login_sms.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(sms)) {
                    bt_login.setEnabled(false);
                } else {
                    bt_login.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iv_phone_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_login_phone.setText("");
            }
        });
        iv_pwd_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_login_pwd.setText("");
            }
        });
        bt_get_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送登录验证码
                String code = CodeUtil.getInstance().getCode();
                String inputCode = CommUtil.getText(et_login_sms_pic);
                if (!code.equalsIgnoreCase(inputCode)) {
                    ToastUtil.showToast(LoginActivity.this, "请填写正确的图形验证码");
                    return;
                }


                if (NetworkUtil.checkNetwork(LoginActivity.this)) {
                    if (isRequest) {
                        ToastUtil.showToast(LoginActivity.this, "请稍后");
                        return;
                    }
                    isRequest = true;
                    String phone = et_login_phone.getText().toString().trim();
                    if (phone.length() == 0) {
                        isRequest = false;
                        ToastUtil.showToast(LoginActivity.this, "请填写正确的手机号");
                        return;
                    }

                    loginPresenter.requestLoginSms(phone);
                }


            }
        });


        cb_pwd_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int show = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                int hide = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
                et_login_pwd.setInputType(isChecked ? show : hide);
                // 使光标始终在最后位置
                Editable etable = et_login_pwd.getText();
                Selection.setSelection(etable, etable.length());
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入注册环节
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
        tv_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入忘记密码环节
                Intent intentForget = new Intent(LoginActivity.this, ForgetPwdActivity.class);
                startActivity(intentForget);
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtil.checkNetwork(LoginActivity.this)) {
                    loginSystem();
                }
            }
        });

        //初始化密码是否可见
        int show = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
        int hide = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        et_login_pwd.setInputType(cb_pwd_hide.isChecked() ? show : hide);

        String phone = et_login_phone.getText().toString().trim();
        String pwd = et_login_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
            bt_login.setEnabled(false);
        } else {
            bt_login.setEnabled(true);
        }


        //图形验证码
        setPicCode();
        iv_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPicCode();

            }
        });
    }

    public void setPicCode() {
        Bitmap b = CodeUtil.getInstance().createBitmap();
        iv_get_code.setImageBitmap(b);
    }

    private void loginSystem() {
        String phone = et_login_phone.getText().toString().trim();
        String pwd = et_login_pwd.getText().toString().trim();
        String sms = et_login_sms.getText().toString().trim();
        if (phone.length() == 0) {
            ToastUtil.showToast(this, "请填写完整");
            return;
        }
        switch (login_type) {
            case TYPE_PWD:
                if (pwd.length() == 0) {
                    ToastUtil.showToast(this, "请填写完整");
                    return;
                }
                loginPresenter.loginByPwd(phone, CommUtil.shaEncrypt(pwd));
                break;
            case TYPE_SMS:
                if (sms.length() == 0) {
                    ToastUtil.showToast(this, "请填写完整");
                    return;
                }
                loginPresenter.loginBySms(phone, sms);
                break;
        }
    }

    private void changeType(int type) {
        login_type = type;
        bt_style_pwd.setSelected(false);
        bt_style_sms.setSelected(false);
        rl_login_pwd.setVisibility(View.GONE);
        rl_login_sms.setVisibility(View.GONE);
        rl_login_sms_pic.setVisibility(View.GONE);


        String phone = et_login_phone.getText().toString().trim();
        String pwd = et_login_pwd.getText().toString().trim();
        String sms = et_login_sms.getText().toString().trim();

        switch (type) {
            case TYPE_PWD:
                bt_style_pwd.setSelected(true);
                rl_login_pwd.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
                    bt_login.setEnabled(false);
                } else {
                    bt_login.setEnabled(true);
                }
                break;
            case TYPE_SMS:
                bt_style_sms.setSelected(true);
                rl_login_sms.setVisibility(View.VISIBLE);
                rl_login_sms_pic.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(sms)) {
                    bt_login.setEnabled(false);
                } else {
                    bt_login.setEnabled(true);
                }
                break;
        }
    }


    @Override
    public void getLoginSms(String msg) {
        ToastUtil.showToastLong(this, "短信已发送");
        if (time != null) {
            time.start();
        }
        bt_get_sms.setEnabled(false);
    }

    @Override
    public void getLoginSmsFail(String error) {
        ToastUtil.showToastLong(this, error);
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void loginDone(BaseBean<Token> token) {
        ToastUtil.showToastLong(this, "恭喜您，登录成功");
//        EventBus.getDefault().post(new EventClose("main"));

        PrefUtil.putString(this, PrefKey.TOKEN, token.getContent().getAccess_token());
        PrefUtil.putString(this, PrefKey.PHONE, CommUtil.getText(et_login_phone));
        if (login_type == TYPE_PWD) {
            //密码模式则存2个密码(真假),标记自动登录
            PrefUtil.putString(this, PrefKey.PWD_ENCODE, CommUtil.shaEncrypt(CommUtil.getText(et_login_pwd)));
            PrefUtil.putString(this, PrefKey.PWD, CommUtil.getText(et_login_pwd));
            PrefUtil.putBoolean(this, PrefKey.AUTO_LOGIN, true);
        } else {
            PrefUtil.putBoolean(this, PrefKey.AUTO_LOGIN, false);
        }


        //延迟500毫秒关闭swipe
        Observable.timer(200, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

//                                Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
//                                startActivity(intentMain);
//                                finish();

                                EventBus.getDefault().post(new EventLogin());
                                finish();
                            }
                        });
                    }
                }
        );


    }

    @Override
    public void loginFail(String error) {
        ToastUtil.showToastLong(this, error);
    }

    @Override
    public void requestStart() {
//        ProgressBarUtil.showLoadDialog(this);
        showLoading();
        //不用添加view的关闭键盘
        if (manager != null) {
            manager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                    0);
        }
    }


    @Override
    public void requestEnd() {
        isRequest = false;
//        ProgressBarUtil.hideLoadDialogDelay(this);
        hideLoading();
    }


//    private long mPressedTime = 0;
//
//    @Override
//    public void onBackPressed() {
//        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
//        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
//            ToastUtil.showToast(this, "再按一次退出程序");
//            mPressedTime = mNowTime;
//        } else {
//            //退出程序
//            finish();
//            System.exit(0);
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }
//    }


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
