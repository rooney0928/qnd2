package com.app.qunadai.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.CreditCard;
import com.app.qunadai.content.ui.product.BrowserActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/7/24.
 */

public class CreditCardAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CreditCard.ContentBeanX.BanksBean.ContentBean> list;

    public CreditCardAdapter(Context context) {
        this.context = context;
    }


    public void setList(List<CreditCard.ContentBeanX.BanksBean.ContentBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_bank, parent, false);
        CreditHolder viewHolder = new CreditHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CreditHolder) {
            CreditHolder creditHolder = (CreditHolder) holder;
            creditHolder.setData();
        }
    }

    class CreditHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_card_layout)
        RelativeLayout rl_card_layout;
        @BindView(R.id.iv_card_avatar)
        ImageView iv_card_avatar;
        @BindView(R.id.tv_card_bankname)
        TextView tv_card_bankname;
        @BindView(R.id.tv_card_slogan)
        TextView tv_card_slogan;

        @BindView(R.id.tv_card_speed)
        TextView tv_card_speed;
        @BindView(R.id.tv_card_average)
        TextView tv_card_average;
        @BindView(R.id.tv_card_apply)
        TextView tv_card_apply;

        @BindView(R.id.ll_card_label)
        LinearLayout ll_card_label;


        public CreditHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData() {
            final CreditCard.ContentBeanX.BanksBean.ContentBean bean = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + bean.getBLogo();
            ImgUtil.loadRound(context, imgUrl, iv_card_avatar);

            tv_card_bankname.setText(bean.getBName());
            tv_card_slogan.setText(bean.getRecommendSlogan());

            tv_card_speed.setText(bean.getApproveSpeed());
            tv_card_average.setText(bean.getAvgAmount());
            tv_card_apply.setText(bean.getApplyBase()+"");

            ll_card_label.removeAllViews();
            String[] labels = bean.getBLabel().split(",");
            for(String lb:labels){
                View view = View.inflate(context,R.layout.view_label,null);
                TextView tv_label = (TextView) view.findViewById(R.id.tv_label);
                tv_label.setText(lb);
                ll_card_label.addView(view);
            }

            rl_card_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BrowserActivity.class);
                    String url = bean.getBUrl();
                    intent.putExtra("url", url);
                    intent.putExtra("title", "办信用卡");
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
