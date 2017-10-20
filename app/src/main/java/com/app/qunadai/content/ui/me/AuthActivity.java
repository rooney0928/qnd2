package com.app.qunadai.content.ui.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qunadai.MyApp;
import com.app.qunadai.R;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.StatusBean;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.AuthContract;
import com.app.qunadai.content.presenter.v5.AuthPresenter;
import com.app.qunadai.content.ui.bbs.HelpActivity;
import com.app.qunadai.content.view.AuthView;
import com.app.qunadai.third.eventbus.EventMe;
import com.app.qunadai.third.eventbus.EventRefresh;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ReqKey;
import com.app.qunadai.utils.ToastUtil;
import com.moxie.client.model.MxParam;
import com.shinelw.library.ColorArcProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by wayne on 2017/9/15.
 */

public class AuthActivity extends BaseActivity implements AuthContract.View {

    private AuthPresenter authPresenter;

    @BindView(R.id.av_bankcard)
    AuthView av_bankcard;
    @BindView(R.id.av_realinfo)
    AuthView av_realinfo;
    @BindView(R.id.av_ebank)
    AuthView av_ebank;
    @BindView(R.id.av_carrier)
    AuthView av_carrier;
    @BindView(R.id.av_alipay)
    AuthView av_alipay;
    @BindView(R.id.av_email)
    AuthView av_email;
    @BindView(R.id.av_fund)
    AuthView av_fund;
    @BindView(R.id.av_taobao)
    AuthView av_taobao;
    @BindView(R.id.av_credit)
    AuthView av_credit;

    @BindView(R.id.bar1)
    ColorArcProgressBar bar1;

    @BindView(R.id.tv_limit_money)
    TextView tv_limit_money;

