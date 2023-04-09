package com.neverleave0916.farmin.ui.account;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.neverleave0916.farmin.MainActivity;
import com.neverleave0916.farmin.R;

import com.neverleave0916.farmin.barcode;
import com.neverleave0916.farmin.LoginActivity;
import com.neverleave0916.farmin.logindata;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {

    //private final  String url = "http://neverleave0916.com:34566/api/members";
    private AccountViewModel accountViewModel;
//    private JsonArrayRequest request;
//    private RequestQueue requestQueue;
//    private List<memberview> lstmember ;
    public static TextView accountdata1;
    public static TextView accountdata2;
    public static TextView accountdata3;
    public static TextView accountdata4;
    public static TextView accountdata5;
    public static TextView accountdata6;


    Button Regbarcode,PerEditLogout,btn2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View v = inflater.inflate(R.layout.fragment_account, container, false);


        //lstmember = new ArrayList<>();

        accountdata1=v.findViewById(R.id.PerNameEdit);
        accountdata2=v.findViewById(R.id.PerPhoneEdit);
        accountdata3=v.findViewById(R.id.PerBirthEdit);
        accountdata4=v.findViewById(R.id.PerMailEdit);
        accountdata5=v.findViewById(R.id.PerAddressEdit);
        accountdata6=v.findViewById(R.id.balance);

        Regbarcode=v.findViewById(R.id.Regbarcode);
        PerEditLogout=v.findViewById(R.id.PerEditLogout);
        btn2 = v.findViewById(R.id.perEditLogout);

        //logindata.showdata();


        Cursor c=MainActivity.db.rawQuery("SELECT * FROM "+MainActivity.tb_name_member,null);
        if (c.moveToFirst()) {
            do {
                AccountFragment.accountdata1.setText(c.getString(c.getColumnIndex("member_name")));
                AccountFragment.accountdata2.setText(c.getString(c.getColumnIndex("member_phone")));
                AccountFragment.accountdata3.setText(c.getString(c.getColumnIndex("member_birthday")));
                AccountFragment.accountdata4.setText(c.getString(c.getColumnIndex("member_email")));
                AccountFragment.accountdata5.setText(c.getString(c.getColumnIndex("member_address")));
                AccountFragment.accountdata6.setText(String. valueOf(c.getFloat(c.getColumnIndex("member_balance"))));
            } while (c.moveToNext());
        }


        
        Regbarcode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), barcode.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                accountdata1.clearFocus();
                accountdata1.setEnabled(true);
                accountdata1.setFocusable(true);

                accountdata1.setFocusableInTouchMode(true);
                accountdata1.requestFocus();
                //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                InputMethodManager keyboard=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(accountdata1,0);
            }
        });

        /* final TextView textView = root.findViewById(R.id.text_account);
        accountViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        PerEditLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return v;
    }
}
