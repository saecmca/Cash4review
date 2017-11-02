package com.cash4review.payment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cash4review.R;
import com.cash4review.category.HomeScreen;
import com.cash4review.utils.Appconstants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Success extends AppCompatActivity {
    KonfettiView viewKonfetti;
    @BindView(R.id.succlay)
    LinearLayout succlay;
    @BindView(R.id.layhappy)
    LinearLayout layhappy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);
        Picasso.with(this).load("http://www.influx.co.in/reviewoncash/Success-Screen.png").into((ImageView) findViewById(R.id.ivImagea));
        viewKonfetti = (KonfettiView) findViewById(R.id.viewKonfetti);
        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.BLUE, Color.RED, Color.BLUE)
                .setDirection(1.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(5000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                .stream(300, 5000L);
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this, HomeScreen.class);
        startActivity(main);
        finish();

    }

    @OnClick({R.id.tvYes, R.id.tvNo, R.id.tvhYes, R.id.tvhNo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvYes:
                succlay.setVisibility(View.GONE);
                layhappy.setVisibility(View.VISIBLE);
                Picasso.with(this).load("http://www.influx.co.in/reviewoncash/Happy_Reviewing.jpg").into((ImageView) findViewById(R.id.ivImagea));
                Appconstants.showDialog(this, "");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Appconstants.dismissDialog();
                    }
                }, 2000);
                break;
            case R.id.tvNo:
                Intent main2 = new Intent(this, HomeScreen.class);
                startActivity(main2);
                finish();
                break;

            case R.id.tvhYes:
                finish();
                break;
            case R.id.tvhNo:
                Intent main1 = new Intent(this, HomeScreen.class);
                startActivity(main1);
                finish();
                break;
        }
    }

}
