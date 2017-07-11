package com.app.qunadai.content.adapter;

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
        View view = LayoutInflater.from(context).inflate(R.layout.item_loan_msg, parent, false);
        LoanHolder holder = new LoanHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof LoanHolder && position >= 0) {
            LoanHolder loanHolder = (LoanHolder) viewHolder;
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
            tv_person.setText(detail.getNum()+"");
            rl_loan_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入贷款产品
                    CommUtil.tcEvent(context,"Product list","产品列表");
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("pid",detail.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
