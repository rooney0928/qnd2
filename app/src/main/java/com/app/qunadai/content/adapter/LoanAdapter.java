package com.app.qunadai.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.content.ui.product.ProductDetailActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/5/12.
 */

public class LoanAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<LoanDetail> list;

    public LoanAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<LoanDetail> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_loan_msg2, parent, false);
        LoanHolder2 holder = new LoanHolder2(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof LoanHolder2 && position >= 0) {
            LoanHolder2 loanHolder = (LoanHolder2) viewHolder;
            loanHolder.setData();
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class LoanHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_loan_item)
        RelativeLayout rl_loan_item;
        @BindView(R.id.iv_avatar)
        ImageView iv_avatar;
        @BindView(R.id.tv_product_name)
        TextView tv_product_name;
        @BindView(R.id.tv_product_desc)
        TextView tv_product_desc;
        @BindView(R.id.tv_person)
        TextView tv_person;


        public LoanHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            final LoanDetail detail = list.get(getAdapterPosition());

            String imgUrl = RxHttp.ROOT + "attachments/" + detail.getIcon();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_avatar);
            tv_product_name.setText(detail.getName());
            tv_product_desc.setText(detail.getDescribe());
            tv_person.setText(detail.getNum() + "");
            rl_loan_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入贷款产品
                    CommUtil.tcEvent(context, "Product list", "产品列表");
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("pid", detail.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    class LoanHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_loan_item)
        RelativeLayout rl_loan_item;
        @BindView(R.id.iv_avatar)
        ImageView iv_avatar;
        @BindView(R.id.tv_loan_name)
        TextView tv_loan_name;

        @BindView(R.id.tv_loan_amount)
        TextView tv_loan_amount;
        @BindView(R.id.tv_loan_slogan)
        TextView tv_loan_slogan;
        @BindView(R.id.tv_loan_rate)
        TextView tv_loan_rate;
        @BindView(R.id.tv_loan_period)
        TextView tv_loan_period;

        @BindView(R.id.bt_apply)
        Button bt_apply;

        public LoanHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData() {
            final LoanDetail detail = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + detail.getIcon();
            ImgUtil.loadImg(context, imgUrl, iv_avatar);

            tv_loan_name.setText(detail.getName());
            tv_loan_amount.setText(detail.getMaxAmount() + "");
            tv_loan_slogan.setText(detail.getDescribe());
            tv_loan_rate.setText("月费率 " + detail.getRate());

            String period = detail.getMinTerm() + "-" + detail.getMaxTerm() + detail.getTermUnit();
            tv_loan_period.setText("贷款期限 " + period);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommUtil.tcEvent(context, "Product list", "产品列表");
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("pid", detail.getId());
                    context.startActivity(intent);
                }
            };

            rl_loan_item.setOnClickListener(listener);
            bt_apply.setOnClickListener(listener);
        }
    }
}
