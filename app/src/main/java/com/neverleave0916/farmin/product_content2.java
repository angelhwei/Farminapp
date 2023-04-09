package com.neverleave0916.farmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class product_content2 extends AppCompatActivity {

    Button Content_btn3;
    Button Content_btn2;
    Button ContentBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_content);

        Content_btn3=findViewById(R.id.Content_btn3);
        Content_btn2=findViewById(R.id.Content_btn2);
        ContentBack=findViewById(R.id.ContentBack);

        ContentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_content2.this.finish();
            }
        });

        Content_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(product_content2.this, PurchaseView.class);
                startActivity(intent);
            }
        });

        String id = getIntent().getExtras().getString("product_id");
        String category = getIntent().getExtras().getString("product_category_id");
        String status = getIntent().getExtras().getString("published_status_id");
        String name = getIntent().getExtras().getString("product_name");
        String unit = getIntent().getExtras().getString("product_unit");
        String price = getIntent().getExtras().getString("product_unit_price");
        String inventory = getIntent().getExtras().getString("product_inventory");
        String describe = getIntent().getExtras().getString("product_desc");

        TextView text_name = findViewById(R.id.textView3);
        TextView text_price = findViewById(R.id.textView4);
        TextView text_desc = findViewById(R.id.description);

        text_name.setText(name);
        text_price.setText(price);
        text_desc.setText(describe);



    }
}
