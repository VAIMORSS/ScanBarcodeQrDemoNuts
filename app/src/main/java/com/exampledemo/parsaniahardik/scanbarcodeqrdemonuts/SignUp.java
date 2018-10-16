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





public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final Button Create_Account =(Button) findViewById(R.id.bCreateAccount);
        final Button login =(Button) findViewById(R.id.loginForSignup);
        final EditText T_id = (EditText) findViewById(R.id.idForSignup);
        final EditText T_pass = (EditText) findViewById(R.id.passForSignup1);
        final EditText T_pass2 = (EditText) findViewById(R.id.passForSignup1);

        final SQLiteDatabase mydatabase1 = openOrCreateDatabase("Lyf",MODE_PRIVATE,null);
        mydatabase1.execSQL("CREATE TABLE IF NOT EXISTS IdPass(Username VARCHAR,Password VARCHAR);");

        Create_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = T_id.getText().toString();
                String pass = T_pass.getText().toString();
                String pass2 = T_pass2.getText().toString();
                if(id.equals("")||pass.equals("")||pass2.equals("")){
                    Toast.makeText(getApplicationContext(),getString(R.string.IdpassEmptyAlert),Toast.LENGTH_SHORT).show();
                }else{
                    Cursor rsUsernames = mydatabase1.rawQuery("Select Username from idpass ",null);
                    rsUsernames.moveToFirst();
                    boolean flag=false;

                    while(rsUsernames.moveToNext()){
                        if(id.equals(rsUsernames.getString(0))){
                            flag=true;
                        }
                    }




                    if(flag!=true){
                        if(pass.equals(pass2)){
                            Toast.makeText(getApplicationContext(),getString(R.string.greetingWelcome),Toast.LENGTH_SHORT).show();
                            mydatabase1.execSQL("INSERT INTO IdPass VALUES("+"'"+id+"'"+","+"'"+pass+"');");
                        }else{
                            Toast.makeText(getApplicationContext(),getString(R.string.samePass),Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),getString(R.string.userAlreadyExists),Toast.LENGTH_SHORT).show();
                    }

                }
                //mydatabase.execSQL("INSERT INTO IdPass VALUES('admin','admin');");
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

