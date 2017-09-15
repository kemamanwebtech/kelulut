package org.kemaman.kelulut_main.utils;

import android.content.Context;

/**
 *  Class to hold App's web servies URL, setting, TAG and preferences.
 */

public class AppConfig {

//    public static String URL_LOGIN = "http://145.239.86.102/kelulut/login.php";
//    public static String URL_REGISTER = "http://145.239.86.102/kelulut/register.php";
//    public static String URL_GET_GENUS = "http://145.239.86.102/kelulut/get_genus_info.php";
//    public static String URL_GET_GENUS_SPECIES = "http://145.239.86.102/kelulut/get_genus_species.php";
//    public static String URL_UPLOAD = "http://145.239.86.102/kelulut/upload_image.php";
//    public static String URL_GETLOCATION = "http://145.239.86.102/kelulut/get_location.php";
//    public static String URL_GET_IMAGE = "http://145.239.86.102/kelulut/get_image.php";
//    public static String URL_GET_COMMENT = "http://145.239.86.102/kelulut/get_comment.php";
//    public static String URL_ADD_COMMENT = "http://145.239.86.102/kelulut/add_comment.php";
//    public static String URL_GET_QUESTION = "http://145.239.86.102/kelulut/get_question.php";

    public static String URL_LOGIN = "http://145.239.86.102:5000/login";
    public static String URL_REGISTER = "http://145.239.86.102:5000/register";
    public static String URL_GET_GENUS = "http://145.239.86.102:5000/get_genus_info";
    public static String URL_GET_GENUS_SPECIES = "http://145.239.86.102:5000/get_genus_species";
    public static String URL_UPLOAD = "http://145.239.86.102:5000/upload_image";
    public static String URL_GETLOCATION = "http://145.239.86.102:5000/get_location";
    public static String URL_GET_IMAGE = "http://145.239.86.102:5000/get_image";
    public static String URL_GET_COMMENT = "http://145.239.86.102:5000/get_comment";
    public static String URL_ADD_COMMENT = "http://145.239.86.102:5000/add_comment";
    public static String URL_GET_QUESTION = "http://145.239.86.102:5000/get_question";
    public static String URL_SAVE_SCORE = "http://145.239.86.102:5000/save_score";

    // For Shared Pref
    public static String SHAREDPREF_NAME = "KELULUT_SHAREDPREF";
    public static String FIRST_TIME = "first_time";
    public static String REG_STATUS = "reg_status";
    public static String IS_LOGGEDIN = "isLoggedIn";
    public static String USER_NAME = "user_name";
    public static String USER_EMAIL = "email";
    public static String USER_PASSWORD = "password";

}
