package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.ProComment;
import com.app.qunadai.bean.v5.Reply;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.RelativeDateFormat;
import com.etiennelawlor.trestle.library.Span;
import com.etiennelawlor.trestle.library.Trestle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/10/10.
 */

public class ReplyAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<Reply> list;
    private ProComment proComment;

    public ReplyAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Reply> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setProComment(ProComment proComment) {
        this.proComment = proComment;
        notifyDataSetChanged();
    }


    public enum ITEM_TYPE {
        ITEM_TYPE_TITLE,
        ITEM_TYPE_CONTENT
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_TITLE.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }


        RecyclerView.ViewHolder viewHolder;

        if (viewType == ITEM_TYPE.ITEM_TYPE_TITLE.ordinal()) {
            View viewTitle = LayoutInflater.from(context).inflate(R.layout.item_reply_title, parent, false);
            viewHolder = new TitleHolder(viewTitle);
        } else {
            View viewReply = LayoutInflater.from(context).inflate(R.layout.item_pro_reply, parent, false);
            viewHolder = new ReplyHolder(viewReply);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof TitleHolder) {
            TitleHolder titleHolder = (TitleHolder) holder;
            titleHolder.setData();
        } else if (holder instanceof ReplyHolder) {
            ReplyHolder replyHolder = (ReplyHolder) holder;
            replyHolder.setData();
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_reply_title)
        RelativeLayout rl_reply_title;
        @BindView(R.id.iv_title_avatar)
        ImageView iv_title_avatar;
        @BindView(R.id.tv_title_username)
        TextView tv_title_username;
        @BindView(R.id.tv_title_content)
        TextView tv_title_content;
        @BindView(R.id.tv_title_time)
        TextView tv_title_time;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            if (proComment != null) {
//                ProComment pc = list.get(getAdapterPosition());
                String imgUrl = RxHttp.ROOT + "attachments/" + proComment.getUseravatar();
                ImgUtil.loadImgAvatar(context, imgUrl, iv_title_avatar);
                if (CheckUtil.isMobile(proComment.getUsernick())) {
                    StringBuilder sb = new StringBuilder(proComment.getUsernick());
                    String username = sb.replace(3, proComment.getUsernick().length() - 4, "****").toString();
                    tv_title_username.setText(username);
                } else {
                    tv_title_username.setText(proComment.getUsernick());
                }

                if (CommUtil.isNull(proComment.getContent())) {
                    tv_title_content.setVisibility(View.GONE);
                } else {
                    tv_title_content.setText(proComment.getContent());

                }
                tv_title_time.setText(RelativeDateFormat.format(new Date(proComment.getCreatedTime())));
            }
        }
    }

    class ReplyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_reply_avatar)
        ImageView iv_reply_avatar;
        @BindView(R.id.tv_reply_content)
        TextView tv_reply_content;
        @BindView(R.id.tv_reply_date)
        TextView tv_reply_date;

        public ReplyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            String imgUrl = RxHttp.ROOT + "attachments/" + proComment.getUseravatar();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_reply_avatar);

            Reply rp = list.get(getAdapterPosition() - 1);

            String nickname = rp.getUsernick();
            if (CheckUtil.isMobile(rp.getUsernick())) {
                StringBuilder sb = new StringBuilder(rp.getUsernick());
                String username = sb.replace(3, rp.getUsernick().length() - 4, "****").toString();
                nickname = username;
            }

            List<Span> span = new ArrayList<>();
            span.add(new Span.Builder(nickname+"：")
                    .foregroundColor(ContextCompat.getColor(context,R.color.black5))
                    .build()
            );
            span.add(new Span.Builder(rp.getContent())
                    .foregroundColor(ContextCompat.getColor(context,R.color.text_grey))
                    .build()
            );

            CharSequence sequence = Trestle.getFormattedText(span);
            tv_reply_content.setText(sequence);

            tv_reply_date.setText(RelativeDateFormat.format(new Date(rp.getCreatedTime())));

        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 1 : list.size() + 1;
    }
}
