package com.cash4review;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cash4review.login.Login;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ramesh on 09-Jun-16.
 */
public class IntroScreen extends AppCompatActivity {

    ViewPager viewPager;
    @BindView(R.id.btnProceed)
    TextView btnProceed;
    private TextView tvSkip;
    ArrayList<String> arrView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        ButterKnife.bind(this);
        tvSkip = (TextView) findViewById(R.id.tvSkip);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        arrView.add("http://www.influx.co.in/reviewoncash/first-3-screens-&-Login-2 (1).png");
        arrView.add("http://www.influx.co.in/reviewoncash/first-3-screens-&-Login-3 (1).png");
        arrView.add("http://www.influx.co.in/reviewoncash/first-3-screens-&-Login-4 (1).png");
        final DemoPagerAdapter book_adapt = new DemoPagerAdapter(IntroScreen.this, arrView);
        viewPager.setAdapter(book_adapt);
        InkPageIndicator inkPageIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(viewPager);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(IntroScreen.this, Login.class);
                startActivity(main);
                finish();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    btnProceed.setVisibility(View.VISIBLE);
                } else {
                    btnProceed.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @OnClick(R.id.btnProceed)
    public void onViewClicked() {
        Intent main = new Intent(IntroScreen.this, Login.class);
        startActivity(main);
        finish();
    }
}
