package com.example.sqllite.project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqllite.R;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private ListView listView;
    private ProDatabase proDatabase;
    AdapterView adapterView;
    private Object View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_list_data );

        listView = (ListView) findViewById ( R.id.listView );

        proDatabase = new ProDatabase ( this);
    }

    //load data
    public void loadData(){

        ArrayList<String> listData = new ArrayList<> ();
        Cursor cursor = proDatabase.showData();

        if (cursor.getCount () == 0){

            Toast.makeText ( getApplicationContext (), "No Data is availble in database", Toast.LENGTH_LONG ).show ();
        }else {
            while (cursor.moveToNext ()){
                listData.add ( cursor.getString ( 0 ) + " \t "+cursor.getString ( 1 ) );
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String> ( this, R.layout.list_item, R.id.textViewId , listData);

        listView.setAdapter ( adapter );



      listView.setOnClickListener ( new View.OnClickListener () {
          @Override
          public void onClick(View v) {
              String selectValue = adapterView.getItemAtPosition ( (Integer) View ).toString ();
              Toast.makeText ( getApplicationContext (), "Seleted value :"+selectValue, Toast.LENGTH_LONG).show ();
          }
      } );
    }
}