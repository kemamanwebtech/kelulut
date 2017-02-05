package org.kemaman.kelulut_main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.kemaman.kelulut_main.utils.AppConfig;
import org.kemaman.kelulut_main.utils.AppController;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ViewImageActivity extends AppCompatActivity {
    private static final String TAG = ViewImageActivity.class.getSimpleName();
    String id;
    ImageView imgRetrieved;
    Button btnAddComment;
    TextView txtComment;
    String comment;
    String userName;
    EditText editComment;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        id = getIntent().getExtras().getString("id","");

        imgRetrieved = (ImageView) findViewById(R.id.imgRetrieved);

        editComment = (EditText)  findViewById(R.id.editComment);
        btnAddComment = (Button) findViewById(R.id.btnAddComment);
        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment(id, editComment.getText().toString());
            }
        });

        getImage(id);

        txtComment = (TextView) findViewById(R.id.txtComment);
        getComment(id);



    }

    private void getImage(final String id) {

        // Tag used to cancel the request
        String tag_string_getImage = "req_getImage";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_GET_IMAGE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);

                // change imageview here
                new DownloadImageTask((ImageView) findViewById(R.id.imgRetrieved))
                        .execute(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }
        };

        // Adding request to request queue
        if (AppController.getInstance() == null){
            Log.e(TAG, "AppController.getInstance() is null");
        }
        AppController.getInstance().addToRequestQueue(strReq, tag_string_getImage);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);

        }
    }

    private void getComment(final String id){
            // Tag used to cancel the request
            String tag_string_getComment = "req_getComment";

            StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_GET_COMMENT, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response: " + response);
                    txtComment.setText(response);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", id);
                    return params;
                }
            };

            // Adding request to request queue
            if (AppController.getInstance() == null){
                Log.e(TAG, "AppController.getInstance() is null");
            }
            AppController.getInstance().addToRequestQueue(strReq, tag_string_getComment);
    }

    // add user name to params
    private void addComment(final String inId, final String inComment){
        // Tag used to cancel the request
        String tag_string_addComment = "req_addComment";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_ADD_COMMENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response);
                SweetAlertDialog t = new SweetAlertDialog(ViewImageActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        t.setTitleText("Success!");
                        t.setContentText("Your comment has been successfully saved!");
                        t.show();

                Intent intent = new Intent(getApplicationContext(), ViewImageActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                SharedPreferences prefs = getSharedPreferences(AppConfig.SHAREDPREF_NAME, Context.MODE_PRIVATE);
                userName = prefs.getString(AppConfig.USER_NAME, "John Doe");

                Map<String, String> params = new HashMap<String, String>();
                params.put("id", inId);
                params.put("name", userName);
                params.put("comment", inComment);
                return params;
            }
        };

        // Adding request to request queue
        if (AppController.getInstance() == null){
            Log.e(TAG, "AppController.getInstance() is null");
        }
        AppController.getInstance().addToRequestQueue(strReq, tag_string_addComment);
    }

}
