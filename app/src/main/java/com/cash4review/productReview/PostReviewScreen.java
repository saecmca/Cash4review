package com.cash4review.productReview;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cash4review.R;
import com.cash4review.customCamera.PickerBuilder;
import com.cash4review.payment.Success;
import com.cash4review.utils.Appconstants;

import java.io.ByteArrayOutputStream;

/**
 * Created by Kavitha on 14-10-2017.
 */

public class PostReviewScreen extends AppCompatActivity {
    TextView txtUsername, txtMobileNumber, txtRating;
    EditText editService, editAmbience, editProorItem;
    Button btnPostReview, btnChangeImage;
    public String TAG = "PostReviewScreen";
    private ProgressDialog mProgress;

    private static final int PERMISSION_REQUEST_CODE_1 = 2;
    private static final int PERMISSION_REQUEST_CODE_2 = 3;
    private static final int PERMISSION_REQUEST_CODE_3 = 4;
    ImageView imgPOI, imgBack;
    private String profileImage = "", categoryname = "";

    private RatingBar rate_products;
    Spinner spiCategory, spiItems, spiLocations;
    private Animation animShake;
    TextInputLayout textinputService, textInputAmbience, textInputproducts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        intiviews();
    }

    private void intiviews() {
        txtRating = (TextView) findViewById(R.id.txt_ratings);
        editService = (EditText) findViewById(R.id.edit_pro_review);
        editAmbience = (EditText) findViewById(R.id.edit_pro_review_ambience);
        editProorItem = (EditText) findViewById(R.id.edit_pro_review_item);
        btnPostReview = (Button) findViewById(R.id.btn_post_review);
        imgPOI = (ImageView) findViewById(R.id.img_poc);
        btnChangeImage = (Button) findViewById(R.id.img_camera);
        spiCategory = (Spinner) findViewById(R.id.spinner_category);
        spiItems = (Spinner) findViewById(R.id.spinner_item);
        spiLocations = (Spinner) findViewById(R.id.spinner_location);
        textinputService = (TextInputLayout) findViewById(R.id.text_input_pro_review_service);
        textInputAmbience = (TextInputLayout) findViewById(R.id.text_input_pro_review_anbience);
        textInputproducts = (TextInputLayout) findViewById(R.id.text_input_pro_review_item);
        imgBack = (ImageView) findViewById(R.id.back);
        rate_products = (RatingBar) findViewById(R.id.rating_bar);

        btnPostReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
//        btnChangeImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                selectImagenew();
//            }
//        });

        String[] cat_array = getResources().getStringArray(R.array.arr_category);
        String[] item_array = getResources().getStringArray(R.array.arr_item);
        String[] loc_array = getResources().getStringArray(R.array.loc_item);

        ArrayAdapter<String> catArrayList = new ArrayAdapter<String>(PostReviewScreen.this, android.R.layout.simple_list_item_1, cat_array);
        ArrayAdapter<String> itemArrayList = new ArrayAdapter<String>(PostReviewScreen.this, android.R.layout.simple_list_item_1, item_array);
        ArrayAdapter<String> locArrayList = new ArrayAdapter<String>(PostReviewScreen.this, android.R.layout.simple_list_item_1, loc_array);
        animShake = AnimationUtils.loadAnimation(PostReviewScreen.this, R.anim.shake);

        spiCategory.setAdapter(catArrayList);
        spiItems.setAdapter(itemArrayList);
        spiLocations.setAdapter(locArrayList);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void checkValidation() {
        String productname = "Asif_briyani", pro_desc = "Its awesome place where u get good service", prorating, pro_likes = "0", pro_unlikes = "0",
                pro_comments = "0", pro_image, pro_location = "Nungambakkam,Chennai";
        if (profileImage != null && !profileImage.equalsIgnoreCase("")) {
            pro_image = profileImage;
        } else {
            pro_image = "";
        }

        if (rate_products.getRating() != 0.0) {
            prorating = String.valueOf(rate_products.getRating());
        } else {
            prorating = "";
        }
        textInputproducts.setErrorEnabled(false);
        textinputService.setErrorEnabled(false);
        textInputproducts.setErrorEnabled(false);
        textinputService.setAnimation(null);
        textInputAmbience.setAnimation(null);
        textInputAmbience.setAnimation(null);
        String category = spiCategory.getSelectedItem().toString();
        String item = spiItems.getSelectedItem().toString();
        String location = spiLocations.getSelectedItem().toString();
        if (prorating.equalsIgnoreCase("") || prorating.equalsIgnoreCase("0") || prorating.equalsIgnoreCase("0.0")) {
            rate_products.setAnimation(animShake);
            rate_products.startAnimation(animShake);
            Toast.makeText(PostReviewScreen.this, "Please give rating", Toast.LENGTH_SHORT).show();
        } else if (category.equalsIgnoreCase("CATEGORY")) {
            spiCategory.setAnimation(animShake);
            spiCategory.startAnimation(animShake);
            Toast.makeText(PostReviewScreen.this, "Please select categories", Toast.LENGTH_SHORT).show();
        } else if (item.equalsIgnoreCase("PRODUCTS / ITEMS")) {
            spiItems.setAnimation(animShake);
            spiItems.startAnimation(animShake);
            Toast.makeText(PostReviewScreen.this, "Please select products/items", Toast.LENGTH_SHORT).show();
        } else if (location.equalsIgnoreCase("LOCATIONS")) {
            spiLocations.setAnimation(animShake);
            spiLocations.startAnimation(animShake);
            Toast.makeText(PostReviewScreen.this, "Please select location", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(editService.getText().toString().trim())) {
            textinputService.setErrorEnabled(true);
            textinputService.setError("Please enter your reviews for Service");
            editService.setFocusable(true);
        } else if (editService.getText().toString().length() < 20) {
            textinputService.setErrorEnabled(true);
            textinputService.setError("Please enter atleast 20 char for you reviews");
            editService.setFocusable(true);
        } else if (TextUtils.isEmpty(editAmbience.getText().toString().trim())) {
            textInputAmbience.setErrorEnabled(true);
            textInputAmbience.setError("Please enter your reviews for Ambience");
            editAmbience.setFocusable(true);
        } else if (editAmbience.getText().toString().length() < 20) {
            textInputAmbience.setErrorEnabled(true);
            textInputAmbience.setError("Please enter atleast 20 char for you reviews");
            editAmbience.setFocusable(true);
        } else if (TextUtils.isEmpty(editProorItem.getText().toString().trim())) {
            textInputproducts.setErrorEnabled(true);
            textInputproducts.setError("Please enter your reviews for Products / Items");
            editProorItem.setFocusable(true);
        } else if (editProorItem.getText().toString().length() < 20) {
            textInputproducts.setErrorEnabled(true);
            textInputproducts.setError("Please enter atleast 20 char for you reviews");
            editProorItem.setFocusable(true);
        } else {
            View view = getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            Appconstants.showDialog(this, "");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Appconstants.dismissDialog();
                    Intent main = new Intent(PostReviewScreen.this, Success.class);
                    startActivity(main);
                    finish();
                }
            }, 2000);
            /*Intent intent = new Intent(PostReviewScreen.this, Addcard.class);
            startActivity(intent);
            finish();*/
        }
