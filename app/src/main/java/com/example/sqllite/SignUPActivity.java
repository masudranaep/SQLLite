package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameET, emailET, userNameET, passwordET;

    private Button signUPbtn;
    UserModel userModel;

    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sign_upactivity );


        nameET = (EditText) findViewById ( R.id.name_ET );
        emailET = (EditText) findViewById ( R.id.email_ET );
        userNameET = (EditText) findViewById ( R.id.userName_ET );
        passwordET = (EditText) findViewById ( R.id.password_ET );

        signUPbtn = (Button) findViewById ( R.id.signUPbtn );

        signUPbtn.setOnClickListener ( this );


        userModel = new UserModel ();

        dataBase = new DataBase ( this );

    }

    @Override
    public void onClick(View v) {

        String name = nameET.getText ().toString ();
        String email = emailET.getText ().toString ();
        String userName = userNameET.getText ().toString ();
        String password = passwordET.getText ().toString ();

        userModel.setName ( name );
        userModel.setEmail ( email );
        userModel.setUserName ( userName );
        userModel.setPassword ( password );


       long rowId = dataBase.insertData ( userModel );

       if (rowId > 0){

           Toast.makeText ( getApplicationContext (), "Row "+ rowId + "is successfull inserted", Toast.LENGTH_LONG ).show ();
       }else {
           Toast.makeText ( getApplicationContext (), "Row insertion failed", Toast.LENGTH_LONG ).show ();

       }

    }
}