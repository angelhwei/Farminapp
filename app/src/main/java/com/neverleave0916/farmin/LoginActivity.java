package com.neverleave0916.farmin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.neverleave0916.farmin.ui.account.memberview;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.neverleave0916.farmin.MainActivity.db_name;
import static com.neverleave0916.farmin.MainActivity.tb_name_member;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private List<memberview> lstmember ;

    Button register_btn;
    Button login;
    EditText username;
    EditText password;
    String url = "http://neverleave0916.com:34566/api/members/";

    public static SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login);
        register_btn=findViewById(R.id.register_btn);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        lstmember = new ArrayList<>();

        login.setOnClickListener(v -> {
            String account=username.getText().toString().trim();
            String psw=password.getText().toString().trim();

            if(TextUtils.isEmpty(account) || TextUtils.isEmpty(psw)){
                username.setError("請輸入帳號!");
                password.setError("請輸入密碼!");
                return;
            }
            else{
                checkLogin(account, psw);
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        /*開啟或建立資料庫*/
        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        /* 會員資料表*/
        String createMemberTable = "CREATE TABLE IF NOT EXISTS " +
                tb_name_member +
                //"(_id INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "(member_id CHAR(6) PRIMARY KEY," +
                "member_card_id VARCHAR(10)," +
                "member_name VARCHAR(10)," +
                "member_gender INT(1)," +
                "member_birthday VARCHAR(32)," +
                "member_phone CHAR(15)," +
                "member_email CHAR(100)," +
                "member_address VARCHAR(100)," +
                "member_balance FLOAT(2))";
        db.execSQL(createMemberTable);          //建立會員資料表
        /*清空所有資料表，正式上線前須刪除*/
        db.execSQL("DELETE FROM "+tb_name_member);

        try (Cursor c = db.rawQuery("SELECT * FROM "+tb_name_member, null)){
            if (c.moveToFirst()) {  //移到第一筆資料
                String account = c.getString(c.getColumnIndex("member_id"));
                String pwd = c.getString(c.getColumnIndex("member_phone"));
                checkLogin(account, pwd);
            }
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        db.close();
        // 另一個Activity已經得到焦點，而該Activity將要被暫停
        // Another activity is taking focus (this activity is about to be "paused").
    }



    private void checkLogin(String account, String pwd) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+account, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                        try {
//                            memberview member = new memberview();
//                            member.setMember_id(response.getString("member_id"));
//                            member.setMember_card_id(response.getString("member_card_id"));
//                            member.setMember_name(response.getString("member_name"));
//                            member.setMember_gender(response.getString("member_gender"));
//                            member.setMember_birthday(response.getString("member_birthday"));
//                            member.setMember_phone(response.getString("member_phone"));
//                            member.setMember_email(response.getString("member_email"));
//                            member.setMember_address(response.getString("member_address"));
//                            member.setMember_balance(response.getString("member_balance"));
//                            lstmember.add(member);

                            if(response.length() != 0) {
                                if (account.equals(response.getString("member_id")) && pwd.equals(response.getString("member_phone"))) {
                                    Login(account, pwd, response);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();

                }
            });

            requestQueue = Volley.newRequestQueue(this.getApplicationContext());
            requestQueue.add(request);
    }

    private void Login(String account, String pwd, JSONObject response) throws JSONException {
//        new logindata(lstmember.get(0).getMember_id(), lstmember.get(0).getMember_card_id(),
//                        lstmember.get(0).getMember_name(), lstmember.get(0).getMember_gender(),
//                        lstmember.get(0).getMember_birthday(), lstmember.get(0).getMember_phone(),
//                        lstmember.get(0).getMember_email(), lstmember.get(0).getMember_address(),
//                        lstmember.get(0).getMember_balance());

        //addMemberData(lstmember.get(0).getMember_id(), lstmember.get(0).getMember_card_id(), lstmember.get(0).getMember_name(), Integer.valueOf(lstmember.get(0).getMember_gender()),
        //        lstmember.get(0).getMember_birthday(), lstmember.get(0).getMember_phone(), lstmember.get(0).getMember_email(), lstmember.get(0).getMember_address(), Float.parseFloat(lstmember.get(0).getMember_balance()));

        new logindata(response.getString("member_id"), response.getString("member_card_id"),
                response.getString("member_name"), response.getInt("member_gender"),
                response.getString("member_birthday"), response.getString("member_phone"),
                response.getString("member_email"), response.getString("member_address"),
                Float.parseFloat(response.getString("member_balance")));
        try{
            addMemberData(response.getString("member_id"), response.getString("member_card_id"),
                    response.getString("member_name"), response.getInt("member_gender"),
                    response.getString("member_birthday"), response.getString("member_phone"),
                    response.getString("member_email"), response.getString("member_address"),
                    Float.parseFloat(response.getString("member_balance")));
        } catch (Exception e) {

        }
        showTextToast("User " + "has logged in!");

        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }



    private Toast toast = null;
    @SuppressLint("ShowToast")
    private void  showTextToast(String msg) {
        if (toast != null) {
            toast.cancel(); //取消
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT); //重新新建并显示吐司
        } else {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        toast.show();
        //source：https://zhidao.baidu.com/question/1895427629509315300.html
    }
    /* 新增/更新會員資料 */
    private void addMemberData(String member_id, String member_card_id, String member_name,  int member_gender,
                                     String member_birthday, String member_phone, String member_email, String member_address, float member_balance ){
        ContentValues cv=new ContentValues(9);
        cv.put("member_id",member_id);
        cv.put("member_card_id",member_card_id);
        cv.put("member_name",member_name);
        cv.put("member_gender",member_gender);
        cv.put("member_birthday",member_birthday);
        cv.put("member_phone",member_phone);
        cv.put("member_email",member_email);
        cv.put("member_address",member_address);
        cv.put("member_balance",member_balance);

        db.replace(tb_name_member,null,cv);
    }
}