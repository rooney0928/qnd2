package com.app.qunadai.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.qunadai.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/6/6.
 */

public class CommentAdapter extends RecyclerView.Adapter {

    private Context context;

    public CommentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_comment_reply)
        LinearLayout ll_comment_reply;
        @BindView(R.id.rv_reply)
        RecyclerView rv_reply;

        ReplyAdapter adapter;
        LinearLayoutManager linearLayoutManager;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            adapter = new ReplyAdapter(context);
            rv_reply.setLayoutManager(linearLayoutManager);
            rv_reply.setAdapter(adapter);
        }

        public void setData() {
            if (getAdapterPosition() % 2 == 0) {
                ll_comment_reply.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
