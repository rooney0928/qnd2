package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.ProComment;
import com.app.qunadai.content.adapter.CommentAdapter;
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

    private Context context;
    private List<ProComment> list;
    private OnLoadMoreListener loadMoreListener;


    public ProCommentAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ProComment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment_v5, parent, false);
        ContentHolder viewHolder = new ContentHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentHolder) {
            ContentHolder contentHolder = (ContentHolder) holder;
            contentHolder.setData();
        }
    }

    class ContentHolder extends RecyclerView.ViewHolder {
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

        public ContentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData() {
            ProComment pc = list.get(getAdapterPosition());
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

            switch (pc.getStars()) {
                case 0:
                case 1:
                    ImgUtil.loadImg(context, R.mipmap.ic_star1, iv_product_star);
                    break;
                case 2:
                    ImgUtil.loadImg(context, R.mipmap.ic_star2, iv_product_star);
                    break;
                case 3:
                    ImgUtil.loadImg(context, R.mipmap.ic_star3, iv_product_star);
                    break;
                case 4:
                    ImgUtil.loadImg(context, R.mipmap.ic_star4, iv_product_star);
                    break;
                case 5:
                    ImgUtil.loadImg(context, R.mipmap.ic_star5, iv_product_star);
                    break;
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
        return list == null ? 0 : list.size();
    }
}
