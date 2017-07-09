package com.app.qunadai.content.ui.bbs;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.Post;
import com.app.qunadai.bean.bbs.TalentBean;
import com.app.qunadai.content.adapter.PostAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.bbs.TalentContract;
import com.app.qunadai.content.presenter.bbs.TalentPresenter;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 * 贷款达人页面
 * Created by wayne on 2017/6/5.
 */

public class TalentActivity extends BaseActivity implements TalentContract.View {
    private TalentPresenter talentPresenter;
    private static final int PAGE_SIZE = 10;
    int page = 0;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    LinearLayoutManager linearLayoutManager;
    PostAdapter adapter;
    boolean isRefresh;

    List<Post> list;


    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_talent, null);
        return view;
    }

    int lastVisibleItem;


    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        talentPresenter = new TalentPresenter(this);
        adapter = new PostAdapter(this);
        list = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);
    }

    @Override
    public void initViewData() {
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                talentPresenter.getPostList(page, PAGE_SIZE);
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
                    talentPresenter.getPostList(page, PAGE_SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });
        swipe_layout.setRefreshing(true);

        if (NetworkUtil.checkNetwork(this)) {
            page = 0;
            talentPresenter.getPostList(page, PAGE_SIZE);
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

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("网贷达人");
        setTitleRightImg(R.mipmap.ic_post);
        setTitleRightEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入发帖
                Intent intentPost = new Intent(TalentActivity.this, PostActivity.class);
                startActivity(intentPost);
            }
        });
    }


    @Override
    public void postList(TalentBean bean) {
//刷新
        List<Post> postList = bean.getContent().getArticle().getContent();
        list.clear();
        list = postList;
        adapter.setList(list);
    }

    @Override
    public void postListMore(TalentBean bean) {
//加载更多
        List<Post> postList = bean.getContent().getArticle().getContent();
        if (postList.size() > 0) {
            list.addAll(postList);
            adapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(this, "没有更多数据");
        }
    }

    @Override
    public void postListFail(String error) {
        ToastUtil.showToast(this, error);
    }
}
