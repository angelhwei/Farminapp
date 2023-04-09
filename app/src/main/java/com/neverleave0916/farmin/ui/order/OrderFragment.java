package com.neverleave0916.farmin.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.neverleave0916.farmin.PurchaseView;
import com.neverleave0916.farmin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private final  String url = "http://neverleave0916.com:34566/api/products?inventory=5";
    private JsonArrayRequest request;
    //private JsonArrayRequest requestimg;
    private RequestQueue requestQueue;
    private List<productview> lstproduct ;
    RecyclerView recyclerView;
    Button orderconfirm;
    private Toast toast = null;

    public static String tmp;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //lstpview = new ArrayList<JSONObject>();
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        orderconfirm=view.findViewById(R.id.orderconfirm);
        recyclerView = view.findViewById(R.id.recyclerView);
        lstproduct = new ArrayList<>();
        parseJSON();

        /* 確認訂單 */
        orderconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pAdapter.productselected) {
                    startActivity(new Intent(getActivity(), PurchaseView.class));
                }
                else {
                    showTextToast("請選取商品!");
                }
            }
        });

        return view;
    }

    private void parseJSON(){

        request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject;

                for(int i=0; i<response.length();i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        productview product = new productview();
                        product.setProduct_id(jsonObject.getString("product_id"));
                        product.setProduct_category_id(jsonObject.getString("product_category_id"));
                        product.setPublished_status_id(jsonObject.getString("published_status_id"));
                        product.setProduct_name(jsonObject.getString("product_name"));
                        product.setProduct_unit(jsonObject.getString("product_unit"));
                        product.setProduct_unit_price(jsonObject.getString("product_unit_price"));
                        product.setProduct_inventory(jsonObject.getString("product_inventory"));
                        product.setProduct_desc(jsonObject.getString("product_desc"));
                        product.setProduct_img(jsonObject.getString("product_image"));
                        lstproduct.add(product);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setuprecyclerview(lstproduct);

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

    private void setuprecyclerview(List<productview> lstproduct) {

        pAdapter myadapter = new pAdapter(getContext(),lstproduct);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(myadapter);
    }

    private void  showTextToast(String msg) {

        toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT); //重新新建并显示吐司
        toast.show();
        //source：https://zhidao.baidu.com/question/1895427629509315300.html
    }


}
