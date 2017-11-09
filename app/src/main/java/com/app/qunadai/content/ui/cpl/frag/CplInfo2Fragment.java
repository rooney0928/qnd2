package com.app.qunadai.content.ui.cpl.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wayne on 2017/11/7.
 */

public class CplInfo2Fragment extends BaseFragment implements FragmentBackPressed {

    //省市区
    @BindView(R.id.ll_cpl_location)
    LinearLayout ll_cpl_location;
    @BindView(R.id.tv_cpl_location)
    TextView tv_cpl_location;

    //居住地址
    @BindView(R.id.et_cpl_live)
    EditText et_cpl_live;

    //是否缴纳社保
    @BindView(R.id.ll_cpl_social)
    LinearLayout ll_cpl_social;
    @BindView(R.id.tv_cpl_social)
    TextView tv_cpl_social;

    //紧急联系人
    @BindView(R.id.et_cpl_warning_man)
    EditText et_cpl_warning_man;

    //紧急联系人关系
    @BindView(R.id.ll_cpl_warning_relative)
    LinearLayout ll_cpl_warning_relative;
    @BindView(R.id.tv_cpl_warning_relative)
    TextView tv_cpl_warning_relative;

    //紧急联系人电话
    @BindView(R.id.et_cpl_warning_phone)
    EditText et_cpl_warning_phone;

    @BindView(R.id.tv_cpl_submit)
    TextView tv_cpl_submit;

    private UserInfo tempUserInfo;


    public static CplInfo2Fragment getInstance() {
        CplInfo2Fragment cplInfo2Fragment = new CplInfo2Fragment();
        Bundle bundle = new Bundle();
        cplInfo2Fragment.setArguments(bundle);
        return cplInfo2Fragment;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        tempUserInfo = new UserInfo();
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_auth2_cpl, null);
        return root;
    }

    String[] array;

    @OnClick({R.id.ll_cpl_location, R.id.ll_cpl_social, R.id.ll_cpl_warning_relative, R.id.tv_cpl_submit})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.ll_cpl_location:
                //详细属性设置，如果不需要自定义样式的话，可以直接使用默认的，去掉下面的属性设置，直接build()即可。
                CityConfig cityConfig = new CityConfig.Builder(getActivity())
                        .title("选择地区")
                        .titleBackgroundColor("#E9E9E9")
                        .textSize(18)
//                        .titleTextColor("#585858")
//                        .textColor("0xFF585858")
                        .confirTextColor("#31B7C1")
//                        .cancelTextColor("#000000")

                        .province("直辖市")
                        .city("北京")
                        .district("东城区")
                        .visibleItemsCount(5)
                        .provinceCyclic(false)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .itemPadding(5)
                        .setCityInfoType(CityConfig.CityInfoType.BASE)
                        .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                        .build();

                //配置属性
                CityPickerView cityPicker = new CityPickerView(cityConfig);
                cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean provinceBean, CityBean cityBean, DistrictBean districtBean) {
//                        String province = provinceBean.getName();
//                        String city = cityBean.getName();
//                        String area = districtBean.getName();
//                        tv_cpl_location.setText(province + "-" + city + "-" + area);
                        StringBuilder sb = new StringBuilder();
                        if (provinceBean.getName().equalsIgnoreCase("直辖市")) {
                            sb.append(cityBean.getName());
                            tempUserInfo.setProvince(cityBean.getName());
                        } else {
                            sb.append(provinceBean.getName());
                            tempUserInfo.setProvince(provinceBean.getName());
                        }
                        sb.append("-" + cityBean.getName());
                        tempUserInfo.setCity(cityBean.getName());

                        if (districtBean != null) {
                            sb.append("-" + districtBean.getName());
                            tempUserInfo.setDistrict(districtBean.getName());
                        }

                        tv_cpl_location.setText(sb.toString());

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                cityPicker.show();
                break;
            case R.id.ll_cpl_social:
                array = getActivity().getResources().getStringArray(R.array.cpl_social);
                DialogUtil.showBottomSheetDialog(getActivity(), array, new OnCompatItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        tv_cpl_social.setText(array[position]);
                        tempUserInfo.setShebao(position + 1);
                    }
                });
                break;
            case R.id.ll_cpl_warning_relative:
                array = getActivity().getResources().getStringArray(R.array.cpl_relative);
                DialogUtil.showBottomSheetDialog(getActivity(), array, new OnCompatItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        tv_cpl_warning_relative.setText(array[position]);
                        tempUserInfo.setContactType(position);
                    }
                });

                break;
            case R.id.tv_cpl_submit:
                tempUserInfo.setLiving(CommUtil.getText(et_cpl_live));
                tempUserInfo.setContactName(CommUtil.getText(et_cpl_warning_man));
                tempUserInfo.setContactCell(CommUtil.getText(et_cpl_warning_phone));

//                EventBus.getDefault().post(new EventTurn(1, "cplInfo"));
                if (!CommUtil.isNull(tv_cpl_location)
                        && !CommUtil.isNull(tv_cpl_social)
                        && !CommUtil.isNull(tv_cpl_warning_relative)
                        && !CommUtil.isNull(et_cpl_live)
                        && !CommUtil.isNull(et_cpl_warning_man)
                        && CheckUtil.isMobile(CommUtil.getText(et_cpl_warning_phone))
                        ) {
                    ToastUtil.showToast(getActivity(), "数据ok");
                    //这里提交
                    EventBus.getDefault().post(new EventCplInfo(tempUserInfo, 2));
                } else {
                    ToastUtil.showToast(getActivity(), "数据格式不正确");
                }
                break;
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

    }

    @Override
    public void requestEnd() {

    }


    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new EventTurn(0, "cplInfo"));
    }
}
