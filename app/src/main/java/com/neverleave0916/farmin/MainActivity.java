package com.neverleave0916.farmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String db_name="FarmIn";
    public static final String tb_name_member="member";
    public static final String tb_name_products="products";
    public static final String tb_name_cart="cart";
    public static final String tb_name_trans="trans";

    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

    }



    @Override
    protected void onResume()
    {
        super.onResume();
        /*開啟或建立資料庫*/
        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);


        /* 產品資料表 */
        String createProductsTable="CREATE TABLE IF NOT EXISTS "+
                tb_name_products+                   //TABLE名稱
                "(product_id CHAR(6) PRIMARY KEY,"+ //產品編號
                "product_name VARCHAR(32),"+        //產品名稱
                "product_unit VARCHAR(5),"+         //產品單位
                "product_unit_price FLOAT(10))";    //產品單價
        /* 購物車資料表 */
        String createCartTable="CREATE TABLE IF NOT EXISTS "+
                tb_name_cart+                       //TABLE名稱
                "(product_id CHAR(6) PRIMARY KEY,"+             //產品編號
                "unit VARCHAR(5),"+         //產品單位
                "amount INT(2)," +
                "unit_price FLOAT(10)," +
                "product_name VARCHAR(32))";        //產品名稱)
        /* 交易資料表 */
        String createTransTable="CREATE TABLE IF NOT EXISTS "+
                tb_name_trans+                       //TABLE名稱
                "(product_name VARCHAR(32))";        //產品名稱)


        db.execSQL(createProductsTable);        //建立產品資料表
        db.execSQL(createCartTable);            //建立購物車資料表
        db.execSQL(createTransTable);           //建立交易資料表

        Cursor c=db.rawQuery("SELECT * FROM "+tb_name_cart,null);

        /*清空所有資料表，正式上線前須刪除*/
        db.execSQL("DELETE FROM "+tb_name_products);
        db.execSQL("DELETE FROM "+tb_name_cart);
        db.execSQL("DELETE FROM "+tb_name_trans);

        if(c.getCount()==0){
            c=db.rawQuery("SELECT * FROM "+tb_name_cart,null);
        }
        else {
            /*應該暫時沒用到未來或許是用來更新購物車*/
            if (c.moveToFirst()) {
                do {
                    //purview member = new purview(c.getString(c.getColumnIndex("123")), c.getInt(2), c.getInt(1));
                    //textArray.add(member);
                } while (c.moveToNext());
            }
        }
        // Activity已經被顯示出來
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        db.close();
    }
    /* 新增/更新購物車資料 */
    public static void addCartData(String product_id, String unit,  int amount, float unit_price, String product_name){
        ContentValues cv=new ContentValues(2);
        cv.put("product_id",product_id);
        cv.put("unit",unit);
        cv.put("amount",amount);
        cv.put("unit_price",unit_price);
        cv.put("product_name",product_name);
        db.replace(tb_name_cart,null,cv);
    }
}