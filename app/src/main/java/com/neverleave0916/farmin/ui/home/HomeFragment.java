package com.neverleave0916.farmin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.neverleave0916.farmin.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.neverleave0916.farmin.barcode;
import com.neverleave0916.farmin.logindata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    Button HomeBarcode;
    private HomeViewModel homeViewModel;
    RollPagerView mRollViewPager;

    private final  String url = "http://neverleave0916.com:34566/api/transactions/member/";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<bookview> lstbook;
    private List<bookview> lstbook2;
    RecyclerView recyclerView;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        HomeBarcode = v.findViewById(R.id.HomeBarcode);
        HomeBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), barcode.class));
            }
        });

        mRollViewPager = v.findViewById(R.id.roll_view_pager);
        mRollViewPager.setPlayDelay(10000);//設定播放時間間隔

        mRollViewPager.setAnimationDurtion(500);//設定透明度

        mRollViewPager.setAdapter(new TestNormalAdapter());//設定介面卡

        recyclerView = v.findViewById(R.id.recyclerView_book);
        String mid;
        mid = logindata.getId();
        String url_m = url + mid;

        lstbook = new ArrayList<>();
        lstbook2 = new ArrayList<>();
        parseJSON(url_m);

        //設定指示器(順序依次)
//mRollViewPager.setHintView(new IconHintView(this, R.drawable.farma, R.drawable.farmb));//自定義指示器圖片
//        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW,Color.WHITE));//設定圓點指示器顏色
//mRollViewPager.setHintView(new TextHintView(this));//設定文字指示器
//mRollViewPager.setHintView(null);//隱藏指示器

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
                        bookview book = new bookview();
                        book.setTransaction_create_dt(jsonObject.getString("transaction_create_dt").substring(0,10));
//                        book.setTransaction_participate_product_amount(jsonObject.getString("transaction_participate_product_amount"));
                        book.setTransaction_src_id(jsonObject.getString("transaction_src_id"));
                        book.setTransaction_status_id(jsonObject.getString("transaction_status_id"));
                        book.setTransaction_total(jsonObject.getString("transaction_total"));

                        //待處理
                        System.out.println("123");
                        if (book                                       .getTransaction_status_id().equals("TSTAT03")) {
                            lstbook.add(book);
                            System.out.println("OK");
                        }

                        if (book.getTransaction_src_id().equals("TSRC02")) {
                            lstbook2.add(book);
                            System.out.println("OK2");
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setuprecyclerview(lstbook2);

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

    private void setuprecyclerview(List<bookview> lstbook) {

        bookAdapter myadapter = new bookAdapter(getContext(),lstbook);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView.setAdapter(myadapter);
    }
    private static class TestNormalAdapter extends StaticPagerAdapter {
        private final int[] imgs = {
                R.drawable.farmb,
                R.drawable.farmc,
                R.drawable.farmd,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}

