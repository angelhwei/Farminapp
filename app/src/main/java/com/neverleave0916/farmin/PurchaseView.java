package com.neverleave0916.farmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.neverleave0916.farmin.MainActivity.db_name;
import static com.neverleave0916.farmin.MainActivity.tb_name_cart;
import static com.neverleave0916.farmin.MainActivity.tb_name_member;

public class PurchaseView extends AppCompatActivity {

    Button PurConfirm;
    Button PurBack;
    TextView paidway;
    RecyclerView recyclerView;

    private int mYear, mMonth, mDay;
    String TID = "";
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);

    String payway;
    String payDT;

    private static float memberBalance;
    private static String member_id;

    private int amounttry;
    private int total;
    JSONArray SQLproductArray = new JSONArray();
    public static ArrayList<purview> pdArray=new ArrayList<>();

    public static SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_view);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);           //開啟資料庫
        Cursor c = db.rawQuery("SELECT * FROM "+tb_name_cart, null);    //開啟購物車資料表
        /* 逐筆讀出產品資料，同時寫入purview*/
        if (c.moveToFirst()) {  //移到第一筆資料
            pdArray.clear();
            do {                //逐筆讀出資料
                try {
                    JSONObject SQLproduct = new JSONObject();
                    SQLproduct.put("product_id", c.getString(c.getColumnIndex("product_id")));
                    SQLproduct.put("unit", "包");
                    SQLproduct.put("amount", c.getString(c.getColumnIndex("amount")));
                    SQLproduct.put("unit_price", 40);
                    SQLproductArray.put(SQLproduct); //產品object放進產品object
                    purview purview = new purview(c.getString(c.getColumnIndex("product_name")), c.getFloat(c.getColumnIndex("unit_price")), c.getInt(c.getColumnIndex("amount")));
                    pdArray.add(purview);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } while (c.moveToNext());  //有下一筆就繼續
        }
        c.close();


        /* 取得訂單數量 */
        c = db.rawQuery("SELECT SUM(amount) FROM " + tb_name_cart, null);
        amounttry = 0;
        if(c.moveToFirst())
            amounttry = c.getInt(0);
        c.close();
        /* 取得總價(待改) */
        if (amounttry < 3){
            total = amounttry * 40;
        }
        else{
            total = amounttry / 3 * 100;
            total += amounttry % 3 * 40;
        }

        TextView cartprice1 = findViewById(R.id.cartprice);
        cartprice1.setText(Integer.toString(total));

        final TextView dateText = (TextView) findViewById(R.id.pickdate);
        Calendar mCal = Calendar.getInstance();
        CharSequence s = DateFormat.format("yyyy-MM-dd", mCal.getTime());

        payDT = s.toString();

        dateText.setText(s);
        Button dateButton = (Button) findViewById(R.id.datebtn);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(PurchaseView.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String format = setDateFormat(year, month, day);
                        dateText.setText(format);
                    }

                }, mYear, mMonth, mDay).show();
            }

        });

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.planets_array,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(1, false);
        spinner.setOnItemSelectedListener(spnOnItemSelected);
        String sInfo = spinner.getItemAtPosition(1).toString();
        payway = sInfo;
        paidway = (TextView) findViewById(R.id.paidway);
        paidway.setText(spinner.getSelectedItem().toString());

        int index = spinner.getSelectedItemPosition();

        PurBack = findViewById(R.id.CartBack);
        PurConfirm = findViewById(R.id.CartConfirm);


        new Thread(GetMax).start();
        /* 送出訂單 */
        PurConfirm.setOnClickListener(v -> {
            /* 取得會員ID與餘額 */
            Cursor c1 = db.rawQuery("SELECT member_id,member_balance FROM " + tb_name_member, null);
            if(c1.moveToFirst()){
                member_id = c1.getString(c1.getColumnIndex("member_id"));
                memberBalance = c1.getFloat(c1.getColumnIndex("member_balance"));
            }
            c1.close();
            /* 進行結帳 */
            if (payway.equals("現場付款")) {
                new Thread(runnable).start();
            } else {
                if (total < memberBalance) {
                    new Thread(runnable).start();
                } else {
                    System.out.println(total + memberBalance + "no money");
                }
            }

            /* 清空購物車 */
            db.execSQL("DELETE FROM " + tb_name_cart);
            /* 跳回主頁 */
            Intent intent = new Intent();
            intent.setClass(PurchaseView.this, MainActivity.class);
            startActivity(intent);
        });

        PurBack.setOnClickListener(v -> {
            PurchaseView.this.finish();
            db.execSQL("DELETE FROM " + tb_name_cart);

        });
        recyclerView = findViewById(R.id.cartrecycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PurAdapter recyclerAdapter = new PurAdapter(getApplicationContext(), pdArray);
        recyclerView.setAdapter(recyclerAdapter);

    }

