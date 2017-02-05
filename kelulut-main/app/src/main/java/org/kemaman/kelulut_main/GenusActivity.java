package org.kemaman.kelulut_main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.kemaman.kelulut_main.utils.AppConfig;
import org.kemaman.kelulut_main.utils.AppController;
import java.util.HashMap;
import java.util.Map;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class GenusActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    //ProgressDialog pDialog;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup progress dialog box
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading...");
        pDialog.setCancelable(false);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            String genus = (String) b.get("GENUS");

            // get the image drawable name from passed intent
            String imageDrawableName = "";
            String[] strArray = genus.split(" ");
            StringBuilder builder = new StringBuilder();
            for (String s : strArray) {
                imageDrawableName = s.substring(0, 1).toLowerCase() + s.substring(1);
                builder.append(imageDrawableName + " ");
            }

            ImageView genusPic = (ImageView) findViewById(R.id.genus_picture);
            Drawable img = getImageDrawable(imageDrawableName);
            genusPic.setImageDrawable(img);

            TextView genusName = (TextView) findViewById(R.id.genus_name);
            genusName.setText(genus);
            genusName.setTextSize(18);


            TextView listSpeciesText = (TextView) findViewById(R.id.list_species);
            listSpeciesText.setTextSize(18);

            getGenusInfo(genus);

            getGenusSpecies(genus);

        }

    }

    private void getGenusInfo(final String genus) {
        // show progress dialog
        pDialog.show();

        // Tag used to cancel the request
        String tag_get_genus = "req_get_genus";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_GET_GENUS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //hideDialog();
                pDialog.hide();

                if (!(response.contains("Error."))) {
                    TextView genus_info = (TextView) findViewById(R.id.genus_info);
                    genus_info.setText(response);
                    genus_info.setTextSize(18);
                } else {
                    Toast.makeText(getApplicationContext(),"No info from database.",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "getgenus Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
                pDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("genus_name", genus);
                return params;
            }
        };

        // Adding request to request queue
        if (AppController.getInstance() == null){
            Log.e(TAG, "AppController.getInstance() is null");
        }
        AppController.getInstance().addToRequestQueue(strReq, tag_get_genus);

    }


    private void getGenusSpecies(final String genus) {
        // show progress dialog
        pDialog.show();

        // Tag used to cancel the request
        String tag_get_genus_species = "req_get_genus_species";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_GET_GENUS_SPECIES, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //hideDialog();
                pDialog.hide();

                if (!(response.contains("Error."))) {
                    TextView genus_species = (TextView) findViewById(R.id.genus_species);
                    genus_species.setText(response);
                    genus_species.setTextSize(18);
                } else {
                    Toast.makeText(getApplicationContext(),"No info from database",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "getSpecies Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
                pDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("genus_name", genus.substring(0,2));
                return params;
            }
        };

        // Adding request to request queue
        if (AppController.getInstance() == null){
            Log.e(TAG, "AppController.getInstance() is null");
        }
        AppController.getInstance().addToRequestQueue(strReq, tag_get_genus_species);

    }


    public Drawable getImageDrawable(String drawableName) {
        Resources res = getResources();
        int resID = res.getIdentifier(drawableName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID );
        return drawable;
    }

}
