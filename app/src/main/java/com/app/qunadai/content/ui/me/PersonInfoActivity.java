package com.app.qunadai.content.ui.me;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.PersonInfo;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.PersonInfoContract;
import com.app.qunadai.content.presenter.PersonInfoPresenter;
import com.app.qunadai.third.address.model.AddressDetailsEntity;
import com.app.qunadai.third.address.model.AddressModel;
import com.app.qunadai.third.address.utils.JsonUtil;
import com.app.qunadai.third.address.utils.Utils;
import com.app.qunadai.third.address.view.ChooseAddressWheel;
import com.app.qunadai.third.address.view.listener.OnAddressChangeListener;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/13.
 */

public class PersonInfoActivity extends BaseActivity implements PersonInfoContract.View {
    private PersonInfoPresenter personInfoPresenter;


    @BindView(R.id.ll_info_amount)
    LinearLayout ll_info_amount;
    @BindView(R.id.rl_info_period)
    RelativeLayout rl_info_period;
    @BindView(R.id.rl_info_job)
    RelativeLayout rl_info_job;
    @BindView(R.id.ll_info_income)
    LinearLayout ll_info_income;
    @BindView(R.id.rl_info_edu)
    RelativeLayout rl_info_edu;
    @BindView(R.id.rl_info_marry)
    RelativeLayout rl_info_marry;
    @BindView(R.id.rl_info_live)
    RelativeLayout rl_info_live;


    @BindView(R.id.et_info_amount)
    EditText et_info_amount;
    @BindView(R.id.tv_info_period)
    TextView tv_info_period;
    @BindView(R.id.tv_info_job)
    TextView tv_info_job;
    @BindView(R.id.et_info_income)
    EditText et_info_income;
    @BindView(R.id.tv_info_edu)
    TextView tv_info_edu;
    @BindView(R.id.tv_info_marry)
    TextView tv_info_marry;
    @BindView(R.id.tv_info_live)
    TextView tv_info_live;

    @BindView(R.id.bt_submit)
    Button bt_submit;

    @BindView(R.id.verify_title_bar)
    View verify_title_bar;

    @BindView(R.id.view_progress_bar)
    View view_progress_bar;
    @BindView(R.id.rl_next_progress_area)
    RelativeLayout rl_next_progress_area;
    @BindView(R.id.iv_progress_two)
    ImageView iv_progress_two;
    @BindView(R.id.tv_verify_two)
    TextView tv_verify_two;


    InputMethodManager manager;

    private ChooseAddressWheel chooseAddressWheel;

    String address = "";

    JSONObject infoObj;
    boolean isFromDetail;
    boolean canEnterBank;

    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitleText("个人信息");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_personal_info, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        isFromDetail = getIntent().getBooleanExtra("isFromDetail", false);
        boolean hideTitle = getIntent().getBooleanExtra("titleHide", false);
        if (hideTitle) {
            verify_title_bar.setVisibility(View.GONE);
        }

        personInfoPresenter = new PersonInfoPresenter(this);
        chooseAddressWheel = new ChooseAddressWheel(this);
        chooseAddressWheel.setOnAddressChangeListener(new OnAddressChangeListener() {

            @Override
            public void onAddressChange(String province, String city, String district) {
                address = province + "," + city + "," + district;
                tv_info_live.setText(province + " " + city + " " + district);
            }
        });
        initAddressData();
//        et_info_income.setText("");