/////////////////////////////////////////////////////////////////////////

    private AdapterView.OnItemSelectedListener spnOnItemSelected
            = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            String sPos = String.valueOf(pos);
            String sInfo = parent.getItemAtPosition(pos).toString();
            //String sInfo=parent.getSelectedItem().toString();
            payway = sInfo;
            paidway.setText(sInfo);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            //
        }
    };

    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
        }
    };

    Runnable GetMax = new Runnable() {
        private final static String TAG = "HTTPURLCONNECTION test";

        @Override
        public void run() {
            String result = "";
            String.valueOf(year);
            String.valueOf(day);
            String formatTest = "%02d";
            TID = "TX" + String.valueOf(year) + String.valueOf(month + 1) + String.format(formatTest, day);

            try {
                URL url = new URL("http://neverleave0916.com:34566/api/transactions/max");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                int status = httpURLConnection.getResponseCode();
                Log.d(TAG, String.valueOf(status));
                if (inputStream != null) {
                    InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader in = new BufferedReader(reader);

                    String line = "";
                    while ((line = in.readLine()) != null) {
                        result += (line + "\n");
                    }
                    TID = CheckDate(result, TID);
                } else {
                    System.out.println("Did not work 119!");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String CheckDate(String result, String TID) {
            String[] array = splitToNChar(result, 10);
            if (TID.equals(array[0])) {
                int number = Integer.parseInt(array[1].trim());
                System.out.println(number);
                String formatTest = "%05d";
                TID = array[0] + String.format(formatTest, number + 1);
                return TID;
            } else {
                TID = TID + "00001";
                return TID;
            }
        }

    };

    private static String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }

    Runnable runnable = new Runnable() {
        private final static String TAG = "HTTPURLCONNECTION test";
        JSONObject SQLtransaction = new JSONObject();

        InputStream is;
        String CreateDate = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(day) + " " + String.valueOf(hour) + ":" + String.valueOf(minute);
        List list = new ArrayList();

        @Override
        public void run() {


            /* 宣告待放入的變數*/
            int discount=0;                 //折扣
            String transaction_status_id;   //交易狀態
            String payment_type_id;         //付款方式
            int transaction_paid;           //已付金額

            if (amounttry >= 3) {
                discount = amounttry / 3 * 20;
            }
            amounttry *= 40;                //原價

            if (payway.equals("現場付款")) {
                transaction_status_id = "TSTAT03";  //未結帳
                payment_type_id = "PM01";           //現金
                transaction_paid=0;
            } else {
                transaction_status_id = "TSTAT01";  //已結帳待取貨
                payment_type_id = "PM02";           //儲值金
                transaction_paid = total;
                float xoxo = memberBalance - total;
                memberBalance = xoxo;
                System.out.println(memberBalance);
//                AccountFragment.accountdata6.setText(memberBalance);
            }

            try {
                SQLtransaction.put("transaction_id", TID);
                SQLtransaction.put("member_id", member_id);//s
                SQLtransaction.put("transaction_src_id", "TSRC02");
                SQLtransaction.put("transaction_status_id", transaction_status_id);
                SQLtransaction.put("payment_type_id", payment_type_id);
                SQLtransaction.put("transaction_create_dt", CreateDate);
                SQLtransaction.put("transaction_subtotal", amounttry);
                SQLtransaction.put("transaction_discount", discount);
                SQLtransaction.put("transaction_total", total);
                SQLtransaction.put("transaction_paid", transaction_paid);
                SQLtransaction.put("transaction_pay_dt", payDT);
                SQLtransaction.put("transaction_remark", null);
                SQLtransaction.put("products", SQLproductArray);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(SQLtransaction);
            String result = "";
            try {
                URL url = new URL("http://neverleave0916.com:34566/api/transactions");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                byte[] data = (SQLtransaction.toString()).getBytes();
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                OutputStream out = httpURLConnection.getOutputStream();
                // 写入请求的字符串
                out.write((SQLtransaction.toString()).getBytes());
                out.flush();
                out.close();

                int code = httpURLConnection.getResponseCode();
                System.out.println(code);
                is = httpURLConnection.getInputStream();// 得到網絡返回的正確輸入流
                System.out.println(is);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "请求结果");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
}