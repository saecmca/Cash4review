package com.cash4review.login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cash4review.IntroScreen;
import com.cash4review.R;

public class Splasscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main=new Intent(Splasscreen.this, IntroScreen.class);
                startActivity(main);
                finish();
            }
        }, 3000);
    }
}
