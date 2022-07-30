package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.jar.Attributes;

public class SqlDatabase extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "Student.db";
    private static  final String TABLE_NAME = "student_details";
    private static final int VERVION_NUMBER = 1;

    private  static final String NANE = "Name";
    private static  final String AGE = "Age";
    private static final String ID = "id";
    private static final String GENDER = "gender";

    private  static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT," + NANE +" VARCHAR(255), "+AGE+" INTERGER, "+GENDER+" VARCHER(15) );";
    

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;


    private Context context;

    public SqlDatabase(@Nullable Context context) {
        super ( context, DATABASE_NAME, null, VERVION_NUMBER);

        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            Toast.makeText ( context, "onCreate is called",Toast.LENGTH_LONG ).show ();
            sqLiteDatabase.execSQL ( CREATE_TABLE );

        } catch (Exception e) {

            Toast.makeText ( context, "Exception : "+e, Toast.LENGTH_LONG).show ();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast.makeText ( context, "onUpgrade is called",Toast.LENGTH_LONG ).show ();


            db.execSQL ( DROP_TABLE );
            onCreate ( db );
        } catch (SQLException e) {
            Toast.makeText ( context, "Exception : "+e, Toast.LENGTH_LONG).show ();
        }



    }
    //  set 2
     public  long insertData(String name, String age, String gander){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
         ContentValues contentValues = new ContentValues ();
         contentValues.put ( NANE, name );
         contentValues.put ( AGE, age );
         contentValues.put ( GENDER, gander );

         long rowId = sqLiteDatabase.insert ( TABLE_NAME, null, contentValues );

         return rowId;
     }

     // showdatadisplay

    public Cursor displaydataShow(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        Cursor cursor = sqLiteDatabase.rawQuery ( SELECT_ALL, null );


        return cursor;
    }

    //update data
    public boolean updateData(String id, String name, String age, String gender){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put ( ID, id );
        contentValues.put ( NANE, name );
        contentValues.put ( AGE, age );
        contentValues.put ( GENDER, gender );

        sqLiteDatabase.update ( TABLE_NAME, contentValues, ID+" = ?", new String[]{} );

      return true;
    }

    //delete data

    public  int deleteDate(String id){

    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase () ;
    return  sqLiteDatabase.delete ( TABLE_NAME, ID+ " = ?", new String[]{ id } );

    }

}
