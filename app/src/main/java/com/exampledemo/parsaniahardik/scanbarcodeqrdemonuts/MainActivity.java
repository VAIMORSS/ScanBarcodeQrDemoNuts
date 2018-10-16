package com.exampledemo.parsaniahardik.scanbarcodeqrdemonuts;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SQLiteDatabase mydatabase = openOrCreateDatabase("Lyf",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS IdPass(Username VARCHAR,Password VARCHAR);");




        final Button B_login = (Button) findViewById(R.id.login);
        final Button B_signup = (Button) findViewById(R.id.signup);
        final EditText T_id = (EditText) findViewById(R.id.id);
        final EditText T_pass = (EditText) findViewById(R.id.pass);

        B_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = T_id.getText().toString();
                String pass = T_pass.getText().toString();



                String q="SELECT * FROM Idpass";

                boolean flag=false,flag2=false;

                Cursor rs= mydatabase.rawQuery(q,null);

                while(rs.moveToNext()){
                    if(id.equals(rs.getString(0))){
                        flag=true;

                    }
                    if(pass.equals(rs.getString(1))){
                        flag2=true;

                    }
                }


                if(id.equals("")||pass.equals("")){
                    Toast.makeText(getApplicationContext(),getString(R.string.IdpassEmptyAlert), Toast.LENGTH_SHORT).show();
                }else{
                    if(!flag) {
                        Toast.makeText(getApplicationContext(), getString(R.string.userNotFound), Toast.LENGTH_LONG).show();
                    }

                    if(flag){
                        if(flag2){
                            //Toast.makeText(getApplicationContext(), "You are the person", Toast.LENGTH_LONG).show();
                            launchFirstPageActivity();


                        }else{
                            Toast.makeText(getApplicationContext(), getString(R.string.WrongPass), Toast.LENGTH_LONG).show();
                        }
                    }
                }



            }
        });

        B_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();
            }
        });
    }
    private void launchActivity(){
        Intent intentSignup = new Intent(this,SignUp.class);
        startActivity(intentSignup);
    }

    private void launchFirstPageActivity(){
        Intent intentFirstPage = new Intent(this,firstPage.class);
        startActivity(intentFirstPage);
    }







}
