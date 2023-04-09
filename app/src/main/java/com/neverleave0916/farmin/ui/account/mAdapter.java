package com.neverleave0916.farmin.ui.account;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neverleave0916.farmin.R;

import java.util.List;

public class mAdapter extends RecyclerView.Adapter<mAdapter.MyViewHolder> {

    //RequestOptions options ;
    private Context mContext ;
    private List<memberview> mData ;
    //RequestOptions option;

    public mAdapter(Context mContext, List lst){
        this.mContext = mContext;
        this.mData = lst;
        //options = new RequestOptions().centerCrop().placeholder(R.drawable.home).error(R.drawable.home);
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.fragment_account,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mname,mphone,mbirth,mmail,maddress,mbalance;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);
            mname = itemView.findViewById(R.id.PerNameEdit);
            mphone = itemView.findViewById(R.id.PerPhoneEdit);
            mbirth = itemView.findViewById(R.id.PerBirthEdit);
            mmail = itemView.findViewById(R.id.PerMailEdit);
            maddress = itemView.findViewById(R.id.PerAddressEdit);
            mbalance = itemView.findViewById(R.id.balance);
            view_container = itemView.findViewById(R.id.container);

        }
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final memberview pdlist = mData.get(position);
        holder.mname.setText(mData.get(position).getMember_name());
        holder.mphone.setText(mData.get(position).getMember_phone());
        holder.mbirth.setText(mData.get(position).getMember_birthday());
        holder.mmail.setText(mData.get(position).getMember_email());
        holder.maddress.setText(mData.get(position).getMember_address());
        holder.mbalance.setText(mData.get(position).getMember_balance());

        // load image from the internet using Glide
        //Glide.with(mContext).load(mData.get(position).getImage_url()).apply(options).into(holder.AnimeThumbnail);


                Intent i = new Intent(mContext, AccountFragment.class);
                i.putExtra("member_id",pdlist.getMember_id());
                i.putExtra("member_card_id",pdlist.getMember_card_id());
                i.putExtra("member_name",pdlist.getMember_name());
                i.putExtra("member_gender",pdlist.getMember_gender());
                i.putExtra("member_birthday",pdlist.getMember_birthday());
                i.putExtra("member_phone",pdlist.getMember_phone());
                i.putExtra("member_email",pdlist.getMember_email());
                i.putExtra("member_address",pdlist.getMember_address());
                i.putExtra("member_balance",pdlist.getMember_balance());


                mContext.startActivity(i);


            }




    @Override
    public int getItemCount() {
        return mData.size();
    }


}
