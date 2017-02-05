package org.kemaman.kelulut_main;

import java.util.HashMap;
import java.util.Map;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.kemaman.kelulut_main.utils.AppConfig;
import org.kemaman.kelulut_main.utils.AppController;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputName;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    String userName = "";
    String email = "";
    String passwd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        pDialog = new ProgressDialog(RegisterActivity.this);
        pDialog.setCancelable(false);

        // check if user is already logged in previously
        SharedPreferences prefs = getSharedPreferences(AppConfig.SHAREDPREF_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean(AppConfig.IS_LOGGEDIN, false);
        boolean hasEmail = prefs.contains(AppConfig.USER_EMAIL);
        if (isLoggedIn) {
            if (hasEmail) {
                //user is already logged in. Proceed to InfoActivity
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                finish();
            }
        }

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                userName = inputName.getText().toString().trim();
                email = inputEmail.getText().toString().trim();
                passwd = inputPassword.getText().toString();

                if (!userName.isEmpty() && !email.isEmpty() && !passwd.isEmpty()) {
                    registerUser(userName, email, passwd);
                } else {
                    Toast.makeText(getApplicationContext(), "Something was wrong. Please re-enter your details.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void registerUser(final String userName, final String email, final String password) {

        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Register Response: " + response);
                hideDialog();
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                if (response.contains("Error")){
                    // refresh this activity
                    Intent intent = getIntent();
                    overridePendingTransition(0, 0);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                } else {

                    // User logged in successfully. Now, save email and password to SharedPref. Change state to IsloggedIn = true.
                    SharedPreferences prefs = getSharedPreferences(AppConfig.SHAREDPREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(AppConfig.USER_NAME, userName);
                    editor.commit();


                    // Launch login activity
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", userName);
                params.put("email", email);
                params.put("passwd", password);
                return params;
            }
        };

        // Adding request to request queue
        if (AppController.getInstance() == null){
            Log.e(TAG, "AppController.getInstance() is null");
        }
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}