package com.cash4review.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cash4review.R;
import com.cash4review.productReview.PostReviewScreen;
import com.cash4review.productReview.ProductsReviewScreen;
import com.cash4review.utils.ItemClickSupport;
import com.cash4review.wallet.WalletScreen;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeScreen extends AppCompatActivity {

    @BindView(R.id.rclCategory)
    RecyclerView rclCategory;
    @BindView(R.id.ivCategory)
    ImageView ivCategory;
    @BindView(R.id.ivadd)
    ImageView ivadd;
    @BindView(R.id.ivwallet)
    ImageView ivwallet;
    @BindView(R.id.ivProf)
    ImageView ivProf;
    private ArrayList<HomeModel> arrHome = new ArrayList<>();
    HomeModel homeModel;
    private boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        homeModel = new HomeModel();
        homeModel.setName("RESTAURANTS");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/restaurants.png");

        arrHome.add(homeModel);
        homeModel = new HomeModel();
        homeModel.setName("BOUTIQUES");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/Boutiques.png");
        arrHome.add(homeModel);
        homeModel = new HomeModel();
        homeModel.setName("SALONS");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/SALON.png");
        arrHome.add(homeModel);
        homeModel = new HomeModel();
        homeModel.setName("RESTAURANTS");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/restaurants.png");
        arrHome.add(homeModel);
        homeModel = new HomeModel();
        homeModel.setName("BOUTIQUES");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/Boutiques.png");
        arrHome.add(homeModel);
        homeModel = new HomeModel();
        homeModel.setName("SALONS");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/SALON.png");
        arrHome.add(homeModel);
        homeModel = new HomeModel();
        homeModel.setName("RESTAURANTS");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/restaurants.png");
        arrHome.add(homeModel);
        homeModel = new HomeModel();
        homeModel.setName("BOUTIQUES");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/Boutiques.png");
        arrHome.add(homeModel);
        homeModel = new HomeModel();
        homeModel.setName("SALONS");
        homeModel.setUrls("http://www.influx.co.in/reviewoncash/SALON.png");
        arrHome.add(homeModel);

        rclCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeScreen.this);
        rclCategory.setLayoutManager(layoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(this, arrHome);
        rclCategory.setAdapter(homeAdapter);
        ItemClickSupport.addTo(rclCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent main = new Intent(HomeScreen.this, ProductsReviewScreen.class);
                main.putExtra("name", arrHome.get(position).getName());
                startActivity(main);

            }
        });
    }

    @OnClick({R.id.ivCategory, R.id.ivadd, R.id.ivwallet, R.id.ivAddReview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivCategory:
                break;
            case R.id.ivadd:
                break;
            case R.id.ivwallet:
                startActivity(new Intent(this, WalletScreen.class));
                break;
            case R.id.ivAddReview:
                startActivity(new Intent(this, PostReviewScreen.class));
                break;
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    public void onBackPressed() {


            if (exit) {
                ActivityCompat.finishAffinity(this); // finish activity
            } else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);
            }



    }
}
