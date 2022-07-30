package com.example.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqllite.project.ListDataActivity;
import com.example.sqllite.project.ProDatabase;

import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    SqlDatabase sqlDatabase;

    DataBase dataBase;

    private ProDatabase proDatabase;


    private EditText idEt, nameET;
    private Button saveBtn, showBtn, updateBtn, deleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );


        idEt = (EditText) findViewById ( R.id.id_ET );
        nameET = (EditText) findViewById ( R.id.name_ET );

        saveBtn = (Button)findViewById ( R.id.saveBtn );
        showBtn = (Button)findViewById ( R.id.showBtn );
        updateBtn = (Button)findViewById ( R.id.updateBtn );
        deleteBtn = (Button)findViewById ( R.id.deleteBtn );


       sqlDatabase = new SqlDatabase ( this );


//       nameET = (EditText)findViewById ( R.id.nameET );
//        ageEt = (EditText)findViewById ( R.id.ageET );
//        ganderET = (EditText)findViewById ( R.id.genderET );
//        idET = (EditText)findViewById ( R.id.IdET );
//        addDataBtn = (Button) findViewById ( R.id.submitbtn );
//        showData = (Button) findViewById ( R.id.showDataOld );
//        updateBtn = (Button) findViewById ( R.id.updateData );
//        deleteBtn = (Button) findViewById ( R.id.deleteData );


       // SQLiteDatabase sqLiteDatabase = sqlDatabase.getWritableDatabase ();

//        addDataBtn.setOnClickListener ( this );
//        showData.setOnClickListener ( this );
//        updateBtn.setOnClickListener ( this );
//        deleteBtn.setOnClickListener ( this );





        proDatabase = new ProDatabase ( this );
         SQLiteDatabase sqLiteDatabase =  dataBase.getWritableDatabase ();


         saveBtn.setOnClickListener ( this );
         showBtn.setOnClickListener ( this );

        updateBtn.setOnClickListener ( this );
        deleteBtn.setOnClickListener ( this );


    }

    @Override
    public void onClick(View v) {

        String id = idEt.getText ().toString ();
        String name = nameET.getText ().toString ();

        if (v.getId () == R.id.saveBtn){

            if(id.equals ( " " ) && name.equals ( " " )){
                Toast.makeText ( getApplicationContext (), "Please enter all the data", Toast.LENGTH_LONG ).show ();
            }else {

              long rowNumber = proDatabase.SavaData (id, name);

              if(rowNumber > -1){
                  Toast.makeText ( getApplicationContext (), "Data is inserted succssfully", Toast.LENGTH_LONG ).show ();
                  idEt.setText ( " " );
                  nameET.setText ( " " );

              }else {
                  Toast.makeText ( getApplicationContext (), "Data is inserted succssfully", Toast.LENGTH_LONG ).show ();

              }
            }


        }else if(v.getId () == R.id.showBtn){


            Intent intent = new Intent (MainActivity.this, ListDataActivity.class );
            startActivity ( intent );

        }else if(v.getId () == R.id.updateBtn){

            Boolean isUpdated = proDatabase.updateData ( id, name);

            if (isUpdated == true){

                Toast.makeText ( getApplicationContext (), "Date is Update", Toast.LENGTH_LONG ).show ();
            }else {
                Toast.makeText ( getApplicationContext (),"Data is updated",  Toast.LENGTH_LONG ).show ();
            }

        }else if (v.getId () == R.id.deleteBtn){

            int value = proDatabase.deleteData ( id );

            if (value < 0){

                Toast.makeText ( getApplicationContext (), "Data is  not delete", Toast.LENGTH_LONG ).show ();
            }else {
                Toast.makeText ( getApplicationContext (), "Data is  delete", Toast.LENGTH_LONG ).show ();
            }

        }

        //sign
//      String  userName = userNameET.getText ().toString ();
//      String password = passwordET.getText ().toString ();
//
//
//        if (v.getId () == R.id.signInBtn){
//
//            Boolean result  = dataBase.findPassword(userName, password);
//
//            if (result == true){
//
//                Intent intent = new Intent (MainActivity.this, ResultPage.class);
//                startActivity ( intent );
//            }else {
//
//                Toast.makeText ( getApplicationContext (), "userName and Password didn", Toast.LENGTH_LONG );
//            }
//
//
//
//    }else  if (v.getId () == R.id.SignUphearBtn){
//
//            Intent intent = new Intent (MainActivity.this, SignUPActivity.class);
//            startActivity ( intent );
//
//        }

//    // 2 set
//    @Override
//    public void onClick(View v) {
//
//        String name = nameET.getText ().toString ();
//        String age = ageEt.getText ().toString ();
//        String gender = ganderET.getText ().toString ();
//        String id = idET.getText ().toString ();
//
//
//        if (v.getId () == R.id.submitbtn) {
//
//            long rowId = sqlDatabase.insertData ( name, age, gender );
//
//            if (rowId == -1) {
//
//                Toast.makeText ( getApplicationContext (), "Row " + rowId + "Not Sucessfully inserted", Toast.LENGTH_LONG ).show ();
//
//            } else {
//                Toast.makeText ( getApplicationContext (), "Row " + rowId + "is Sucessfully inserted", Toast.LENGTH_LONG ).show ();
//
//            }
//        }
//        // show data
//        if (v.getId () == R.id.showDataOld){
//
//            Cursor cursor = sqlDatabase.displaydataShow ();
//
//            if (cursor.getCount () == 0){
//
//                showData("Error", "No data Found");
//
//                return;
//            }
//            StringBuffer stringBuffer  = new StringBuffer ();
//
//            while (cursor.moveToNext ()){
//
//                stringBuffer.append ( "ID : "+cursor.getString ( 0 )+ "\n" );
//                stringBuffer.append ( "Name : "+cursor.getString ( 1 )+ "\n");
//                stringBuffer.append ( "Age : "+cursor.getString ( 2 )+ "\n");
//                stringBuffer.append ( "Gender : "+cursor.getString ( 3 )+ "\n\n" );
//
//
//            }
//            showData("ResultSet", stringBuffer.toString ());
//        }


//
//        //update button
//        else if(v.getId () == R.id.updateData){
//
//            Boolean isUpdate = sqlDatabase.updateData ( id, name, age, gender );
//
//            if(isUpdate == true){
//
//                Toast.makeText ( getApplicationContext (), "Data is Update", Toast.LENGTH_LONG ).show ();
//
//            }else {
//                Toast.makeText ( getApplicationContext (), "Data is Notr Update", Toast.LENGTH_LONG ).show ();
//            }
//        }


//        //delete data
//        if (v.getId () == R.id.deleteData){
//
//            int value = sqlDatabase.deleteDate ( id );
//            if (value > 0){
//
//                Toast.makeText ( getApplicationContext (), "Delete Data", Toast.LENGTH_LONG ).show ();
//            }else {
//                Toast.makeText ( getApplicationContext (), "Data Not Delete", Toast.LENGTH_LONG ).show ();
//            }
//        }
//

    }

// showData
//    private void showData(String title, String message) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
//
//        builder.setTitle ( title );
//        builder.setMessage ( message );
//        builder.setCancelable ( true );
//        builder.show ();
//
//
//
//    }
}