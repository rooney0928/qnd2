package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.RoomBean;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/9/7.
 */

public class RecoChildAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<RoomBean> list;

    public RecoChildAdapter(Context context) {
        this.context = context;
    }


    public void setList(List<RoomBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_reco, parent, false);
        RoomHolder viewHolder = new RoomHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RoomHolder) {
            RoomHolder roomHolder = (RoomHolder) holder;
            roomHolder.setData();
        }
    }

    class RoomHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_reco_pic)
        ImageView iv_reco_pic;

        public RoomHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            RoomBean bean = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + bean.getContentImg();

            ImgUtil.loadImg(context, imgUrl, iv_reco_pic);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() >= 2 ? 2 : list.size();
    }
}
