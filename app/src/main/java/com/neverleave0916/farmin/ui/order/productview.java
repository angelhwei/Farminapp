package com.neverleave0916.farmin.ui.order;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class productview {

    private String product_id ;
    private String product_category_id;
    private String published_status_id;
    private String product_name;
    private String product_unit;
    private String product_unit_price;
    private String product_inventory;
    private String product_desc;
    private String product_img;
    public productview(){

    }


    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public float getProduct_unit_price() {
        return Float.parseFloat(product_unit_price);
    }
    public String getProduct_category_id() {
        return product_category_id;
    }

    public String getPublished_status_id() {
        return published_status_id;
    }

    public String getProduct_inventory() {
        return product_inventory;
    }

    public String getProduct_desc() {
        return product_desc;
    }
    public String getProduct_img(){return this.product_img;}

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_category_id(String product_category_id) {
        this.product_category_id = product_category_id;
    }

    public void setPublished_status_id(String published_status_id) {
        this.published_status_id = published_status_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public void setProduct_unit_price(String product_unit_price) {
        this.product_unit_price = product_unit_price;
    }

    public void setProduct_inventory(String product_inventory) {
        this.product_inventory = product_inventory;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public void setProduct_img(String b) {
        this.product_img=b;
        //this.product_img=Arrays.copyOf(b, b.length);
//        product_img = new byte[b.length];
//        for(int i = 0; i < b.length; i++) {
//            product_img[i] = b[i];
//        }
        //this.product_img = b;
    }




}
