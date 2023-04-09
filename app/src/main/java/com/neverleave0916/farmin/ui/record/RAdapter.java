package com.neverleave0916.farmin.ui.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neverleave0916.farmin.R;

import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.MyViewHolder> {

    //RequestOptions options ;
    private Context mContext ;
    private List<recordview> mData ;
    //RequestOptions option;

    public RAdapter(Context mContext, List lst){
        this.mContext = mContext;
        this.mData = lst;
        //options = new RequestOptions().centerCrop().placeholder(R.drawable.home).error(R.drawable.home);
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.display_item2,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rdate,rprice;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            rdate = itemView.findViewById(R.id.datetextview);
            rprice = itemView.findViewById(R.id.bpricetextview);
            view_container = itemView.findViewById(R.id.rcontainer);

        }
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final recordview relist = mData.get(position);
        holder.rdate.setText(mData.get(position).getDeposit_dt());
        holder.rprice.setText(mData.get(position).getDeposit_amount());

        // load image from the internet using Glide
        //Glide.with(mContext).load(mData.get(position).getImage_url()).apply(options).into(holder.AnimeThumbnail);

//        holder.view_container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mContext, product_content2.class);
//                i.putExtra("product_id",pdlist.getProduct_id());
//                i.putExtra("product_category_id",pdlist.getProduct_category_id());
//                i.putExtra("published_status_id",pdlist.getPublished_status_id());
//                i.putExtra("product_name",pdlist.getProduct_name());
//                i.putExtra("product_unit",pdlist.getProduct_unit());
//                i.putExtra("product_unit_price",pdlist.getProduct_unit_price());
//                i.putExtra("product_inventory",pdlist.getProduct_inventory());
//                i.putExtra("product_desc",pdlist.getProduct_desc());


//                mContext.startActivity(i);


//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
