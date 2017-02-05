package org.kemaman.kelulut_main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.kemaman.kelulut_main.utils.AppConfig;
import org.kemaman.kelulut_main.utils.AppController;
import org.kemaman.kelulut_main.utils.SQLiteHandler;
import org.kemaman.kelulut_main.utils.SessionManager;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private EditText inputEmail;
    private EditText inputPassword;
    private Button btnLinkToRegister;
    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Progress dialog
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setCancelable(false);

        // check if user is already logged in previously
        SharedPreferences prefs = getSharedPreferences(AppConfig.SHAREDPREF_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean(AppConfig.IS_LOGGEDIN, false);
        boolean hasEmail = prefs.contains(AppConfig.USER_EMAIL);
        if (isLoggedIn) {
            if (hasEmail) {

                // TODO : run the splash/tutorial/intro screen if first time running

                // user is already logged in. Proceed to InfoActivity
                Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        }

        // Login Button Click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String passwd = inputPassword.getText().toString();

                if (!email.isEmpty() && !passwd.isEmpty()) {
                    Log.d(TAG, "Attempt to login...");
                    loginUser(email, passwd);
                } else {
                    Toast.makeText(getApplicationContext(), "Something was wrong. Please enter your login info.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void loginUser(final String email, final String password) {

        // Tag used to cancel the request
        String tag_string_login = "req_login";

        pDialog.setMessage("Login in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);
                hideDialog();
                Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();

                if (response.contains("Successfully")) {

                    // User logged in successfully. Now, save email and password to SharedPref. Change state to IsloggedIn = true.
                    SharedPreferences prefs = getSharedPreferences(AppConfig.SHAREDPREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean(AppConfig.IS_LOGGEDIN, true);
                    editor.putString(AppConfig.USER_EMAIL, email);
                    editor.putString(AppConfig.USER_PASSWORD, email);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Successfully login.", Toast.LENGTH_LONG).show();

                    // go to InfoActivity upon login
                    Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(LoginActivity.this,"Not successful login. Check your login details.",Toast.LENGTH_LONG).show();

                    // restart activity
                    Intent intent = getIntent();
                    overridePendingTransition(0, 0);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("passwd", password);
                return params;
            }
        };

        // Adding request to request queue
        if (AppController.getInstance() == null){
            Log.e(TAG, "AppController.getInstance() is null");
        }
        AppController.getInstance().addToRequestQueue(strReq, tag_string_login);
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
