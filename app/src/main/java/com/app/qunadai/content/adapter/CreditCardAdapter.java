package com.app.qunadai.content.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.qunadai.R;
import com.app.qunadai.bean.CreditCard;

import java.util.List;

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

    }

    class CreditHolder extends RecyclerView.ViewHolder {

        public CreditHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
