package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.ReplyMessages;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.ImgUtil;
import com.etiennelawlor.trestle.library.Span;
import com.etiennelawlor.trestle.library.Trestle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/11/7.
 */

public class MyMessageReplyAdapter extends RecyclerView.Adapter {
    private Context context;

    private List<ReplyMessages.RepliedCommentsBean.ContentBean.RepliesBean> list;

    public MyMessageReplyAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ReplyMessages.RepliedCommentsBean.ContentBean.RepliesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_msg_reply, parent, false);
        MyReplyHolder viewHolder = new MyReplyHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyReplyHolder) {
            MyReplyHolder myReplyHolder = (MyReplyHolder) holder;
            myReplyHolder.setData();
        }
    }

    class MyReplyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_reply_content)
        TextView tv_reply_content;


        public MyReplyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            ReplyMessages.RepliedCommentsBean.ContentBean.RepliesBean bean = list.get(getAdapterPosition());

            String nickname = bean.getUsernick();
            if (CheckUtil.isMobile(bean.getUsernick())) {
                StringBuilder sb = new StringBuilder(bean.getUsernick());
                String username = sb.replace(3, bean.getUsernick().length() - 4, "****").toString();
                nickname = username;
            }

            List<Span> span = new ArrayList<>();
            span.add(new Span.Builder(nickname + "回复：")
                    .foregroundColor(ContextCompat.getColor(context, R.color.black5))
                    .build()
            );
            span.add(new Span.Builder(bean.getContent())
                    .foregroundColor(ContextCompat.getColor(context, R.color.cpl_key))
                    .build()
            );
            CharSequence sequence = Trestle.getFormattedText(span);

            tv_reply_content.setText(sequence);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
