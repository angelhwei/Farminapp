package com.neverleave0916.farmin.ui.order;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.neverleave0916.farmin.MainActivity.db;
import static com.neverleave0916.farmin.MainActivity.db_name;
import static com.neverleave0916.farmin.MainActivity.addCartData;
import com.neverleave0916.farmin.PurAdapter;
import com.neverleave0916.farmin.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class pAdapter extends RecyclerView.Adapter<pAdapter.MyViewHolder> {

    //RequestOptions options ;
    private Context mContext ;
    private List<productview> mData ;
    //RequestOptions option;
    public static String price,n,name;
    public static int pr,co;

    public static boolean productselected=false;


    public pAdapter(Context mContext, List lst){
        this.mContext = mContext;
        this.mData = lst;
    }



    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.display_item,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pname,count,pprice;
        ImageView imageView;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.textView1);
            pprice = itemView.findViewById(R.id.textView4);
            imageView=itemView.findViewById(R.id.imageView);
            count=itemView.findViewById(R.id.count);
            view_container = itemView.findViewById(R.id.container);
        }
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final productview pdlist = mData.get(position);
        holder.pname.setText(pdlist.getProduct_name());
        holder.pprice.setText(String.valueOf(pdlist.getProduct_unit_price()).split("\\.")[0]);
        Picasso.get().load(pdlist.getProduct_img()).into(holder.imageView);

        holder.view_container.setOnClickListener(v -> {
            Intent i = new Intent(mContext, PurAdapter.class);
            productselected = true;

            holder.count.setVisibility(View.VISIBLE);
            n = holder.count.getText().toString();
            holder.count.setText(String.valueOf(Integer.parseInt(n) + 1));
            n = holder.count.getText().toString();
            name = holder.pname.getText().toString();
            price = holder.pprice.getText().toString();
            pr=Integer.parseInt(price);
            co=Integer.parseInt(n);

            //點選的東西寫進資料庫
            addCartData(pdlist.getProduct_id(),pdlist.getProduct_unit(), co, pdlist.getProduct_unit_price(), pdlist.getProduct_name());

        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
