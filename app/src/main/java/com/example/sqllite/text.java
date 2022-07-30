package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.jar.Attributes;
import java.util.stream.Stream;

public class text extends SQLiteOpenHelper {

    private static final String DATABSE_NAME = "underditail.db";
    private static final String TABLE_NAME = "user_detils";
    private static final String ID = "id";
    private static final int VERSION_NUMBER = 1;
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) NOT NULL, "+EMAIL+" NOT NULL, "+USERNAME+" NOT NUll, "+PASSWORD+" NOT NULL);";
    private static final String DROP_TABLE = " DROP TABLE IF EXISTS "+TABLE_NAME;

    private Context context;
    public text(@Nullable Context context) {
        super ( context, DATABSE_NAME, null, VERSION_NUMBER );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL ( CREATE_TABLE );
            Toast.makeText ( context, "OnCreate is called", Toast.LENGTH_LONG ).show ();
        }catch (Exception e){
            Toast.makeText ( context, "Exception"+e, Toast.LENGTH_LONG ).show ();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        try {
            Toast.makeText ( context, "OnUpdate is called", Toast.LENGTH_LONG ).show ();

            db.execSQL ( DROP_TABLE);
            onCreate ( db );

        }catch (Exception e){
            Toast.makeText ( context, "Exception"+e, Toast.LENGTH_LONG ).show ();


        }

    }
}