//        db.myCartInsert(productname, "", pro_location, prorating, pro_image, pro_desc, pro_likes, pro_unlikes, pro_comments, categoryname);

    }


    private void selectImagenew() {

        mProgress = new ProgressDialog(PostReviewScreen.this);
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(PostReviewScreen.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    int result = getCameraPermission();
                    if (result == 1) {
                        Log.d(TAG, "camera: from main dialogd");
                        new PickerBuilder(PostReviewScreen.this, PickerBuilder.SELECT_FROM_CAMERA)
                                .setOnImageReceivedListener(new PickerBuilder.onImageReceivedListener() {
                                    @Override
                                    public void onImageReceived(final Uri imageUri) {
                                        // Toast.makeText(LookbookActivity.this, "Got image - " + imageUri, Toast.LENGTH_LONG).show();
                                        mProgress.setMessage("Please wait ...");
                                        mProgress.setCancelable(false);
                                        mProgress.show();


                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
//
                                                    imgPOI.setImageBitmap(BitmapFactory.decodeFile(imageUri.getEncodedPath()));

                                                    filepath(BitmapFactory.decodeFile(imageUri.getEncodedPath()));

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, 1000);

                                    }
                                })
                                .setImageName("Cash4Review")
                                .setImageFolderName("cash4reviewfolder")
                                .withTimeStamp(false)
                                .setCropScreenColor(Color.CYAN)
                                .setOnPermissionRefusedListener(new PickerBuilder.onPermissionRefusedListener() {
                                    @Override
                                    public void onPermissionRefused() {
                                        Log.d(TAG, "onPermissionRefused: refused camera");
                                    }
                                })
                                .start();
                    } else {
                        Toast.makeText(PostReviewScreen.this, "Permission Required", Toast.LENGTH_SHORT).show();
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    int result = getGalleryPermission();
                    if (result == 1) {
                        new PickerBuilder(PostReviewScreen.this, PickerBuilder.SELECT_FROM_GALLERY)
                                .setOnImageReceivedListener(new PickerBuilder.onImageReceivedListener() {
                                    @Override
                                    public void onImageReceived(final Uri imageUri) {

                                        mProgress.setMessage("Please wait ...");
                                        mProgress.setCancelable(false);
                                        mProgress.show();

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {

                                                    imgPOI.setImageBitmap(BitmapFactory.decodeFile(imageUri.getEncodedPath()));
                                                    filepath(BitmapFactory.decodeFile(imageUri.getEncodedPath()));

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, 1000);
                                    }
                                })
                                .setImageName("Cash4Review")
                                .setImageFolderName("cash4reviewfolder")
                                .setCropScreenColor(Color.CYAN)
                                .setOnPermissionRefusedListener(new PickerBuilder.onPermissionRefusedListener() {
                                    @Override
                                    public void onPermissionRefused() {

                                    }
                                })
                                .start();
                    }

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    /***
     * This method will get gallery permision
     */
    private int getGalleryPermission() {

        if (ContextCompat.checkSelfPermission(PostReviewScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PostReviewScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE_3);
            return 0;
        } else {
            return 1;
        }

    }


    /**
     * This method will get camera permission for
     */
    private int getCameraPermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(PostReviewScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PostReviewScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE_1);
                return 0;
            } else {
                // permission is already granted
                if (ContextCompat.checkSelfPermission(PostReviewScreen.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PostReviewScreen.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE_2);
                    return 0;
                } else {
                    return 1;
                }
            }
        } else {
            // do nothing
            Log.d(TAG, "getCameraPermission: lower then marshmallow");
            return 1;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE_1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(PostReviewScreen.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(PostReviewScreen.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE_2);
                    } else {
                        Log.d(TAG, "onRequestPermissionsResult: code 1, camera already granted");
                        startCameraToCapture();
                    }

                } else {
                    new android.support.v7.app.AlertDialog.Builder(PostReviewScreen.this)
                            .setTitle("Permission Alert")
                            .setMessage("WRITE_EXTERNAL_STORAGE")
                            .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    // requestPermission1();
                                }
                            })
                            .setNegativeButton("close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    finish();
                                }
                            })
                            .setIcon(R.mipmap.ic_launcher).show();
                }
                break;
            case PERMISSION_REQUEST_CODE_2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: code 2");
                    startCameraToCapture();
                } else {
                    new android.support.v7.app.AlertDialog.Builder(PostReviewScreen.this)
                            .setTitle("Permission Alert")
                            .setMessage("CAMERA PERMISSION")
                            .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    //requestPermission2();
                                }
                            })
                            .setNegativeButton("close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    finish();
                                }
                            })
                            .setIcon(R.mipmap.ic_launcher).show();
                }
                break;
            case PERMISSION_REQUEST_CODE_3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: code 3");
                    startGalleryToSelectImage();
                } else {
                    new android.support.v7.app.AlertDialog.Builder(PostReviewScreen.this)
                            .setTitle("Permission Alert")
                            .setMessage("PHONE PERMISSION")
                            .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    //  requestPermission3();
                                }
                            })
                            .setNegativeButton("close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    finish();
                                }
                            })
                            .setIcon(R.mipmap.ic_launcher).show();
                }
                break;


        }
    }

    /**
     * This method will select image from gallery
     */
    private void startGalleryToSelectImage() {
        new PickerBuilder(PostReviewScreen.this, PickerBuilder.SELECT_FROM_GALLERY)
                .setOnImageReceivedListener(new PickerBuilder.onImageReceivedListener() {
                    @Override
                    public void onImageReceived(final Uri imageUri) {

                        mProgress.setMessage("Please wait ...");
                        mProgress.setCancelable(false);
                        mProgress.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    imgPOI.setImageBitmap(BitmapFactory.decodeFile(imageUri.getEncodedPath()));
                                    filepath(BitmapFactory.decodeFile(imageUri.getEncodedPath()));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 1000);
                    }
                })
                .setImageName("Cash4Review")
                .setImageFolderName("cash4reviewfolder")
                .setCropScreenColor(Color.CYAN)
                .setOnPermissionRefusedListener(new PickerBuilder.onPermissionRefusedListener() {
                    @Override
                    public void onPermissionRefused() {
                        Log.d(TAG, "onPermissionRefused: permission refused ");
                    }
                })
                .start();
    }

    /**
     * This method will start camera capture
     */
    private void startCameraToCapture() {
        Log.d(TAG, "startCameraToCapture: from method");
        new PickerBuilder(PostReviewScreen.this, PickerBuilder.SELECT_FROM_CAMERA)
                .setOnImageReceivedListener(new PickerBuilder.onImageReceivedListener() {
                    @Override
                    public void onImageReceived(final Uri imageUri) {
                        // Toast.makeText(LookbookActivity.this, "Got image - " + imageUri, Toast.LENGTH_LONG).show();
                        mProgress.setMessage("Please wait ...");
                        mProgress.setCancelable(false);
                        mProgress.show();


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
//
                                    imgPOI.setImageBitmap(BitmapFactory.decodeFile(imageUri.getEncodedPath()));

                                    filepath(BitmapFactory.decodeFile(imageUri.getEncodedPath()));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 1000);

                    }
                })
                .setImageName("Cash4Reviews")
                .setImageFolderName("cash4reviewsfolder")
                .withTimeStamp(false)
                .setCropScreenColor(Color.CYAN)
                .setOnPermissionRefusedListener(new PickerBuilder.onPermissionRefusedListener() {
                    @Override
                    public void onPermissionRefused() {
                        Log.d(TAG, "onPermissionRefused: refused camera");
                    }
                })
                .start();
    }


    private void filepath(Bitmap imageUri) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageUri.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        profileImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        mProgress.dismiss();

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
