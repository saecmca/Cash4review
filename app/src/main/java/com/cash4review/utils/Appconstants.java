package com.cash4review.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cash4review.R;
import com.cash4review.productReview.PostReviewScreen;
import com.squareup.picasso.Picasso;

/**
 * Created by Mani on 15-10-2017.
 */

public class Appconstants {
    public static ProgressDialog pgdialog;

    public static void showDialog(Context mContext, String strMessage) {
        try {
            if (pgdialog != null)
                if (pgdialog.isShowing())
                    pgdialog.dismiss();
            pgdialog = ProgressDialog.show(mContext, "", strMessage, true);
            pgdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            pgdialog.setCancelable(false);
            pgdialog.show();
            pgdialog.setContentView(R.layout.custom_loading);

        } catch (Exception e) {
            e.printStackTrace();
        }
    } public static void dismissDialog() {
        try {
            if (pgdialog.isShowing())
                pgdialog.dismiss();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static String alerts(final Context APP_CONTEXT, String Errormsg) {
        try{
            final Dialog dialog = new Dialog(APP_CONTEXT);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Window window = dialog.getWindow();
            dialog.setContentView(R.layout.dialogs);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

            dialog.setCancelable(true);
            ImageView imageView = (ImageView) dialog.findViewById(R.id.ivdialog);
            TextView textView = (TextView) dialog.findViewById(R.id.tvback);
            TextView textrv= (TextView) dialog.findViewById(R.id.tvRevie);


            Picasso.with(APP_CONTEXT).load(Errormsg).into(imageView);
            dialog.show();
            textrv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    APP_CONTEXT.startActivity(new Intent(APP_CONTEXT, PostReviewScreen.class));
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
        return Errormsg;

    }
    public static String alerts1(final Context APP_CONTEXT, String Errormsg) {
        try{
            final Dialog dialog = new Dialog(APP_CONTEXT);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Window window = dialog.getWindow();
            dialog.setContentView(R.layout.dialogs);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

            dialog.setCancelable(true);
            ImageView imageView = (ImageView) dialog.findViewById(R.id.ivdialog);
            TextView textView = (TextView) dialog.findViewById(R.id.tvback);
            TextView textrv= (TextView) dialog.findViewById(R.id.tvRevie);


            Picasso.with(APP_CONTEXT).load(Errormsg).into(imageView);
            dialog.show();
//            textrv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                    APP_CONTEXT.startActivity(new Intent(APP_CONTEXT, PostReviewScreen.class));
//                }
//            });
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
        return Errormsg;

    }
}
