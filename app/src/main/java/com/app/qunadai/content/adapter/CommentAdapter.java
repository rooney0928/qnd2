package com.app.qunadai.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.Comment;
import com.app.qunadai.content.ui.bbs.ReplyActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.RelativeDateFormat;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/6/6.
 */

public class CommentAdapter extends RecyclerView.Adapter {

    private Context context;
    private OnCompatItemClickListener itemClickListener;

    private List<Comment> list;

    public CommentAdapter(Context context, OnCompatItemClickListener listener) {
        this.context = context;
        this.itemClickListener = listener;
    }

    public void setList(List<Comment> list) {
        this.list = list;
        notifyDataSetChanged();
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
        @BindView(R.id.iv_comment_avatar)
        ImageView iv_comment_avatar;
        @BindView(R.id.tv_comment_username)
        TextView tv_comment_username;
        @BindView(R.id.tv_comment_time)
        TextView tv_comment_time;
        @BindView(R.id.cb_comment_praise)
        CheckBox cb_comment_praise;
        @BindView(R.id.tv_comment_amount)
        TextView tv_comment_amount;
        @BindView(R.id.tv_comment_content)
        TextView tv_comment_content;

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
                    itemClickListener.onItemClick(itemView, getAdapterPosition());
                }
            });
            rv_reply.setLayoutManager(linearLayoutManager);
            rv_reply.setAdapter(adapter);
        }

        public void setData() {

//            ImgUtil.loadImg(context,);
            Comment c = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + c.getUserAvatar();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_comment_avatar);
            tv_comment_username.setText(c.getUserNick());
            tv_comment_time.setText(RelativeDateFormat.format(new Date(c.getCreatedTime())));
            cb_comment_praise.setChecked(c.isPraisedByCurrentUser());
            cb_comment_praise.setText(""+c.getThumbUpAmount());
            tv_comment_content.setText(c.getContent());

            ll_comment_reply.setVisibility(View.GONE);
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
        return list == null ? 0 : list.size();
    }
}