        //修改进度条样式
        boolean bankChecked = PrefUtil.getBoolean(this, PrefKey.BANK_CHECKED, false);
        if (bankChecked) {
            view_progress_bar.setBackgroundResource(R.drawable.shape_line_info_ok);
            rl_next_progress_area.setBackgroundResource(R.drawable.shape_round_text_bg_curr);
            iv_progress_two.setVisibility(View.VISIBLE);
            tv_verify_two.setVisibility(View.GONE);
        }
        if (NetworkUtil.checkNetwork(this)) {
            personInfoPresenter.requestPersonInfo(PrefUtil.getString(this, PrefKey.TOKEN, ""));
            personInfoPresenter.requestPersonValue(PrefUtil.getString(this, PrefKey.TOKEN, ""));
        }

    }

    @Override
    public void initViewData() {
        String infoJson = Utils.readAssert(this, "info.json");
        try {
            infoObj = new JSONObject(infoJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ll_info_amount.setOnClickListener(this);
        rl_info_period.setOnClickListener(this);
        rl_info_job.setOnClickListener(this);
        ll_info_income.setOnClickListener(this);
        rl_info_edu.setOnClickListener(this);
        rl_info_marry.setOnClickListener(this);
        rl_info_live.setOnClickListener(this);

        bt_submit.setOnClickListener(this);
    }

    @Override
    public void getPersonInfo(PersonInfo bean) {
        PersonInfo.ContentBean.UserCreditInfoBean content = bean.getContent().getUserCreditInfo();
        try {
            JSONObject periodObj = infoObj.getJSONObject("period");
            JSONObject jobObj = infoObj.getJSONObject("job");
            JSONObject eduObj = infoObj.getJSONObject("edu");
            JSONObject marryObj = infoObj.getJSONObject("marry");


            if (!TextUtils.isEmpty(content.getLoanAmount())) {
                et_info_amount.setText(content.getLoanAmount());
            }
            //期限
            String tempPeriod = periodObj.optString(content.getLoanDeadLine(), null);
            tv_info_period.setText(tempPeriod != null ? tempPeriod : content.getLoanDeadLine());
            if (CommUtil.isNull(tv_info_period.getText().toString())) {
                tv_info_period.setText("请选择");
            }

            //职业
            String tempJob = jobObj.optString(content.getEmploymentStatus(), null);
            tv_info_job.setText(tempJob != null ? tempJob : content.getEmploymentStatus());
            if (CommUtil.isNull(tv_info_job.getText().toString())) {
                tv_info_job.setText("请选择");
            }

            //收入
            if (!TextUtils.isEmpty(content.getHouseholdIncome())) {
                et_info_income.setText(content.getHouseholdIncome());
            }

            //教育
            String tempEdu = eduObj.optString(content.getEducationLevel(), null);
            tv_info_edu.setText(tempEdu != null ? tempEdu : content.getEducationLevel());
            if (CommUtil.isNull(tv_info_edu.getText().toString())) {
                tv_info_edu.setText("请选择");
            }
            //婚姻
            String tempMar = marryObj.optString(content.getMaritalStatus(), null);
            tv_info_marry.setText(tempEdu != null ? tempMar : content.getMaritalStatus());
            if (CommUtil.isNull(tv_info_marry.getText().toString())) {
                tv_info_marry.setText("请选择");
            }
            //请选择
            if (CommUtil.isNull(content.getHabitualResidence())) {
                tv_info_live.setText("请选择");
            } else if (content.getHabitualResidence().indexOf(",") != -1) {
                String[] tempAddress = content.getHabitualResidence().split(",");
                String tempAddr = "";
                for (String s : tempAddress) {
                    tempAddr += s + " ";
                }
                tv_info_live.setText(tempAddr.trim());

                address = tempAddr.trim().replace(" ", ",");
                tv_info_live.setText(address);
            } else {
                address = content.getHabitualResidence();
                tv_info_live.setText(address);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void getPersonInfoFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void setPersonInfo(PersonInfo bean) {
        if (isFromDetail) {
            finish();
        } else {
            if (canEnterBank) {
                ToastUtil.showToast(this, bean.getDetail());
                Intent intent = new Intent(this, BankCardActivity.class);
                startActivity(intent);
            } else {
                EventBus.getDefault().post(new EventTurn(1));

                finish();
            }
        }

    }

    @Override
    public void setPersonInfoFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void getPersonValue(PersonBean value) {
        PersonBean.ContentBean.PersonalValueBean info = value.getContent().getPersonalValue();
        switch (info.getBankStatus()) {
            case "SUCCESS":
            case "HRISK":
            case "MRISK":
            case "LRISK":
                canEnterBank = false;
                break;
            case "PROCESSING":
                canEnterBank = false;
                break;
            default:
                canEnterBank = true;
                break;
        }
    }

    @Override
    public void getPersonValueFail(String error) {
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_info_amount:
                et_info_amount.setFocusable(true);
                break;
            case R.id.rl_info_period:
                String[] periods = getResources().getStringArray(R.array.info_period);
                chooseByMultiChoice(periods, new OnChoiceListener() {
                    @Override
                    public void itemSelected(String item) {
                        tv_info_period.setText(item);
                    }
                });
                break;
            case R.id.rl_info_job:
                String[] jobs = getResources().getStringArray(R.array.info_job);
                chooseByMultiChoice(jobs, new OnChoiceListener() {
                    @Override
                    public void itemSelected(String item) {
                        tv_info_job.setText(item);
                    }
                });
                break;
            case R.id.ll_info_income:
                et_info_income.setFocusable(true);
                break;
            case R.id.rl_info_edu:
                String[] edus = getResources().getStringArray(R.array.info_edu);
                chooseByMultiChoice(edus, new OnChoiceListener() {
                    @Override
                    public void itemSelected(String item) {
                        tv_info_edu.setText(item);
                    }
                });
                break;
            case R.id.rl_info_marry:
                String[] marrys = getResources().getStringArray(R.array.info_marry);
                chooseByMultiChoice(marrys, new OnChoiceListener() {
                    @Override
                    public void itemSelected(String item) {
                        tv_info_marry.setText(item);
                    }
                });
                break;
            case R.id.rl_info_live:
                Utils.hideKeyBoard(this);
                chooseAddressWheel.show(rl_info_live);
                break;
            case R.id.bt_submit:
                submit();
                break;
        }

        super.onClick(v);

    }

    private void submit() {
        String mAmount = CommUtil.getText(et_info_amount);
        String mPeriod = CommUtil.getText(tv_info_period);
        String mJob = CommUtil.getText(tv_info_job);
        String mIncome = CommUtil.getText(et_info_income);
        String mEdu = CommUtil.getText(tv_info_edu);
        String mMarry = CommUtil.getText(tv_info_marry);
        String mAddress = address;
//        Intent intent = new Intent(this, BankCardActivity.class);
//        startActivity(intent);

        if (!CommUtil.isNumber(mAmount)) {
            ToastUtil.showToast(this, "请填写正确的借款额度");
            return;
        }
        if (TextUtils.isEmpty(mPeriod)) {
            ToastUtil.showToast(this, "请选择借款期限");
            return;
        }
        if (TextUtils.isEmpty(mJob)) {
            ToastUtil.showToast(this, "请选择职业情况");
            return;
        }
        if (!CommUtil.isNumber(mIncome)) {
            ToastUtil.showToast(this, "请填写正确的家庭收入");
            return;
        }
        if (TextUtils.isEmpty(mEdu)) {
            ToastUtil.showToast(this, "请选择教育程度");
            return;
        }
        if (TextUtils.isEmpty(mMarry)) {
            ToastUtil.showToast(this, "请选择婚姻状况");
            return;
        }
        if (TextUtils.isEmpty(mAddress)) {
            ToastUtil.showToast(this, "请选择常居住地");
            return;
        }

//        ToastUtil.showToast(this, "可以提交了");
        personInfoPresenter.setPersonInfo(PrefUtil.getString(this, PrefKey.TOKEN, "")
                , mAmount, mPeriod, mJob, mIncome, mEdu, mMarry, mAddress);
    }

    AlertDialog dialog;

    public void chooseByMultiChoice(final String[] list, final OnChoiceListener listener) {
//        DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//        int width = metric.widthPixels;     // 屏幕宽度（像素）
//        int height = metric.heightPixels;   // 屏幕高度（像素）

//        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
//        p.height = (int) (width * 0.5); // 高度设置为屏幕的0.5
//        p.width = (int) (height * 0.8); // 宽度设置为屏幕的0.8

//        int w = (int) (width * 0.8);
//        int h = (int) (height * 0.5);

        AlertDialog.Builder listDialog = new AlertDialog.Builder(this);
        listDialog.setTitle("请选择");
        listDialog.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                listener.itemSelected(list[which]);
//                LogU.t(list[which]);
            }
        });
        dialog = listDialog.show();

//        dialog.getWindow().setLayout(w,h);

//        listDialog.show().getWindow().setAttributes(p);
    }

    interface OnChoiceListener {
        void itemSelected(String item);
    }

    private void initAddressData() {
        String address = Utils.readAssert(this, "address.json");
        AddressModel model = JsonUtil.parseJson(address, AddressModel.class);
        if (model != null) {
            AddressDetailsEntity data = model.Result;
            if (data == null) return;
            if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
//                chooseAddressWheel.setProvince(data.ProvinceItems.Province);
                chooseAddressWheel.setProvince(data.ProvinceItems.Province);
                chooseAddressWheel.defaultValue(data.Province, data.City, data.Area);
            }
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100) //在ui线程执行 优先级100
    public void onReceive(EventClose event) {
        LogU.t("close");
        if ("info".equalsIgnoreCase(event.getPage())) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
