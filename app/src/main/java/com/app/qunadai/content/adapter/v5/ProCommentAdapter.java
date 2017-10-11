package com.app.qunadai.content.adapter.v5;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.ProComment;
import com.app.qunadai.bean.v5.Reply;
import com.app.qunadai.content.adapter.CommentAdapter;
import com.app.qunadai.content.ui.product.RepliesActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.RelativeDateFormat;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/9/14.
 */

public class ProCommentAdapter extends RecyclerView.Adapter {
    public enum ITEM_TYPE {
        ITEM_TYPE_CONTENT,
        ITEM_TYPE_LOAD_MORE
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
    public interface OnClickReplyListener {
        void replyComment(int position);
    }

    private Context context;
    private List<ProComment> list;
    private OnLoadMoreListener loadMoreListener;
    private OnClickReplyListener onClickReplyListener;
    private int totalComment;


    public ProCommentAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ProComment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void setOnClickReplyListener(OnClickReplyListener onClickReplyListener) {
        this.onClickReplyListener = onClickReplyListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < list.size()) {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_LOAD_MORE.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }


        RecyclerView.ViewHolder viewHolder;

        if (viewType == ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal()) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_comment_v5, parent, false);
            viewHolder = new ContentHolder(view);
        } else {
            View viewLoad = LayoutInflater.from(context).inflate(R.layout.view_loadmore, parent, false);
            viewHolder = new LoadMoreHolder(viewLoad);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentHolder) {
            ContentHolder contentHolder = (ContentHolder) holder;
            contentHolder.setData();
        } else if (holder instanceof LoadMoreHolder) {
            LoadMoreHolder loadMoreHolder = (LoadMoreHolder) holder;
            loadMoreHolder.setData();
        }
    }

    class ContentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_comment_layout)
        RelativeLayout rl_comment_layout;
        @BindView(R.id.iv_comment_avatar)
        ImageView iv_comment_avatar;
        @BindView(R.id.tv_comment_username)
        TextView tv_comment_username;
        @BindView(R.id.iv_product_star)
        ImageView iv_product_star;
        @BindView(R.id.tv_comment_content)
        TextView tv_comment_content;
        @BindView(R.id.tv_comment_time)
        TextView tv_comment_time;


        @BindView(R.id.ll_last_part)
        LinearLayout ll_last_part;
        @BindView(R.id.tv_last_nickname)
        TextView tv_last_nickname;
        @BindView(R.id.tv_last_content)
        TextView tv_last_content;
        @BindView(R.id.tv_last_date)
        TextView tv_last_date;
        @BindView(R.id.tv_comment_reply_total)
        TextView tv_comment_reply_total;

        public ContentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData() {
            final ProComment pc = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + pc.getUseravatar();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_comment_avatar);
            if (CheckUtil.isMobile(pc.getUsernick())) {
                StringBuilder sb = new StringBuilder(pc.getUsernick());
                String username = sb.replace(3, pc.getUsernick().length() - 4, "****").toString();
                tv_comment_username.setText(username);
            } else {
                tv_comment_username.setText(pc.getUsernick());
            }

            if (CommUtil.isNull(pc.getContent())) {
                tv_comment_content.setVisibility(View.GONE);
            } else {
                tv_comment_content.setText(pc.getContent());

            }
            tv_comment_time.setText(RelativeDateFormat.format(new Date(pc.getCreatedTime())));

            rl_comment_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickReplyListener!=null){
                        onClickReplyListener.replyComment(getAdapterPosition());
                    }
                }
            });

            //最终回复
            if (pc.getLatestReply() != null) {
                Reply replyBean = pc.getLatestReply();
                ll_last_part.setVisibility(View.VISIBLE);
                //判断是否为手机号，是则替换星号
                if (CheckUtil.isMobile(replyBean.getUsernick())) {
                    StringBuilder sb = new StringBuilder(replyBean.getUsernick());
                    String username = sb.replace(3, replyBean.getUsernick().length() - 4, "****").toString();
                    tv_last_nickname.setText(username + ":");
                } else {
                    tv_last_nickname.setText(replyBean.getUsernick() + ":");
                }

                tv_last_content.setText(replyBean.getContent());
                tv_last_date.setText(RelativeDateFormat.format(new Date(replyBean.getCreatedTime())));

                tv_comment_reply_total.setText("共" + pc.getReplyNumber() + "条回复>");

                tv_comment_reply_total.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentReply = new Intent(context, RepliesActivity.class);
                        intentReply.putExtra("proComment", pc);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Activity activity = (Activity) context;
                            context.startActivity(intentReply, ActivityOptions.makeSceneTransitionAnimation(activity, iv_comment_avatar, "share_comment_avatar").toBundle());
                        } else {
//                        startActivity(intent);
                            context.startActivity(intentReply);

                        }
                    }
                });
            } else {
                ll_last_part.setVisibility(View.GONE);
            }
        }
    }

    class LoadMoreHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_load_more)
        TextView tv_load_more;

        public LoadMoreHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            tv_load_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMoreListener.onLoadMore();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list == null || list.size() == 0 ? 0 : list.size() + 1;
    }
}
