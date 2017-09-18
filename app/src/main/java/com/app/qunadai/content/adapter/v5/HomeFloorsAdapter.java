package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.content.Intent;
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
import com.app.qunadai.content.ui.bbs.PostDetailActivity;
import com.app.qunadai.content.ui.home.CreditCardActivity;
import com.app.qunadai.content.ui.home.FilterProductsActivity;
import com.app.qunadai.content.ui.product.BrowserActivity;
import com.app.qunadai.content.ui.product.Product5DetailActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
            final RoomBean roomBean = bean.getFloorContents().get(0);
//            iv_home_banner_item.setImageResource(R.mipmap.ex_pkq);
            String imgUrl = RxHttp.ROOT + "attachments/" + roomBean.getContentImg();

            ImgUtil.loadImg(context, imgUrl, iv_home_banner_item);
            iv_home_banner_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (roomBean.getBannerMode()) {
                        case "EXTERNAL":
                            Intent intent = new Intent(context, BrowserActivity.class);
                            intent.putExtra("url", roomBean.getContentUrl());
                            intent.putExtra("title", "");
                            context.startActivity(intent);
                            break;
                        case "INTERNAL":

                            break;
                        case "PARAMETER":
                            try {
                                JSONObject obj = new JSONObject(roomBean.getContentUrl());
                                String type = obj.getString("type");
                                if (type.equalsIgnoreCase("productListType")) {
                                    //进入列表
                                    String prop = obj.getString("parameter");
                                    Intent intentPros = new Intent(context, FilterProductsActivity.class);

                                    switch (prop) {
                                        case "loanTime":
                                            intentPros.putExtra("index", 0);
                                            break;
                                        case "minRate":
                                            intentPros.putExtra("index", 1);
                                            break;
                                        case "maxAmount":
                                            intentPros.putExtra("index", 2);
                                            break;
                                        case "maxTerm":
                                            intentPros.putExtra("index", 3);
                                            break;
                                    }
                                    context.startActivity(intentPros);

                                }else if(type.equalsIgnoreCase("productDetailType")){
                                    //进入产品详情
                                    String proId = obj.getString("parameter");
                                    Intent intentDetail = new Intent(context, Product5DetailActivity.class);
                                    intentDetail.putExtra("pid", proId);
                                    context.startActivity(intentDetail);
                                }else if(type.equalsIgnoreCase("creditCardListType")){
                                    //进入信用卡列表
                                    Intent intentCredit = new Intent(context, CreditCardActivity.class);
                                    context.startActivity(intentCredit);
                                }else if(type.equalsIgnoreCase("articleDetailType")){
                                    //进入帖子详情
                                    String postid = obj.getString("parameter");
                                    Intent intentDetail = new Intent(context, PostDetailActivity.class);
                                    intentDetail.putExtra("postid", postid);
                                    context.startActivity(intentDetail);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            });

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
