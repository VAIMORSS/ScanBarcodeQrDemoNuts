package com.exampledemo.parsaniahardik.scanbarcodeqrdemonuts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zbar.ZBarScannerView;




public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    //camera permission is needed.

    public double total;
    public double totaldiscount;
    public double totalTax;

    ////////////
    TextView tvForProName, tvForProPrice;
    List<product> ProductList = new ArrayList<product>();




    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view



        //////////////////////////////////////////////
        final SQLiteDatabase mydatabase = openOrCreateDatabase("Lyf", MODE_PRIVATE, null);
       /* mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Product(Product_ID VARCHAR,Product_Name VARCHAR, Paroduct_Price REAL, Product_Weight REAL);");
        mydatabase.execSQL("INSERT INTO Product VALUES('123456','test',12,5);");
        mydatabase.execSQL("INSERT INTO Product VALUES('712345678911','test1',123,5.1);");
        mydatabase.execSQL("INSERT INTO Product VALUES('012345678905','test2',124,5.2);");
        mydatabase.execSQL("INSERT INTO Product VALUES('671860013624','test3',125,5.3);");
        mydatabase.execSQL("INSERT INTO Product VALUES('39123439','test4',126,5.4);");
        mydatabase.execSQL("INSERT INTO Product VALUES('AB12345678910','test5',127,5.5);");*/

        //mydatabase.execSQL("DROP TABLE Product");
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(me.dm7.barcodescanner.zbar.Result result) {

        tvForProName = new TextView(ScanActivity.this);
        tvForProPrice = new TextView(ScanActivity.this);
        product temp = new product();
        // Do something with the result here
        Log.v("kkkk", result.getContents()); // Prints scan results
        Log.v("uuuu", result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
        String Coderesult = result.getContents();

        total = Double.parseDouble(firstPage.total.getText().toString());
        totaldiscount = Double.parseDouble(firstPage.discount.getText().toString());
        totalTax = Double.parseDouble(firstPage.tax.getText().toString());



        Bundle test = new Bundle();
        test.putString("ID", Coderesult);
        temp.idFinder(Coderesult);
        if (temp.idFinder(Coderesult)) {
            String g=temp.pId+"      "+temp.pPrice;
            Toast.makeText(this, g, Toast.LENGTH_LONG).show();
            tvForProName.setText(g);
            firstPage.ll.addView(tvForProName);
            ProductList.add(temp);
            taxCalculator(temp);
        }

        onBackPressed();

    }


    public void taxCalculator(product a){
        total+=((a.TAX+1)*a.pPrice);
        totaldiscount+=((a.pPrice)*a.discount);
        totalTax+=(a.pPrice*a.TAX);
        firstPage.tax.setText(String.valueOf(totalTax));
        firstPage.total.setText(String.valueOf(total));
        firstPage.discount.setText(String.valueOf(totaldiscount));
    }
    public class product {
            int k;
            int ksk;
        String pId;
        String pName;
        double pPrice;
        double pWeight;
        double discount;
        double TAX;


        product() {
            this.pId = "";
            this.pName = "";
            this.pPrice = 0.0;
            this.pWeight = 0.0;
            this.discount=0.0;
            this.TAX=0.13;

        }

        public void productSetter(String id, String name, double price, double weight) {

            this.pId = id;
            this.pName = name;
            this.pPrice = price;
            this.pWeight = weight;
        }


        public boolean idFinder(String id) {
            boolean flag = false;

            if (id != "") {
                final SQLiteDatabase mydatabase = openOrCreateDatabase("Lyf", MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Product(Product_ID VARCHAR,Product_Name VARCHAR, Product_Price REAL, Product_Weight REAL);");
                String q = "SELECT * FROM Product where " + id + "=Product_ID";

                try {
                    Cursor rs = mydatabase.rawQuery(q, null);
                    while (rs.moveToNext()) {
                        //System.out.println("While cheking movetonext>" + rs.getString(0) + ">>>>");
                        this.productSetter(rs.getString(0), rs.getString(1), Double.parseDouble(rs.getString(2)), Double.parseDouble(rs.getString(3)));
                        flag = true;
                    }

                    rs.close();
                } catch (Exception e) {


                    flag = false;
                }
                mydatabase.close();
            }
            return flag;
        }
    }
}


/***
 * TODO: Add fragments top maintain landscap and portrait mode When the user will change the layout the sscreeen will be
 * devided in to the two screen one part for the scrolbar and another part with scan icon and price, tax and other stuff
 *
 * 2. Online database
 *
 * 3.
 */