    @BindView(R.id.iv_loan_question)
    ImageView iv_loan_question;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("我的信息");
    }

    @Override
    protected View createCenterView() {
        View root = View.inflate(this, R.layout.activity_auth, null);
        return root;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        authPresenter = new AuthPresenter(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initViewData() {
        av_bankcard.setOnClickListener(this);
        av_realinfo.setOnClickListener(this);
        av_ebank.setOnClickListener(this);
        av_carrier.setOnClickListener(this);
        av_alipay.setOnClickListener(this);
        av_email.setOnClickListener(this);
        av_fund.setOnClickListener(this);
        av_taobao.setOnClickListener(this);
        av_credit.setOnClickListener(this);

        iv_loan_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHelp = new Intent(AuthActivity.this, HelpActivity.class);
                startActivity(intentHelp);
            }
        });

        authPresenter.requestPersonValue(getToken());

        bar1.setCurrentValues(5);

    }

    @Override
    public void onClick(View v) {
        if (v instanceof AuthView) {
            if (CommUtil.isNull(getToken())) {
                exeLogin();
                return;
            }
            Bundle bundle = new Bundle();

            MxParam mxParam = new MxParam();
            mxParam.setUserId(PrefUtil.getString(this, PrefKey.PHONE, ""));
            mxParam.setApiKey(MyApp.MX_KEY);
            mxParam.setThemeColor("#31B7C1");//主题色（非必传）
//            mxParam.setAgreementUrl(mainActivity.getSharedPreferValue("agreementUrl"));//自定义协议地址（非必传）
            mxParam.setAgreementEntryText("同意数据获取协议");    //自定义协议相关说明（非必传）
//            mxParam.setCacheDisable(MxParam.PARAM_COMMON_YES);//不使用缓存（非必传）
//            mxParam.setLoadingViewText("验证过程中不会浪费您任何流量\n请稍等片刻");  //设置导入过程中的自定义提示文案，为居中显示
//            mxParam.setQuitDisable(true); //设置导入过程中，触发返回键或者点击actionbar的返回按钮的时候，不执行魔蝎的默认行为


            switch (v.getId()) {

                case R.id.av_ebank:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_ONLINEBANK);
                    break;
                case R.id.av_carrier:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_CARRIER);
                    break;
                case R.id.av_alipay:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_ALIPAY);
                    break;
                case R.id.av_email:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_EMAIL);
                    break;
                case R.id.av_fund:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_FUND);
                    break;
                case R.id.av_taobao:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_TAOBAO);
                    break;
                case R.id.av_credit:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_ZHENGXIN);
                    break;
            }
            if (v.getId() == R.id.av_bankcard || v.getId() == R.id.av_realinfo) {
                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }
                switch (v.getId()) {
                    case R.id.av_bankcard:
                        //进入银行卡认证
                        if (av_bankcard.getStatus() == AuthView.AUTH_YES) {
//                            av_bankcard.setOnClickListener(nullClick);

                        } else if (av_bankcard.getStatus() == AuthView.AUTH_ING) {
                            av_bankcard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToastUtil.showToast(AuthActivity.this, "正在认证中");
                                }
                            });
                        } else {
                            av_bankcard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intentBank = new Intent(AuthActivity.this, BankCardActivity.class);
                                    intentBank.putExtra("titleHide", true);
                                    startActivityForResult(intentBank, ReqKey.REQ_BANK_INFO);
                                }
                            });
                        }

                        break;
                    case R.id.av_realinfo:
                        //进入个人信息认证

                        Intent intentInfo = new Intent(AuthActivity.this, PersonInfoActivity.class);
                        intentInfo.putExtra("titleHide", true);
                        intentInfo.putExtra("isFromDetail", true);
                        startActivityForResult(intentInfo, ReqKey.REQ_BANK_INFO);
                        break;
                }

            } else {
                bundle.putParcelable("param", mxParam);
                Intent intent = new Intent(AuthActivity.this, com.moxie.client.MainActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, ReqKey.REQ_MOXIE);
            }


        } else if (v.getId() == R.id.rl_back) {
            finish();
        }

    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
        showLoading();
    }

    @Override
    public void requestEnd() {
        hideLoading();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ReqKey.REQ_BANK_INFO:
                    authPresenter.requestPersonValue(PrefUtil.getString(this, PrefKey.TOKEN, ""));

                    break;
                case ReqKey.REQ_MOXIE:
                    Bundle b = data.getExtras();              //data为B中回传的Intent
                    String result = b.getString("result");    //result即为回传的值(JSON格式)
//                    json = result;

                    if (TextUtils.isEmpty(result)) {
                        ToastUtil.showToast(this, "没有进行导入操作!");
                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            LogU.t("json0--" + jsonObject.toString());

                            int code = jsonObject.getInt("code");
                            String taskType = jsonObject.getString("taskType");


                            switch (code) {
                                case -1:
                                    ToastUtil.showToast(this, "没有进行导入操作!");

                                    break;
                                case -2:
                                    ToastUtil.showToast(this, "导入失败(服务问题)!");
                                    break;
                                case -3:
                                    ToastUtil.showToast(this, "导入失败(服务异常)!");
                                    break;
                                case -4:
//                                ToastUtil.showToast(getActivity(),"导入失败(");
                                    break;
                                case 0:
//                                ToastUtil.showToast(getActivity(),"导入失败!");
                                    break;
                                case 1:
                                    requestUpdateStates(taskType);
                                    ToastUtil.showToast(this, "导入成功!");
                                    break;
                                case 2:
                                    /**
                                     * 如果用户中途导入魔蝎SDK会出现这个情况，如需获取最终状态请轮询贵方后台接口
                                     * 魔蝎后台会向贵方后台推送Task通知和Bill通知
                                     * Task通知：登录成功/登录失败
                                     * Bill通知：账单通知
                                     */
                                    ToastUtil.showToast(this, "导入中");
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    break;
            }
        }


    }

    private void requestUpdateStates(String taskType) {
        String phone = PrefUtil.getString(this, PrefKey.PHONE, "");
        String token = PrefUtil.getString(this, PrefKey.TOKEN, "");
        authPresenter.updateStatus(phone, taskType, token);
    }

    /**
     * 设置认证状态
     *
     * @param status
     */
    private void setBindStatus(AuthView av, String status) {
        /** 新建,处理中,验证成功,验证失败,处理错误,用户被封禁,高风险,中风险,低风险 */
        //CREATED, PROCESSING, SUCCESS, FAILURE, ERROR, BANNED, HRISK, MRISK, LRISK
        switch (status) {
            case "SUCCESS":
            case "HRISK":
            case "MRISK":
            case "LRISK":
                av.setAuthStatus(AuthView.AUTH_YES);
                break;
            case "PROCESSING":
                av.setAuthStatus(AuthView.AUTH_ING);
                break;
            default:
                av.setAuthStatus(AuthView.AUTH_NO);
                break;
        }

    }


    @Override
    public void getPersonValue(PersonBean bean) {
        tv_limit_money.setText(bean.getContent().getPersonalValue().getValuation() + "");


        //银行卡验证
        setBindStatus(av_bankcard, bean.getContent().getPersonalValue().getBankStatus());
        //真实信息
        setBindStatus(av_realinfo, bean.getContent().getPersonalValue().getRealInfoStatus());
        //网银
        setBindStatus(av_ebank, bean.getContent().getPersonalValue().getEbankStatus());
        //运营商
        setBindStatus(av_carrier, bean.getContent().getPersonalValue().getOperatorStatus());
        //支付宝
        setBindStatus(av_alipay, bean.getContent().getPersonalValue().getAlipayStatus());
        //邮箱
        setBindStatus(av_email, bean.getContent().getPersonalValue().getEmailStatus());
        //公积金
        setBindStatus(av_fund, bean.getContent().getPersonalValue().getFundStatus());
        //淘宝
        setBindStatus(av_taobao, bean.getContent().getPersonalValue().getTaobaoStatus());
        //征信
        setBindStatus(av_credit, bean.getContent().getPersonalValue().getZxStatus());

        switch (bean.getContent().getPersonalValue().getBankStatus()) {
            case "SUCCESS":
            case "HRISK":
            case "MRISK":
            case "LRISK":
            case "PROCESSING":
                PrefUtil.putBoolean(this, PrefKey.BANK_CHECKED, true);
                break;
            default:
                PrefUtil.putBoolean(this, PrefKey.BANK_CHECKED, false);
                break;
        }
        bar1.setCurrentValues(countAuthCount());
    }

    public int countAuthCount() {
        int curr = 0;
        if (av_bankcard.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }
        if (av_realinfo.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }
        if (av_ebank.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }
        if (av_carrier.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }
        if (av_alipay.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }
        if (av_email.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }
        if (av_fund.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }
        if (av_taobao.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }
        if (av_credit.getStatus() == AuthView.AUTH_YES) {
            curr++;
        }

        return curr;
    }

    @Override
    public void getPersonValueFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void getStatus(StatusBean bean) {
        authPresenter.requestPersonValue(getToken());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        if ("auth".equalsIgnoreCase(event.getType())) {
            authPresenter.requestPersonValue(getToken());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
