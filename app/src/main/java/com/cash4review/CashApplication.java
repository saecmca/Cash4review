package com.cash4review;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Mani on 15-10-2017.
 */

public class CashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
