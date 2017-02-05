package org.kemaman.kelulut_main.utils;

import android.content.Context;

/**
 *  Class to hold App's web servies URL, setting, TAG and preferences.
 */

public class AppConfig {

    public static String URL_LOGIN = "http://144.217.93.255/kelulut/login.php";
    public static String URL_REGISTER = "http://144.217.93.255/kelulut/register.php";
    public static String URL_GET_GENUS = "http://144.217.93.255/kelulut/get_genus_info.php";
    public static String URL_GET_GENUS_SPECIES = "http://144.217.93.255/kelulut/get_genus_species.php";
    public static String URL_UPLOAD = "http://144.217.93.255/kelulut/upload_image.php";
    public static String URL_GETLOCATION = "http://144.217.93.255/kelulut/get_location.php";
    public static String URL_GET_IMAGE = "http://144.217.93.255/kelulut/get_image.php";
    public static String URL_GET_COMMENT = "http://144.217.93.255/kelulut/get_comment.php";
    public static String URL_ADD_COMMENT = "http://144.217.93.255/kelulut/add_comment.php";
    public static String URL_GET_QUESTION = "http://144.217.93.255/kelulut/get_question.php";

    // For Shared Pref
    public static String SHAREDPREF_NAME = "KELULUT_SHAREDPREF";
    public static String FIRST_TIME = "first_time";
    public static String REG_STATUS = "reg_status";
    public static String IS_LOGGEDIN = "isLoggedIn";
    public static String USER_NAME = "user_name";
    public static String USER_EMAIL = "email";
    public static String USER_PASSWORD = "password";

}
