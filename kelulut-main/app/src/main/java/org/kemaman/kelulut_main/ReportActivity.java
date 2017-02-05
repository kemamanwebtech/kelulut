package org.kemaman.kelulut_main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.kemaman.kelulut_main.utils.AppConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonChoose;
    private Button buttonUpload;
    private ImageView imageView;
    private EditText editTextImageDesc;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    String latestLocation = "";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get location here
        try {
            if (getIntent().getExtras().containsKey("location")) {
                latestLocation = getIntent().getExtras().getString("location","3.1336465,101.6877054");
            } else {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }




        // setup view
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        editTextImageDesc = (EditText) findViewById(R.id.editTextDesc);
        imageView = (ImageView) findViewById(R.id.imageView);
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        FloatingActionButton fabLearn = (FloatingActionButton) findViewById(R.id.fab_learn);
        fabLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        FloatingActionButton fabReport = (FloatingActionButton) findViewById(R.id.fab_report);
        fabReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        FloatingActionButton fabDiscover = (FloatingActionButton) findViewById(R.id.fab_discover);
        fabDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiscoverActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        FloatingActionButton fabExam = (FloatingActionButton) findViewById(R.id.fab_exam);
        fabExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetQuestionActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
        bitmap.recycle();
        byte[] imageBytes = out.toByteArray();
        try {
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        out = null;

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(ReportActivity.this, s, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(ReportActivity.this, "Error on uploading image. Try again.", Toast.LENGTH_LONG).show();
                        //volleyError.getMessage().toString()
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image_data = getStringImage(bitmap);

                //Getting image_desc & image_location
                String image_desc = editTextImageDesc.getText().toString().trim();

                // get emails from SharedPref
                SharedPreferences prefs = getSharedPreferences(AppConfig.SHAREDPREF_NAME, Context.MODE_PRIVATE);
                String email = prefs.getString(AppConfig.USER_EMAIL, "");
                String userName = prefs.getString(AppConfig.USER_NAME, "");

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("image_data", image_data);
                params.put("email", email);
                params.put("image_desc", userName + " : " + image_desc);
                params.put("location", latestLocation);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {

        if (v == buttonChoose) {
            showFileChooser();
        }

        if (v == buttonUpload) {
            uploadImage();
        }

    }

}
