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
import com.app.qunadai.content.ui.bbs.ReplyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/6/6.
 */

public class CommentAdapter extends RecyclerView.Adapter {

    private Context context;
    private OnCompatItemClickListener itemClickListener;

    public CommentAdapter(Context context, OnCompatItemClickListener listener) {
        this.context = context;
        this.itemClickListener = listener;
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
        if (holder instanceof ViewHolder) {
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

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

            //禁止二级recyclerview滑动
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            adapter = new ReplyAdapter(context, new OnCompatItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //计算评论框高度
//                    int commentH =
                    itemClickListener.onItemClick(itemView,getAdapterPosition());
                }
            });
            rv_reply.setLayoutManager(linearLayoutManager);
            rv_reply.setAdapter(adapter);
        }

        public void setData() {
            if (getAdapterPosition() % 2 == 0) {
                ll_comment_reply.setVisibility(View.GONE);
            }

            ll_comment_reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentReply = new Intent(context, ReplyActivity.class);
                    context.startActivity(intentReply);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
