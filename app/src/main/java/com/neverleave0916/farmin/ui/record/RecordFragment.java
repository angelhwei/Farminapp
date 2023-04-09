package com.neverleave0916.farmin.ui.record;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.neverleave0916.farmin.R;
import com.neverleave0916.farmin.logindata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends Fragment {


    private final  String url = "http://neverleave0916.com:34566/api/deposits/member/";
    private final  String url2 = "http://neverleave0916.com:34566/api/transactions/member/";
    private JsonArrayRequest request;
    private JsonArrayRequest request2;
    private RequestQueue requestQueue;
    private RequestQueue requestQueue2;
    private List<recordview> lstrecord ;
    private List<recordview2> lstrecord2 ;
    private static final String TAG = "RecordFragment";
    private DatePickerDialog.OnDateSetListener dateSetListener;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    private TextView dateshow;
    private TextView dateshow2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_record, container, false);

        recyclerView = v.findViewById(R.id.depositrecyView);
        recyclerView2 = v.findViewById(R.id.purchaserecyView);


        String mid;
        mid = logindata.getId();
        String url_d = url + mid;
        String url_t = url2 + mid;

//        dateselect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal=Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        RecordFragment.this,
//                        android.R.style.Theme_Holo_Dialog_MinWidth,
//                        dateSetListener,
//                        year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
//
//        dateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month=month+1;
//                Log.d(TAG,"onDateSet:mm/dd/yyyy"+month+"/"+dayOfMonth+"/"+year);
//                String date = month+"/"+dayOfMonth+"/"+year;
//                dateshow.setText(date);
//            }
//        };

        lstrecord = new ArrayList<>();
        lstrecord2 = new ArrayList<>();
        parseJSON(url_d);
        parseJSON2(url_t);
        return v;
    }

    private void parseJSON(String url) {
        request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0; i<response.length();i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        recordview record = new recordview();
                        record.setDeposit_dt(jsonObject.getString("deposit_dt").substring(0,10));
                        record.setDeposit_amount(jsonObject.getString("deposit_amount"));
                        lstrecord.add(record);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setuprecyclerview(lstrecord);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);
    }
    private void parseJSON2(String url) {
        request2 = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for(int i=0; i<response.length();i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        recordview2 record2 = new recordview2();
                        record2.setTransaction_create_dt(jsonObject.getString("transaction_create_dt").substring(0,10));
//                        record2.setTransaction_discount(jsonObject.getString("transaction_discount"));
                        record2.setTransaction_total(jsonObject.getString("transaction_total"));
                        //for(int j=0; j<response.length();j++) {
                        //record2.setTransaction_product_name(jsonObject.getString("products"));
//                        record2.setTransaction_participate_product_amount(jsonObject.getString("products.transaction_participate_product.transaction_participate_product_amount"));
//                        record2.setTransaction_product_unit_price(jsonObject.getString("products.transaction_participate_product.transaction_participate_product_unit_price"));
                        //}

                        lstrecord2.add(record2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecyclerview2(lstrecord2);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue2 = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue2.add(request2);
    }


    private void setuprecyclerview(List<recordview> lstrecord) {

        RAdapter myadapter = new RAdapter(getContext(),lstrecord);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView.setAdapter(myadapter);
    }
    private void setuprecyclerview2(List<recordview2> lstrecord2) {

        RAdapter2 myadapter2 = new RAdapter2(getContext(),lstrecord2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView2.setAdapter(myadapter2);
    }
}