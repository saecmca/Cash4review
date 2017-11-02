package com.cash4review.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cash4review.R;
import com.cash4review.category.HomeScreen;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login extends AppCompatActivity {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPwd)
    EditText etPwd;

    @BindView(R.id.tvForgot)
    TextView tvForgot;
    @BindView(R.id.btnSignin)
    ImageView btnSignin;
    @BindView(R.id.btnSignup)
    ImageView btnSignup;
    @BindView(R.id.etUsernameLayout)
    TextInputLayout etUsernameLayout;
    @BindView(R.id.etPasswordLayout)
    TextInputLayout etPasswordLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.btnSignin, R.id.btnSignup,R.id.tvTap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                vlaidation();
                break;
            case R.id.btnSignup:
                break;
            case R.id.tvTap:
                Intent main = new Intent(Login.this, HomeScreen.class);
                startActivity(main);
                finish();
                break;
        }
    }

    private void vlaidation() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsernameLayout.setError("Please enter Mobile number or Email-id");
            etUsername.requestFocus();

        } else if (TextUtils.isEmpty(etPwd.getText().toString())) {
            etPasswordLayout.setError("Please enter Password");
            etPwd.requestFocus();
        } else {
            etUsernameLayout.setErrorEnabled(false);
            etPasswordLayout.setErrorEnabled(false);
            View view = getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            Intent main = new Intent(Login.this, HomeScreen.class);
            startActivity(main);
            finish();
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
