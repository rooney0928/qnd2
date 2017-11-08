package com.app.qunadai.content.ui.cpl.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.cpl.UserInfo;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.third.eventbus.EventCplInfo;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.DialogUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wayne on 2017/11/7.
 */

public class CplInfo1Fragment extends BaseFragment implements FragmentBackPressed {
    //姓名
    @BindView(R.id.et_cpl_name)
    EditText et_cpl_name;
    //身份证
    @BindView(R.id.et_cpl_idcard)
    EditText et_cpl_idcard;
    //微信
    @BindView(R.id.et_cpl_wechat)
    EditText et_cpl_wechat;
    //qq
    @BindView(R.id.et_cpl_qq)
    EditText et_cpl_qq;
    //学历
    @BindView(R.id.rl_cpl_edu)
    RelativeLayout rl_cpl_edu;
    @BindView(R.id.tv_cpl_edu)
    TextView tv_cpl_edu;

    @BindView(R.id.tv_cpl_submit)
    TextView tv_cpl_submit;


    public static CplInfo1Fragment getInstance() {
        CplInfo1Fragment cplInfo1Fragment = new CplInfo1Fragment();
        Bundle bundle = new Bundle();
        cplInfo1Fragment.setArguments(bundle);
        return cplInfo1Fragment;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    private UserInfo tempUserInfo;

    @Override
    protected void initData() {
        tempUserInfo = new UserInfo();

    }

    String[] array;


//    BottomSheetDialog dialog;

    @OnClick({R.id.rl_cpl_edu, R.id.tv_cpl_submit})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_cpl_edu:
                array = getActivity().getResources().getStringArray(R.array.cpl_edu);
                DialogUtil.showBottomSheetDialog(getActivity(), array, new OnCompatItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        tv_cpl_edu.setText(array[position]);
                        tempUserInfo.setEdu(position + 1);
                    }
                });

                break;
            case R.id.tv_cpl_submit:
                tempUserInfo.setName(CommUtil.getText(et_cpl_name));
                tempUserInfo.setIdcard(CommUtil.getText(et_cpl_idcard));
                tempUserInfo.setWechat(CommUtil.getText(et_cpl_wechat));
                tempUserInfo.setQq(CommUtil.getText(et_cpl_qq));

//                EventBus.getDefault().post(new EventTurn(1, "cplInfo"));
                if (!CommUtil.isNull(et_cpl_name)
                        && CheckUtil.isIDCard(CommUtil.getText(et_cpl_idcard))
                        && !CommUtil.isNull(et_cpl_wechat)
                        && !CommUtil.isNull(et_cpl_qq)
                        && !CommUtil.isNull(tv_cpl_edu)
                        ) {

//                    ToastUtil.showToast(getActivity(), "数据ok");
                    EventBus.getDefault().post(new EventCplInfo(tempUserInfo,1));
                    EventBus.getDefault().post(new EventTurn(1, "cplInfo"));
                } else {
                    ToastUtil.showToast(getActivity(), "数据格式不正确");
                }


                break;
        }
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_auth1_cpl, null);
        return root;
    }


    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {

    }

    @Override
    public void requestEnd() {

    }

    @Override
    public void onBackPressed() {
        getActivity().finish();
    }

}
