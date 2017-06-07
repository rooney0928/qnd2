package com.app.qunadai.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.qunadai.R;
import com.app.qunadai.utils.LogU;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/6/6.
 */

public class ReplyAdapter extends RecyclerView.Adapter {

    private Context context;
    private OnCompatItemClickListener itemClickListener;


    public ReplyAdapter(Context context, OnCompatItemClickListener listener) {
        this.context = context;
        this.itemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_reply_layout)
        RelativeLayout rl_reply_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData() {
            rl_reply_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
