package org.kemaman.kelulut_main.utils;

/**
 * Created by seraf on 1/9/17.
 *
 * Helper class for local sqlite database
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

// all Data Transfer Objects (DTO)
import org.kemaman.kelulut_main.dto.DTOInfoKelulut;
// TODO : create all other DTOs here

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static final int DATABASE_VERSION = 1; // Database Version
    private static final String DATABASE_NAME = "db_kelulut"; // Database Name, make it the same as remote MySQL database

    // List table name
    private static final String TABLE_USER = "kelulut_user";
    private static final String TABLE_INFO_KELULUT = "info_kelulut";
    private static final String TABLE_SPECIES = "species";
    private static final String TABLE_GENUS_PICTURE = "genus_picture";
    private static final String TABLE_MAP = "kelulut_map";
    private static final String TABLE_QUIZ = "quiz";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // query to CREATE all tables
        String CREATE_USER_TABLE = " CREATE TABLE " + TABLE_USER + "( "
                + " name TEXT,"
                + " email TEXT,"
                + " passwd TEXT ) ";

        String CREATE_INFO_KELULUT_TABLE = " CREATE TABLE " + TABLE_INFO_KELULUT + "( "
                + " genus_name TEXT,"
                + " about TEXT ) ";

        String CREATE_SPECIES_TABLE = " CREATE TABLE " + TABLE_SPECIES + "( "
                + " genus_name TEXT,"
                + " species TEXT ) ";

        String CREATE_GENUS_PICTURE_TABLE = " CREATE TABLE " + TABLE_GENUS_PICTURE + "( "
                + " genus_name TEXT,"
                + " picture TEXT ) ";

        String CREATE_MAP_TABLE = " CREATE TABLE " + TABLE_MAP + "( "
                + " name TEXT,"
                + " picture TEXT ), "
                + " location TEXT ), "
                + " uploaded TEXT ), "
                + " approved TEXT ) ";

        String CREATE_QUIZ_TABLE = " CREATE TABLE " + TABLE_QUIZ + "( "
                + " question TEXT,"
                + " picture TEXT ), "
                + " answer1 TEXT ), "
                + " answer2 TEXT ), "
                + " answer3 TEXT ), "
                + " answer4 TEXT ), "
                + " correct_answer TEXT ) ";

        // create tables
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_INFO_KELULUT_TABLE);
        db.execSQL(CREATE_SPECIES_TABLE);
        db.execSQL(CREATE_GENUS_PICTURE_TABLE);
        db.execSQL(CREATE_MAP_TABLE);
        db.execSQL(CREATE_QUIZ_TABLE);

        Log.d(TAG, "All local sqlite database tables created");
    }

    /**
     *  Upgrading local sqlite database
     * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO_KELULUT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPECIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENUS_PICTURE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);

        // Create tables again
        onCreate(db);
    }

    /**
     *  Storing user details in local sqlite database
     * */
    public void insertDtoUser(String name, String email, String passwd) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);           // Name
        values.put("email", email);         // Email
        values.put("passwd", passwd);       // Password

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);

        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into table " + TABLE_USER + " , " + id);
    }

    /**
     * Getting user data from local sqlite database
     *  TODO : change hashmap to DTOUser class
     * */
    public HashMap<String, String> getDtoUser() {

        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  name, email, passwd FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("passwd", cursor.getString(3));
        }
        cursor.close();
        db.close();

        // return user
        Log.d(TAG, "Fetching user from table " + TABLE_USER + ", " + user.toString());
        return user;
    }




    /**
     *   get DTOInfoKelulut for Info Page
     *   TODO : check if DTOInfoKelulut can be retrieved successfuly from sqlite
     * */
    public ArrayList<DTOInfoKelulut> getDtoInfoKelulut(){

        String selectQuery = "SELECT  genus_name, about FROM " + TABLE_INFO_KELULUT;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();

        // create list to hold our retrieved data
        ArrayList<DTOInfoKelulut> dTOInfoKelulutList = new ArrayList<DTOInfoKelulut>();

        if (cursor.getCount() > 0) {
            DTOInfoKelulut newDTOInfoKelulut = new DTOInfoKelulut(cursor.getString(1), cursor.getString(2));
            dTOInfoKelulutList.add(newDTOInfoKelulut);
        }

        cursor.close();
        db.close();

        // return user
        Log.d(TAG, "Fetching DTOInfoKelulutList from sqlite database");
        return dTOInfoKelulutList;
    }

    // TODO : create GETTERS method for DTO user (rename), species, genus_picture, map, quiz
    // TODO : create a method sync all table from remote mysql to local sqlite

}

