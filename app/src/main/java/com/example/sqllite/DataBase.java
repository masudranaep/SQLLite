package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.database.CursorWindowCompat;

import java.sql.Struct;

public class DataBase extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "underdatails.db";
    private static final String TABLE_NAME = "user_details";
    private static final String ID = "id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "UserName";
    private static final String PASSWORD = "Password";
    private static final int VERSION_NUMBER = 2;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +NAME+ " VARCHAR(255) NOT NULL, " +EMAIL+ " TEXT NOT NULL, " +USERNAME+ " TEXT NOT NULL, " +PASSWORD+ " TEXT NOT NULL);";

    private static final  String DRAP_TABLE = " DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public DataBase(@Nullable Context context) {
        super ( context, DATABASE_NAME, null, VERSION_NUMBER );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {

            sqLiteDatabase.execSQL (CREATE_TABLE);

            Toast.makeText ( context, "onCreate is Called", Toast.LENGTH_LONG ).show ();

        }catch (Exception e){
            Toast.makeText ( context, "Exception"+e,  Toast.LENGTH_LONG ).show ();


        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        try {
            Toast.makeText ( context, "onUpdate is called", Toast.LENGTH_LONG ).show ();

            db.execSQL ( DRAP_TABLE );
            onCreate ( db );
        }catch (Exception e){
            Toast.makeText ( context, "EXception :"+e, Toast.LENGTH_LONG ).show ();
        }
    }

    //insertData
    public long insertData(UserModel userModel){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();

        ContentValues contentValues = new ContentValues ();

        contentValues.put ( NAME, userModel.getName () );
        contentValues.put ( EMAIL, userModel.getEmail () );
        contentValues.put ( USERNAME, userModel.getUserName () );
        contentValues.put ( PASSWORD, userModel.getPassword () );

        long rowId = sqLiteDatabase.insert ( TABLE_NAME, null, contentValues );

        return rowId;
    }

    public Boolean findPassword(String userName, String  password){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        Cursor cursor = sqLiteDatabase.rawQuery ( "SELECT * FROM "+TABLE_NAME, null);

        Boolean result  = false;

        if (cursor.getCount () == 0){
            Toast.makeText ( context, "No data is found", Toast.LENGTH_LONG ).show ();

        }else {

            while (cursor.moveToNext ()){
                String uName = cursor.getString ( 3 );
                String pass = cursor.getString ( 4 );

                if (uName.equals ( userName ) && pass.equals ( password )){
                    result = true;
                    break;
                }
            }

        }

        return true;
    }
}
