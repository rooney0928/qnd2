package com.app.qunadai.content.ui.me;

import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ExploreBean;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.ExploreAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.ExploreContract;
import com.app.qunadai.content.inter.OnReLinkListener;
import com.app.qunadai.content.presenter.v5.ExplorePresenter;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/9/15.
 */

public class ExploreActivity extends BaseActivity implements ExploreContract.View {

    private ExplorePresenter explorePresenter;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;


    LinearLayoutManager linearLayoutManager;
    ExploreAdapter adapter;
    private List<ExploreBean.UserBrowsingHistoryListBean> list;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("浏览记录");
        setTitleRightImg(R.mipmap.ic5_delete);
        setTitleRightEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用删除记录接口

                showClearDialog();
            }
        });
    }

    private void showClearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("是否确认清除");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
//                startActivity(intent);
                if (NetworkUtil.checkNetwork(ExploreActivity.this)) {
                    explorePresenter.clearExplore(getToken());
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_explore, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();

        explorePresenter = new ExplorePresenter(this);
        adapter = new ExploreAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dp8);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setAdapter(adapter);
        rv_list.setLayoutManager(linearLayoutManager);


        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestExplore();

            }
        });
        requestExplore();


        setOnReLinkListener(new OnReLinkListener() {
            @Override
            public void doNewRequest() {
                explorePresenter.getExplore(getToken());
            }
        });
    }

    public void requestExplore() {
        if (NetworkUtil.checkNetwork(ExploreActivity.this)) {
            explorePresenter.getExplore(getToken());
        } else {
            setViewOffline();
        }

    }

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
    }

    @Override
    public void requestEnd() {
        if (swipe_layout != null && swipe_layout.isRefreshing()) {
            swipe_layout.setRefreshing(false);
        }
    }


    @Override
    public void getExplore(BaseBean<ExploreBean> bean) {
//刷新
        List<ExploreBean.UserBrowsingHistoryListBean> productList = bean.getContent().getUserBrowsingHistoryList();
        list.clear();
        list = productList;
        adapter.setList(list);
        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void getExploreFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void clearExplore(BaseBean bean) {
        if (bean.getCode().equals("000")) {
            ToastUtil.showToast(this, "恭喜您！清除成功");
        }
        adapter.setList(new ArrayList<ExploreBean.UserBrowsingHistoryListBean>());

    }

    @Override
    public void clearExploreFail(String error) {
        ToastUtil.showToast(this, error);

    }
}
