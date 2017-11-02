package com.cash4review.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cash4review.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Selva Ganesh on 18-10-2016. change comment
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHolder> {

    private Context mcontent;
    private ArrayList<HomeModel> arrayList;
    private int selectedIndex = -1;

    public HomeAdapter(Context context, ArrayList<HomeModel> typeList) {
        arrayList = typeList;
        this.mcontent = context;

    }

    public void setSelectedIndex(int ind) {
        selectedIndex = ind;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        try {
            Picasso.with(mcontent).load(arrayList.get(position).getUrls()).into(holder.imgCheck);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imgCheck;

        public MyHolder(View itemView) {
            super(itemView);
            imgCheck = (ImageView) itemView.findViewById(R.id.ivImage);

        }
    }
}