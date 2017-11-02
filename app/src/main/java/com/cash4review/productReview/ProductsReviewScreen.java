package com.cash4review.productReview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cash4review.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Kavitha on 14-10-2017.
 */

public class ProductsReviewScreen extends AppCompatActivity {
    RecyclerView recycleProducts;

    ArrayList<ProductReviewModel> arrReview = new ArrayList<>();
    ProductReviewModel productReviewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_review);

        initviews();
        ((ImageView)findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initviews() {
        try {

            TextView tvTitle = (TextView) findViewById(R.id.tvTit);
            ImageView imageView = (ImageView) findViewById(R.id.ivImage);
            tvTitle.setText(getIntent().getStringExtra("name"));
        //    Picasso.with(this).load("https://s1.postimg.org/1fe5qpd2hr/rest_imag_1.png").into(imageView);
            Picasso.with(this).load("https://i.imgur.com/ueam8ro.png").into(imageView);

            recycleProducts = (RecyclerView) findViewById(R.id.recycle_products);
            recycleProducts.setHasFixedSize(true);
            recycleProducts.setLayoutManager(new LinearLayoutManager(this));
            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("DINDIGUL THALAPPAKATTI");
            productReviewModel.setStrcategoryname("T.NAGAR");
            productReviewModel.setStrLikes("15");
            productReviewModel.setStrUnlikes("3");
            productReviewModel.setStrReviewCount("4.5");
            productReviewModel.setStrcomments("12");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/dindu.png");
            arrReview.add(productReviewModel);

            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("AASIFE BIRIYANI");
            productReviewModel.setStrcategoryname("ANNA NAGAR");
            productReviewModel.setStrLikes("9");
            productReviewModel.setStrUnlikes("3");
            productReviewModel.setStrReviewCount("4");
            productReviewModel.setStrcomments("22");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/aasife.png");
            arrReview.add(productReviewModel);

            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("STAR BIRIYANIS");
            productReviewModel.setStrcategoryname("AMBATTUR");
            productReviewModel.setStrLikes("5");
            productReviewModel.setStrUnlikes("1");
            productReviewModel.setStrReviewCount("3");
            productReviewModel.setStrcomments("12");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/star.png");
            arrReview.add(productReviewModel);

            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("DINDIGUL THALAPPAKATTI");
            productReviewModel.setStrcategoryname("T.NAGAR");
            productReviewModel.setStrLikes("15");
            productReviewModel.setStrUnlikes("3");
            productReviewModel.setStrReviewCount("4.5");
            productReviewModel.setStrcomments("12");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/dindu.png");
            arrReview.add(productReviewModel);

            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("AASIFE BIRIYANI");
            productReviewModel.setStrcategoryname("ANNA NAGAR");
            productReviewModel.setStrLikes("9");
            productReviewModel.setStrUnlikes("3");
            productReviewModel.setStrReviewCount("4");
            productReviewModel.setStrcomments("22");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/aasife.png");
            arrReview.add(productReviewModel);

            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("STAR BIRIYANIS");
            productReviewModel.setStrcategoryname("AMBATTUR");
            productReviewModel.setStrLikes("5");
            productReviewModel.setStrUnlikes("1");
            productReviewModel.setStrReviewCount("3");
            productReviewModel.setStrcomments("12");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/star.png");
            arrReview.add(productReviewModel);


            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("DINDIGUL THALAPPAKATTI");
            productReviewModel.setStrcategoryname("T.NAGAR");
            productReviewModel.setStrLikes("15");
            productReviewModel.setStrUnlikes("3");
            productReviewModel.setStrReviewCount("4.5");
            productReviewModel.setStrcomments("12");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/dindu.png");
            arrReview.add(productReviewModel);

            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("AASIFE BIRIYANI");
            productReviewModel.setStrcategoryname("ANNA NAGAR");
            productReviewModel.setStrLikes("9");
            productReviewModel.setStrUnlikes("3");
            productReviewModel.setStrReviewCount("4");
            productReviewModel.setStrcomments("22");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/aasife.png");
            arrReview.add(productReviewModel);

            productReviewModel = new ProductReviewModel();
            productReviewModel.setStrProductName("STAR BIRIYANIS");
            productReviewModel.setStrcategoryname("AMBATTUR");
            productReviewModel.setStrLikes("5");
            productReviewModel.setStrUnlikes("1");
            productReviewModel.setStrReviewCount("3");
            productReviewModel.setStrcomments("12");
            productReviewModel.setStrimage("http://www.influx.co.in/reviewoncash/star.png");
            arrReview.add(productReviewModel);


            Adapter_product_review adapter_product_review = new Adapter_product_review(ProductsReviewScreen.this, arrReview);
            recycleProducts.setAdapter(adapter_product_review);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
