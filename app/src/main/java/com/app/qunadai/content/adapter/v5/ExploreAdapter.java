package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.ExploreBean;
import com.app.qunadai.content.ui.product.Product5DetailActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/9/15.
 */

public class ExploreAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<ExploreBean.UserBrowsingHistoryListBean> list;

    SimpleDateFormat sdf;

    public ExploreAdapter(Context context) {
        this.context = context;
        sdf = new SimpleDateFormat("yyyy.MM.dd");
    }

    public void setList(List<ExploreBean.UserBrowsingHistoryListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_explore_v5, parent, false);
        ExploreHolder viewHolder = new ExploreHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExploreHolder) {
            ExploreHolder exploreHolder = (ExploreHolder) holder;
            exploreHolder.setData();
        }
    }

    class ExploreHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_explore_layout)
        RelativeLayout rl_explore_layout;
        @BindView(R.id.iv_explore_avatar)
        ImageView iv_explore_avatar;
        @BindView(R.id.tv_explore_name)
        TextView tv_explore_name;
        @BindView(R.id.tv_explore_date)
        TextView tv_explore_date;
        @BindView(R.id.tv_explore_limit)
        TextView tv_explore_limit;
        @BindView(R.id.tv_explore_rate)
        TextView tv_explore_rate;
        @BindView(R.id.tv_explore_period)
        TextView tv_explore_period;

        public ExploreHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData() {
            final ExploreBean.UserBrowsingHistoryListBean bean = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + bean.getProductVO().getIcon();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_explore_avatar);

            tv_explore_name.setText(bean.getProductVO().getName());
            tv_explore_date.setText(sdf.format(new Date(bean.getBrowsingTime())));
            tv_explore_limit.setText(bean.getProductVO().getMaxAmount()+"");
            tv_explore_rate.setText("最低利率" + bean.getProductVO().getMinRate() + "/" + bean.getProductVO().getTermUnit());
            tv_explore_period.setText("贷款期限" + bean.getProductVO().getMaxTerm() + bean.getProductVO().getTermUnit());

            rl_explore_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentDetail = new Intent(context, Product5DetailActivity.class);
                    intentDetail.putExtra("pid", bean.getProductVO().getId());
                    context.startActivity(intentDetail);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
