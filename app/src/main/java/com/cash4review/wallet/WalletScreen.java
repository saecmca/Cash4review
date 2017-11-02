package com.cash4review.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cash4review.R;
import com.cash4review.utils.Appconstants;

/**
 * Created by Kavitha on 15-10-2017.
 */

public class WalletScreen extends AppCompatActivity {
    ImageView imgback;
    RelativeLayout relPaytm, relFreeCharge, relMobikwik;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        imgback = (ImageView) findViewById(R.id.back);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        relFreeCharge = (RelativeLayout) findViewById(R.id.freecharge_layout);
        relMobikwik = (RelativeLayout) findViewById(R.id.mobikwik_layout);
        relPaytm = (RelativeLayout) findViewById(R.id.paytm_layout);

        relFreeCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoSuccess();
            }
        });
        relPaytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSuccess();
            }
        });
        relMobikwik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSuccess();
            }
        });
    }

    public void gotoSuccess() {
        Appconstants.alerts1(WalletScreen.this, "http://www.influx.co.in/reviewoncash/9-other-products (1) (1).png");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
