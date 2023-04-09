package com.neverleave0916.farmin.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neverleave0916.farmin.R;
import com.neverleave0916.farmin.R;;

import java.util.List;

public class bookAdapter extends RecyclerView.Adapter<com.neverleave0916.farmin.ui.home.bookAdapter.MyViewHolder> {

    //RequestOptions options ;
    private Context mContext ;
    private List<bookview> mData ;
    //RequestOptions option;

    public bookAdapter(Context mContext, List lst){
        this.mContext = mContext;
        this.mData = lst;
        //options = new RequestOptions().centerCrop().placeholder(R.drawable.home).error(R.drawable.home);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.display_item6,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tdate.setText(mData.get(position).getTransaction_create_dt());
        //holder.tstatus.setText((mData.get(position).getTransaction_status_id()));
        holder.tsum.setText((mData.get(position).getTransaction_total()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tdate,tstatus,tsum;
        LinearLayout view_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tdate = (itemView).findViewById(R.id.date);
           // tstatus = (itemView).findViewById(R.id.amount);
            tsum = (itemView).findViewById(R.id.total);
            view_container = (itemView).findViewById(R.id.bcontainer);
        }
    }


}
