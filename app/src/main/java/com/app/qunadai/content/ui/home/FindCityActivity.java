package com.app.qunadai.content.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.app.qunadai.R;
import com.app.qunadai.bean.City;
import com.app.qunadai.bean.FindCity;
import com.app.qunadai.content.adapter.LocationChildAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.FindCityContract;
import com.app.qunadai.content.presenter.FindCityPresenter;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wayne on 2017/8/1.
 */

public class FindCityActivity extends BaseActivity implements FindCityContract.View {

    private FindCityPresenter findCityPresenter;

    @BindView(R.id.et_search_text)
    EditText et_search_text;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;


    LocationChildAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("地址选择");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_find_city, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        findCityPresenter = new FindCityPresenter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new LocationChildAdapter(this);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);

        et_search_text.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                findCityPresenter.findCity(s.toString());
            } else {
                adapter.setList(new ArrayList<City>());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void initViewData() {

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
    public void findCity(FindCity bean) {
        adapter.setList(bean.getContent().getDistrictList());
    }

    @Override
    public void findCityError(String error) {
        ToastUtil.showToast(this, error);

    }
}
