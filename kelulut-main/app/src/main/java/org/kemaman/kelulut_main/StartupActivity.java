package org.kemaman.kelulut_main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.kemaman.kelulut_main.utils.AppConfig;

/*
 * Startup's activity for the App
 */
public class StartupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(AppConfig.SHAREDPREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // check if first time running
        boolean isFirst = prefs.getBoolean(AppConfig.FIRST_TIME, true);
        if (isFirst){
            editor.putBoolean(AppConfig.FIRST_TIME, false);
            editor.commit();

            // TODO : run the splash/tutorial/intro screen if first time running
        }

        // check if have already registered
        boolean isRegistered = prefs.getBoolean(AppConfig.REG_STATUS, false);
        if (isRegistered) {
            // go to login Activity if already registered
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // go to register activity if have not yet registered
            editor.putBoolean(AppConfig.FIRST_TIME, true);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
