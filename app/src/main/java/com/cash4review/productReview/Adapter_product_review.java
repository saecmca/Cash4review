package com.cash4review.productReview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cash4review.R;
import com.cash4review.utils.Appconstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Kavitha on 14-10-2017.
 */

public class Adapter_product_review extends RecyclerView.Adapter<Adapter_product_review.MyHolder> {
    Context mcontext;
    ArrayList<ProductReviewModel> arrProduct;

    public Adapter_product_review(Context context, ArrayList<ProductReviewModel> arrProductModel) {
        this.mcontext = context;
        this.arrProduct = arrProductModel;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_review, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        try {
            holder.txtProductName.setText(arrProduct.get(position).getStrProductName());
            holder.txtProductReviews.setText(arrProduct.get(position).getStrReviewCount());
            holder.txtProductLocation.setText(arrProduct.get(position).getStrcategoryname());
            holder.txtProductUnlikes.setText(arrProduct.get(position).getStrUnlikes());
            holder.txtProductLikes.setText(arrProduct.get(position).getStrLikes());
            holder.txtProductComments.setText(arrProduct.get(position).getStrcomments());
            holder.txtProductLikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int like = Integer.parseInt(arrProduct.get(position).getStrLikes()) + 1;
                    arrProduct.get(position).setStrLikes(String.valueOf(like));
                    notifyDataSetChanged();
                }
            });
            holder.txtProductUnlikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!arrProduct.get(position).getStrUnlikes().equals("0")) {
                        int like = Integer.parseInt(arrProduct.get(position).getStrUnlikes()) - 1;
                        arrProduct.get(position).setStrUnlikes(String.valueOf(like));
                        notifyDataSetChanged();
                    }
                }
            });
            if (arrProduct.get(position).getStrimage() != null && !arrProduct.get(position).getStrimage().equalsIgnoreCase("")) {
                Picasso.with(mcontext).load(arrProduct.get(position).getStrimage()).into(holder.imgProduct);
            }
            holder.imgrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(mcontext, PostReviewScreen.class);
                    mcontext.startActivity(main);
                    ((Activity) mcontext).finish();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct, imgrev;
        TextView txtProductName, txtProductLocation, txtProductLikes, txtProductUnlikes, txtProductComments, txtProductReviews;

        public MyHolder(View itemView) {
            super(itemView);

            imgProduct = (ImageView) itemView.findViewById(R.id.img_product);
            txtProductName = (TextView) itemView.findViewById(R.id.txt_product_name);
            txtProductReviews = (TextView) itemView.findViewById(R.id.txt_ratings);
            txtProductLocation = (TextView) itemView.findViewById(R.id.txt_product_location);
            txtProductComments = (TextView) itemView.findViewById(R.id.txt_product_comment);
            txtProductLikes = (TextView) itemView.findViewById(R.id.txt_product_likes);
            txtProductUnlikes = (TextView) itemView.findViewById(R.id.txt_product_unlikes);
            imgrev = (ImageView) itemView.findViewById(R.id.imgrev);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Appconstants.alerts(mcontext, "http://www.influx.co.in/reviewoncash/CategoriesReview.jpg");
                }
            });


        }
    }
}
