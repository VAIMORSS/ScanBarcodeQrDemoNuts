package com.exampledemo.parsaniahardik.scanbarcodeqrdemonuts;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class firstPage extends AppCompatActivity {
    public static TextView tvresult, tax,discount,total;
    public static EditText test;
    static LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Button btn = (Button) findViewById(R.id.btn);

        ll= (LinearLayout)findViewById(R.id.ln);
        tax= (TextView) findViewById(R.id.tax);
        discount= (TextView) findViewById(R.id.discount);
        total= (TextView) findViewById(R.id.total);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(firstPage.this, ScanActivity.class);
                startActivityForResult(intent, 1);
            }

        });

    }

}





/*
 * 1 : wait untill the scanner returns something   Sol: Thread
 *  - scan and send the information to the parent activity
 * 2 : After getting the response seartch for the id in thee database
 *    - Try and catch
 *           --> catch means the tag scanned is not available then send the code to the customerCare dipartment and set it on the database as a non-product code so that next time dipartment do not have to look at that*/


/*for (product a: ProductList)
                {   //if it is tyhe same product then do not adding it
                            System.out.println("Product List "+a.pId+">>>>>>>>>>>>>>>"+  testForDuplicates +">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            a.append2(tvtest);
                            testForDuplicates = a.pId;
                }*/
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode == RESULT_OK){
            editTextString = data.getStringExtra("ID");
        }
    }
*/
