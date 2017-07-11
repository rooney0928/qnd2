package com.app.qunadai.content.ui.bbs.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.Post;
import com.app.qunadai.bean.bbs.StrategyBean;
import com.app.qunadai.content.adapter.BBSHomeAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.bbs.BBSHomeContract;
import com.app.qunadai.content.presenter.bbs.BBSHomePresenter;
import com.app.qunadai.content.ui.bbs.HelpActivity;
import com.app.qunadai.content.ui.bbs.PostActivity;
import com.app.qunadai.content.ui.bbs.TalentActivity;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wayne on 2017/5/22.
 */

public class BBSFragment extends BaseFragment implements View.OnClickListener, BBSHomeContract.View {
    private BBSHomePresenter bbsHomePresenter;

    private static final int PAGE_SIZE = 10;
    int page = 0;
    boolean isRefresh;


    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.ll_bbs_post)
    LinearLayout ll_bbs_post;
    @BindView(R.id.ll_bbs_help)
    LinearLayout ll_bbs_help;

    @BindView(R.id.ll_bbs_talent)
    LinearLayout ll_bbs_talent;

    LinearLayoutManager linearLayoutManager;
    BBSHomeAdapter adapter;

    private List<Post> list;


    public static BBSFragment getInstance() {
        BBSFragment bbsFragment = new BBSFragment();
        Bundle bundle = new Bundle();
        bbsFragment.setArguments(bundle);
        return bbsFragment;
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_bbs, null);
        return root;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }
    int lastVisibleItem;

    @Override
    protected void initData() {
        bbsHomePresenter = new BBSHomePresenter(this);
        list = new ArrayList<>();

        adapter = new BBSHomeAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
//                    swipe_layout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
                    page++;
                    bbsHomePresenter.getPostList(page, PAGE_SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });

        ll_bbs_post.setOnClickListener(this);
        ll_bbs_talent.setOnClickListener(this);
        ll_bbs_help.setOnClickListener(this);

        bbsHomePresenter.getPostList(page, PAGE_SIZE);
    }

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            LogU.t("load bbs h");
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                // TODO 这里有个注意的地方，如果你刚进来时没有数据，
                // TODO 但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。
                if (list != null && list.size() > 0) {
                    page++;
                    bbsHomePresenter.getPostList(page, PAGE_SIZE);
                }
            }
        }
    };

    @Override
    public void requestStart() {

    }

    @Override
    public void requestEnd() {
        isRefresh = false;
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bbs_post:
                //发帖
                Intent intentPost = new Intent(getActivity(), PostActivity.class);
                startActivity(intentPost);
                break;
            case R.id.ll_bbs_talent:
                //进入贷款达人
                Intent intentTalent = new Intent(getActivity(), TalentActivity.class);
                startActivity(intentTalent);
                break;
            case R.id.ll_bbs_help:
                Intent intentHelp = new Intent(getActivity(), HelpActivity.class);
                startActivity(intentHelp);
                break;
        }
    }

    @Override
    public void postList(StrategyBean bean) {
        //刷新
        List<Post> productList = bean.getContent().getArticle().getContent();
        list.clear();
        list = productList;
        adapter.setList(list);
        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void postListMore(StrategyBean bean) {
        //加载更多
        List<Post> productList = bean.getContent().getArticle().getContent();
        if (productList.size() > 0) {
            list.addAll(productList);
            adapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(getActivity(), "没有更多数据");
        }
    }

    @Override
    public void postListFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }
}

