package com.neverleave0916.farmin;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PurAdapter extends RecyclerView.Adapter<com.neverleave0916.farmin.PurAdapter.MyViewHolder> {

    //RequestOptions options ;
    private Context mContext ;
    private List<purview> mData ;
    public static String Productname;
    public static int Productprice;
    public static int Productcount;
    //RequestOptions option;

    public PurAdapter(Context mContext, List lst){
        this.mContext = mContext;
        this.mData = lst;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.display_item5,parent,false);
        // click listener here

        return new MyViewHolder(view);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Productname,Productprice,Productcount;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            //ds5//結帳頁面產品資訊
            Productname = itemView.findViewById(R.id.bnametextview);
            Productprice = itemView.findViewById(R.id.bpricetextview);
            Productcount = itemView.findViewById(R.id.bcount);
            view_container = itemView.findViewById(R.id.ccontainer);

        }
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        purview purview = mData.get(position);
        holder.Productname.setText(purview.getBuyname());
        Integer tmp = purview.getBuycount();
        holder.Productcount.setText(tmp.toString());
        Float D=purview.getBuyprice();
        holder.Productprice.setText(D.toString());
        Cursor cur=PurchaseView.db.rawQuery("SELECT * FROM "+MainActivity.tb_name_cart,null);

        if(cur.moveToFirst()) {
            cur.moveToPosition(position);
            Productname = "416";
            //Productname=cur.getString(cur.getColumnIndex("name"));
            Productprice = cur.getInt(cur.getColumnIndex("amount"));
            Productcount = cur.getInt(cur.getColumnIndex("amount"));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



}
