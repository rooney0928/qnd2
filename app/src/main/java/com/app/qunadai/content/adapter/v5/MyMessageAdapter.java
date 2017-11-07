package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.ReplyMessages;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/11/7.
 */

public class MyMessageAdapter extends RecyclerView.Adapter {

    private Context context;
    List<ReplyMessages.RepliedCommentsBean.ContentBean> list;


    public MyMessageAdapter(Context context) {
        this.context = context;
    }


    public void setList(List<ReplyMessages.RepliedCommentsBean.ContentBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_msg, parent, false);
        MyMsgHolder viewHolder = new MyMsgHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyMsgHolder){
            MyMsgHolder myMsgHolder = (MyMsgHolder) holder;
            myMsgHolder.setData();
        }
    }

    class MyMsgHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_msg_header)
        ImageView iv_msg_header;
        @BindView(R.id.tv_msg_name)
        TextView tv_msg_name;
        @BindView(R.id.tv_msg_content)
        TextView tv_msg_content;
        @BindView(R.id.rv_msg_list)
        RecyclerView rv_msg_list;

        MyMessageReplyAdapter messageReplyAdapter;
        LinearLayoutManager linearLayoutManager;


        public MyMsgHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //禁止二级recyclerview滑动
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            messageReplyAdapter = new MyMessageReplyAdapter(context);
            rv_msg_list.setLayoutManager(linearLayoutManager);
            rv_msg_list.setAdapter(messageReplyAdapter);
        }

        public void setData(){
            ReplyMessages.RepliedCommentsBean.ContentBean bean = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + bean.getProductIcon();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_msg_header);
            tv_msg_name.setText(bean.getProductName());
            tv_msg_content.setText(bean.getContent());

            messageReplyAdapter.setList(bean.getReplies());
            LogU.t("size_"+bean.getReplies().size());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
