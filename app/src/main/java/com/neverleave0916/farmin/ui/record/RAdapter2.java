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

public class RAdapter2 extends RecyclerView.Adapter<RAdapter2.MyViewHolder> {

    //RequestOptions options ;
    private Context mContext ;
    private List<recordview2> mData ;
    //RequestOptions option;

    public RAdapter2(Context mContext, List lst){
        this.mContext = mContext;
        this.mData = lst;
        //options = new RequestOptions().centerCrop().placeholder(R.drawable.home).error(R.drawable.home);
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.display_item3_1,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bdate,bname,bnum,bunitprice,bdiscount,bprice;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            bdate = itemView.findViewById(R.id.bdatetextview);
//            bname = itemView.findViewById(R.id.bnametextView);
//            bnum = itemView.findViewById(R.id.bnumbertextView);
//            bunitprice = itemView.findViewById(R.id.bunitpricetextView);
//            bdiscount = itemView.findViewById(R.id.bdiscounttextView);
            bprice = itemView.findViewById(R.id.bpricetextview);
            view_container = itemView.findViewById(R.id.bcontainer);

        }
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final recordview2 relist = mData.get(position);
        holder.bdate.setText(mData.get(position).getTransaction_create_dt());
//        holder.bname.setText(mData.get(position).getTransaction_product_name());
//        holder.bnum.setText(mData.get(position).getTransaction_participate_product_amount());
//        holder.bunitprice.setText(mData.get(position).getTransaction_product_unit_price());
//        holder.bdiscount.setText(mData.get(position).getTransaction_discount());
        holder.bprice.setText(mData.get(position).getTransaction_total());

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
