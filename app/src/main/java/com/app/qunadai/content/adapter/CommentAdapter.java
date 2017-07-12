package com.app.qunadai.content.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.Comment;
import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.content.contract.bbs.PostCommentContract;
import com.app.qunadai.content.presenter.bbs.PostCommentPresenter;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.third.eventbus.EventToken;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.RelativeDateFormat;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/6/6.
 */

public class CommentAdapter extends RecyclerView.Adapter {
    public static enum ITEM_TYPE {
        ITEM_TYPE_CONTENT,
        ITEM_TYPE_LOADMORE
    }

    private Context context;
    private OnCompatItemClickListener itemClickListener;
    private OnLoadMoreListener loadMoreListener;

    private List<Comment> list;
    private String token;

    public interface OnLoadMoreListener {
        void onLoadMore();
    }


    public CommentAdapter(Context context, OnCompatItemClickListener listener) {
        this.context = context;
        this.itemClickListener = listener;
        token = PrefUtil.getString(context, PrefKey.TOKEN, "");
    }

    public void setList(List<Comment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {

        if (position < list.size()) {
            return ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_LOADMORE.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        RecyclerView.ViewHolder viewHolder;

        if (viewType == ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal()) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
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
        }else if(holder instanceof LoadMoreHolder){
            LoadMoreHolder loadMoreHolder = (LoadMoreHolder) holder;
            loadMoreHolder.setData();
        }
    }

    class ContentHolder extends RecyclerView.ViewHolder implements PostCommentContract.View {
        private PostCommentPresenter postCommentPresenter;

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

        public ContentHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            postCommentPresenter = new PostCommentPresenter(this);

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

            final Comment c = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + c.getUserAvatar();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_comment_avatar);
            tv_comment_username.setText(c.getUserNick());
            tv_comment_time.setText(RelativeDateFormat.format(new Date(c.getCreatedTime())));
            cb_comment_praise.setChecked(c.isPraisedByCurrentUser());
            cb_comment_praise.setText("" + c.getThumbUpAmount());
            tv_comment_content.setText(c.getContent());
/*

            ll_comment_reply.setVisibility(View.GONE);
            ll_comment_reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentReply = new Intent(context, ReplyActivity.class);
                    context.startActivity(intentReply);
                }
            });
*/
            if (CommUtil.isNull(token)) {
                cb_comment_praise.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new EventToken());
                    }
                });
            } else {
                cb_comment_praise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            postCommentPresenter.praiseComment(c.getId(), token);
                        } else {
                            postCommentPresenter.cancelPraiseComment(c.getId(), token);
                        }
                    }
                });
            }


        }

        @Override
        public void praiseComment(PraiseBean bean) {
            optPraise(bean);
        }

        @Override
        public void praiseCommentFail(String error) {
            ToastUtil.showToast(context, error);
        }

        @Override
        public void cancelPraiseComment(PraiseBean bean) {
            optPraise(bean);
        }

        @Override
        public void cancelPraiseCommentFail(String error) {
            ToastUtil.showToast(context, error);
        }

        @Override
        public void updateView(Object serverData) {

        }

        @Override
        public void updateError(String error) {
            ToastUtil.showToast(context, error);
        }

        @Override
        public void requestStart() {
            EventBus.getDefault().post(new EventProgress(true));
        }

        @Override
        public void requestEnd() {
            EventBus.getDefault().post(new EventProgress(false));
        }

        @Override
        public void tokenFail() {

        }

        public void optPraise(PraiseBean bean) {
            boolean isPraise = "NORMAL".equalsIgnoreCase(bean.getContent().getThumbUp().getStatus());
            cb_comment_praise.setSelected(isPraise);

//        int praiseCount = post.getThumbUpAmount();
            int praiseCount = Integer.valueOf(CommUtil.getText(cb_comment_praise));
            if (isPraise) {
                praiseCount += 1;
            } else {
                praiseCount -= 1;
            }

            cb_comment_praise.setText("" + praiseCount);
        }
    }

    class LoadMoreHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_load_more)
        TextView tv_load_more;

        public LoadMoreHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(){
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
