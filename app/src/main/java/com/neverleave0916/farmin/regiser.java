package com.neverleave0916.farmin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class regiser extends AppCompatActivity {

    Button RegConfirm;
    Button Regbarcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }

        RegConfirm=findViewById(R.id.RegConfirm);
        Regbarcode=findViewById(R.id.Regbarcode);

        Regbarcode.setOnClickListener(v -> regiser.this.finish());
        RegConfirm.setOnClickListener(v -> {
            Intent intent=new Intent();
            intent.setClass(regiser.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
