package com.cash4review.payment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cash4review.R;
import com.cash4review.utils.Appconstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Addcard extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etdate)
    EditText etdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @OnClick({R.id.back, R.id.bntAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bntAdd:
                if(etUsername.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_SHORT).show();
                }else   if(etPwd.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter card number",Toast.LENGTH_SHORT).show();
                }else   if(etdate.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter expiry date",Toast.LENGTH_SHORT).show();
                }else {
                    Appconstants.showDialog(this,"");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           Appconstants.dismissDialog();
                            Intent main=new Intent(Addcard.this,Success.class);
                            startActivity(main);
                            finish();
                        }
                    }, 2000);
                }
                break;
        }
    }
}
