package com.example.sqllite.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
public class ProDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "id";
    private static final String NAME = "Name";
    private static final int VERSIOT_NUMBER = 1;

    private static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(30)); ";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private Context context;

    public ProDatabase(@Nullable Context context) {
        super ( context, DATABASE_NAME, null, VERSIOT_NUMBER );

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

      try {
          db.execSQL ( CREATE_TABLE );

          Toast.makeText ( context, "onCreate is called", Toast.LENGTH_LONG ).show ();

      }catch (Exception e){

          Toast.makeText ( context, "Exception"+e, Toast.LENGTH_LONG ).show ();

      }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {


            Toast.makeText ( context, "onUpdate is called", Toast.LENGTH_LONG ).show ();
            db.execSQL ( DROP_TABLE );

            onCreate ( db );

        }catch (Exception e){

            Toast.makeText ( context, "Exception"+e, Toast.LENGTH_LONG ).show ();

        }

    }

    //save ddata
    public long SavaData(String id, String name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();

        contentValues.put ( ID, id );
        contentValues.put ( NAME, name );

        long rowNumber = sqLiteDatabase.insert ( TABLE_NAME, null, contentValues );

        return rowNumber;
    }

    //showdata

    public Cursor showData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        Cursor cursor = sqLiteDatabase.rawQuery ( "SELECT * FROM "+ TABLE_NAME, null );



        return cursor;
    }

    //update data

    public Boolean updateData(String id, String name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues.put ( ID, id );
        contentValues.put ( NAME, name );
        sqLiteDatabase.update ( TABLE_NAME, contentValues, ID+ " ?", new String[] {id} );
        return true;
    }

    //delete data
    public int deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        int value = sqLiteDatabase.delete ( TABLE_NAME, ID + " = ?", new  String[] {id});
        return value;
    }
}
