package com.app.qunadai.content.ui.home;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.bean.Recommend;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.LoanAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.RecommendContract;
import com.app.qunadai.content.presenter.RecommendPresenter;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wayne on 2017/5/12.
 */

public class RecommendActivity extends BaseActivity implements RecommendContract.View {
    private static final int PAGE_SIZE = 10;
    int page = 0;

    private RecommendPresenter recommendPresenter;
//    @BindView(R.id.ll_prompt)
//    LinearLayout ll_prompt;
//    @BindView(R.id.iv_prompt_delete)
//    ImageView iv_prompt_delete;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    LoanAdapter adapter;
    List<LoanDetail> list;
    LinearLayoutManager linearLayoutManager;

    boolean isRefresh;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("为您推荐");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_recommend, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        recommendPresenter = new RecommendPresenter(this);
        list = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new LoanAdapter(this);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);

    }

    int lastVisibleItem;

    @Override
    public void initViewData() {
        int mainColor = ContextCompat.getColor(this, R.color.mainColor);
        swipe_layout.setColorSchemeColors(mainColor);
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isRefresh) {
                    return;
                }
                if (NetworkUtil.checkNetwork(RecommendActivity.this)) {
                    page=0;
                    recommendPresenter.getRecommend(page, PAGE_SIZE);
                } else {
                    swipe_layout.setRefreshing(false);
                }
            }
        });
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    swipe_layout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
                    page++;
                    recommendPresenter.getRecommendMore(page, PAGE_SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });
        if(NetworkUtil.checkNetwork(this)){
            recommendPresenter.getRecommend(page, PAGE_SIZE);
        }
    }

    @Override
    public void getRecommend(Recommend bean) {
        //刷新
        List<LoanDetail> recoList = bean.getContent().getTags().getContent();
        list.clear();
        list = recoList;
        adapter.setList(list);
    }

    @Override
    public void getRecommendMore(Recommend bean) {
        //加载更多
        List<LoanDetail> recoList = bean.getContent().getTags().getContent();
        if (recoList.size() > 0) {
            list.addAll(recoList);
            adapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(this, "没有更多数据");
        }
    }

    @Override
    public void getRecommendFail(String error) {
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
        isRefresh = true;
    }

    @Override
    public void requestEnd() {
        if (swipe_layout != null && swipe_layout.isRefreshing()) {
            //延迟500毫秒关闭swipe
            Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(
                    new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    swipe_layout.setRefreshing(false);
                                }
                            });
                        }
                    }
            );

        }
        isRefresh = false;
    }
}
