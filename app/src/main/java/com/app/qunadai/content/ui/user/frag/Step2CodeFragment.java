package com.app.qunadai.content.ui.user.frag;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.SmsBean;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.Sign2Contract;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.content.presenter.v5.Sign2Presenter;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.third.eventbus.EventTurn;
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

import static com.app.qunadai.MyApp.context;

/**
 * Created by wayne on 2017/9/11.
 */

public class Step2CodeFragment extends BaseFragment implements Sign2Contract.View, FragmentBackPressed, View.OnClickListener {
    private Sign2Presenter sign2Presenter;

    @BindView(R.id.tv_subtitle)
    TextView tv_subtitle;

    @BindView(R.id.iv_login_back)
    ImageView iv_login_back;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.cb_code_right)
    CheckBox cb_code_right;
    @BindView(R.id.tv_code_time)
    TextView tv_code_time;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.tv_send_code)
    TextView tv_send_code;
    TimeCount timeCount;

    public static Step2CodeFragment getInstance() {
        Step2CodeFragment step2CodeFragment = new Step2CodeFragment();
        Bundle bundle = new Bundle();
        step2CodeFragment.setArguments(bundle);
        return step2CodeFragment;
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_sign_step2, null);
        return root;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    String phone;
    String shaCode;
    String smsType;

    @Override
    protected void initData() {
        sign2Presenter = new Sign2Presenter(this);
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==4){
                    boolean result = CommUtil.shaEncrypt(CommUtil.getText(et_code)).equalsIgnoreCase(shaCode);
                    if(result){
                        tv_code_time.setVisibility(View.GONE);
                        cb_code_right.setVisibility(View.VISIBLE);
                    }
                }


                testCode();
//                if (timeCount != null) {
//                    timeCount.onFinish();
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tv_send_code.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        iv_login_back.setOnClickListener(this);
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
        EventBus.getDefault().post(new EventProgress(true, "sign"));

    }

    @Override
    public void requestEnd() {
        EventBus.getDefault().post(new EventProgress(false, "sign"));

    }

    public void startTimer() {
        if (timeCount != null) {
            timeCount.onFinish();
        }
        timeCount = new TimeCount(60000, 1000);

        phone = PrefUtil.getString(getActivity(), PrefKey.TEMP_PHONE, "");
        shaCode = PrefUtil.getString(getActivity(), PrefKey.TEMP_SHA_CODE, "");
        smsType = PrefUtil.getString(getActivity(), PrefKey.SMS_TYPE, "");

        et_code.setText("");



        tv_subtitle.setText("我们向" + phone + "发送了一个4位数的验证码。请在消息框中输入");
        timeCount.start();

        tv_code_time.setVisibility(View.VISIBLE);
        cb_code_right.setVisibility(View.GONE);
        tv_send_code.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new EventTurn(0, "sign"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_back:
                EventBus.getDefault().post(new EventTurn(0, "sign"));
                break;
            case R.id.tv_send_code:
                if (!NetworkUtil.checkNetwork(getActivity())) {
                    return;
                }

                if (smsType.equalsIgnoreCase("reg")) {
                    sign2Presenter.sendRegSms(PrefUtil.getString(getActivity(), PrefKey.TEMP_PHONE, ""));
                } else if (smsType.equalsIgnoreCase("login")) {
                    sign2Presenter.sendLoginSms(PrefUtil.getString(getActivity(), PrefKey.TEMP_PHONE, ""));
                } else if (smsType.equalsIgnoreCase("forget")) {
                    sign2Presenter.sendForgetSms(PrefUtil.getString(getActivity(), PrefKey.TEMP_PHONE, ""));
                }


                break;
            case R.id.tv_submit:
                if (!NetworkUtil.checkNetwork(getActivity())) {
                    return;
                }

                if (!CommUtil.shaEncrypt(CommUtil.getText(et_code)).equalsIgnoreCase(shaCode)) {
                    ToastUtil.showToast(getActivity(), "验证码错误");
                    return;
                }


                if (smsType.equalsIgnoreCase("reg")) {
                    //注册的提交
                    PrefUtil.putString(getActivity(), PrefKey.TEMP_CODE, CommUtil.getText(et_code));
                    EventBus.getDefault().post(new EventTurn(2, "sign"));
                } else if (smsType.equalsIgnoreCase("login")) {
                    //短信登录的提交
                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    String imei = tm.getDeviceId();
                    sign2Presenter.loginBySms(phone, CommUtil.getText(et_code), imei);
                } else if (smsType.equalsIgnoreCase("forget")) {
                    PrefUtil.putString(getActivity(), PrefKey.TEMP_CODE, CommUtil.getText(et_code));
                    EventBus.getDefault().post(new EventTurn(2, "sign"));
                }
                break;
        }
    }

    @Override
    public void getRegisterSms(BaseBean<SmsBean> msg) {
        shaCode = msg.getContent().getVc();

        ToastUtil.showToast(getActivity(), "短信已发送");
        timeCount.ready();
        timeCount.start();

    }

    @Override
    public void getRegisterSmsFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void getLoginSms(BaseBean<SmsBean> bean) {
        shaCode = bean.getContent().getVc();

        ToastUtil.showToast(getActivity(), "短信已发送");
        timeCount.ready();
        timeCount.start();
    }

    @Override
    public void getLoginSmsFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void getForgetSms(BaseBean<SmsBean> bean) {
        shaCode = bean.getContent().getVc();

        ToastUtil.showToast(getActivity(), "短信已发送");
        timeCount.ready();
        timeCount.start();
    }

    @Override
    public void getForgetSmsFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void loginDone(BaseBean<Token> token) {

        PrefUtil.putString(getActivity(), PrefKey.TOKEN, token.getContent().getAccess_token());
        PrefUtil.putString(getActivity(), PrefKey.PHONE, phone);

        ToastUtil.showToast(getActivity(), "恭喜您！登录成功！");
        getActivity().finish();
    }

    @Override
    public void loginFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }


    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            tv_send_code.setEnabled(false);
        }

        public void ready() {
            tv_send_code.setEnabled(false);
            cb_code_right.setVisibility(View.GONE);
            tv_code_time.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //防止8999，7998类似毫秒值
            int time = (int) (Math.round((double) millisUntilFinished / 1000) - 1);
            // 计时过程显示
            if (tv_code_time != null) {
                tv_code_time.setText(time + "s");
            }
        }

        @Override
        public void onFinish() {
            // 计时完毕时触发
            testCode();
            tv_code_time.setVisibility(View.GONE);
            cb_code_right.setVisibility(View.VISIBLE);
            tv_send_code.setEnabled(true);

        }
    }

    private void testCode() {


        String inputCode = CommUtil.getText(et_code);

        cb_code_right.setChecked(CommUtil.shaEncrypt(inputCode).equalsIgnoreCase(shaCode));
        tv_submit.setEnabled(CommUtil.shaEncrypt(inputCode).equalsIgnoreCase(shaCode));

    }


}
