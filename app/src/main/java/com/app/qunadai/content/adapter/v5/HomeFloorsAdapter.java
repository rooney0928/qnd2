package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.RoomBean;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wayne on 2017/9/7.
 */

public class HomeFloorsAdapter extends RecyclerView.Adapter {


    public enum ItemType {
        ITEM_TYPE_RECO,
        ITEM_TYPE_BANNER,
        ITEM_TYPE_PRO
    }

    private Context context;

    List<Floors.FloorsBean> list;

    public HomeFloorsAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Floors.FloorsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Floors.FloorsBean bean = list.get(position);

        switch (bean.getFloorType()) {
            case "RECOMMEND":
                return ItemType.ITEM_TYPE_RECO.ordinal();
            case "BANNER":
                return ItemType.ITEM_TYPE_BANNER.ordinal();
            case "PRODUCT":
                return ItemType.ITEM_TYPE_PRO.ordinal();
            default:
                return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        RecyclerView.ViewHolder viewHolder;

        if (viewType == ItemType.ITEM_TYPE_RECO.ordinal()) {
            View viewReco = LayoutInflater.from(context).inflate(R.layout.item_home_recos, parent, false);
            viewHolder = new RecoHolder(viewReco);
        } else if (viewType == ItemType.ITEM_TYPE_BANNER.ordinal()) {
            View viewBanner = LayoutInflater.from(context).inflate(R.layout.item_home_banner, parent, false);
            viewHolder = new BannerHolder(viewBanner);
        } else {
            View viewBanner = LayoutInflater.from(context).inflate(R.layout.item_home_pros, parent, false);
            viewHolder = new ProHolder(viewBanner);
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecoHolder) {
            RecoHolder recoHolder = (RecoHolder) holder;
            recoHolder.setData();
        } else if (holder instanceof BannerHolder) {
            BannerHolder bannerHolder = (BannerHolder) holder;
            bannerHolder.setData();
        } else if (holder instanceof ProHolder) {
            ProHolder proHolder = (ProHolder) holder;
            proHolder.setData();
        }
    }

    class RecoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_list)
        RecyclerView rv_list;
        RecoChildAdapter adapter;
        GridLayoutManager gridLayoutManager;


        public RecoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            gridLayoutManager = new GridLayoutManager(context, 2);
            adapter = new RecoChildAdapter(context);
            rv_list.setLayoutManager(gridLayoutManager);
            rv_list.setNestedScrollingEnabled(false);

            rv_list.setAdapter(adapter);

        }

        public void setData() {
            Floors.FloorsBean bean = list.get(getAdapterPosition());
            adapter.setList(bean.getFloorContents());
        }
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home_banner_item)
        ImageView iv_home_banner_item;

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setData() {
            Floors.FloorsBean bean = list.get(getAdapterPosition());
            RoomBean roomBean = bean.getFloorContents().get(0);
//            iv_home_banner_item.setImageResource(R.mipmap.ex_pkq);
            String imgUrl = RxHttp.ROOT + "attachments/" + roomBean.getContentImg();

            ImgUtil.loadImg(context, imgUrl, iv_home_banner_item);

//            bean.
            if(bean.getFloorContents().size()>0){
//                bean.getFloorContents().get(0)
            }
        }
    }

    class ProHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_list)
        RecyclerView rv_list;

        ProChildAdapter adapter;
        GridLayoutManager gridLayoutManager;


        public ProHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            adapter = new ProChildAdapter(context);
            gridLayoutManager = new GridLayoutManager(context, 3);

            rv_list.setLayoutManager(gridLayoutManager);
            rv_list.setNestedScrollingEnabled(false);
            rv_list.setAdapter(adapter);
        }

        public void setData() {
            Floors.FloorsBean bean = list.get(getAdapterPosition());
            adapter.setList(bean.getFloorContents());
        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
