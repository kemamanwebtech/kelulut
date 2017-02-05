package org.kemaman.kelulut_main.utils;

/**
 * Created by seraf on 1/9/17.
 */

 import android.content.Context;
 import android.content.SharedPreferences;
 import android.content.SharedPreferences.Editor;
 import android.util.Log;

public class SessionManager {

    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(AppConfig.SHAREDPREF_NAME, 0);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(AppConfig.IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(AppConfig.IS_LOGGEDIN, false);
    }
}
