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
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.Product;
import com.app.qunadai.content.adapter.LoanAdapter;
import com.app.qunadai.content.ui.product.Product5DetailActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.ToastUtil;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.grantland.widget.AutofitTextView;

/**
 * Created by wayne on 2017/9/8.
 */

public class ProductAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<Product> list;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Product> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_v5, parent, false);
        ProductHolder holder = new ProductHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductHolder) {
            ProductHolder productHolder = (ProductHolder) holder;
            productHolder.setData();
        }
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_product_header)
        ImageView iv_product_header;
        @BindView(R.id.tv_product_name)
        TextView tv_product_name;
        @BindView(R.id.tv_product_amount)
        AutofitTextView tv_product_amount;
        @BindView(R.id.tv_product_fast)
        AutofitTextView tv_product_fast;
        @BindView(R.id.tv_product_rate)
        AutofitTextView tv_product_rate;
        @BindView(R.id.tv_product_period)
        AutofitTextView tv_product_period;
        @BindView(R.id.iv_product_star)
        ImageView iv_product_star;


        public ProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            final Product p = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + p.getIcon();

            ImgUtil.loadImgAvatar(context, imgUrl, iv_product_header);
            tv_product_name.setText(p.getName());
            tv_product_amount.setText(p.getMaxAmount() + "");
            String loanUnit = "";
            switch (p.getLoanTimeUnit()) {
                case "SECONDS":
                case "SECOND":
                    loanUnit = "秒";
                    break;
                case "MINUTES":
                case "MINUTE":
                    loanUnit = "分钟";
                    break;
                case "HOURS":
                case "HOUR":
                    loanUnit = "小时";
                    break;
            }

            tv_product_fast.setText(p.getLoanTime() + loanUnit + "放款");
            String unit = "";
            switch (p.getRateStatus()) {
                case "MONTH":
                    unit = "月";
                    break;
                case "WEEK":
                    unit = "周";
                    break;
                case "DAY":
                    unit = "天";
                    break;
                default:
                    unit = "期";
                    break;
            }
            tv_product_rate.setText(unit + "费率" + p.getMinRate() + "%");
            tv_product_period.setText("贷款期限" + p.getMaxTerm() + p.getTermUnit());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtil.showToast(context, p.getId());
                    Intent intentDetail = new Intent(context, Product5DetailActivity.class);
                    intentDetail.putExtra("pid", p.getId());

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Activity activity = (Activity) context;
                        context.startActivity(intentDetail, ActivityOptions.makeSceneTransitionAnimation(activity, iv_product_header, "item_avatar").toBundle());
                    } else {
//                        startActivity(intent);
                        context.startActivity(intentDetail);

                    }
                }
            });


            double stars = (double) p.getTotalStarNumber();
            double comments = (double) p.getTotalCommentNumber();
            long star1 = Math.round(stars / comments);
//            LogU.t(p.getName() + "---" + star1);
            int star = (int) star1;
            iv_product_star.setVisibility(View.VISIBLE);
            switch (star) {
                case 0:
                    ImgUtil.loadImg(context, R.mipmap.ic_star1, iv_product_star);

                    break;
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
            if (star <= 3) {
                iv_product_star.setVisibility(View.GONE);
            }
            iv_product_star.setVisibility(p.getTotalCommentNumber() <= 3 ? View.GONE : View.VISIBLE);

        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
