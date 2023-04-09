package com.neverleave0916.farmin;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


public class barcode extends AppCompatActivity {

    Button BarBack;
    ImageView BarCodeView;
    TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        setAppScreenBrightness(200);



        BarBack=findViewById(R.id.BarBack);
        BarCodeView=findViewById(R.id.BarCodeView);
        balance=findViewById(R.id.textView25);

        balance.setText(logindata.getBalance().toString());

        BarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barcode.this.finish();
            }
        });


        String mid =logindata.getId();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(mid, BarcodeFormat.CODE_128, 700, 300);

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            int [] pixels = new int [width * height];
            for (int i = 0 ; i < height ; i++)
            {
                for (int j = 0 ; j < width ; j++)

                {
                    if (bitMatrix.get(j, i))
                    {
                        pixels[i * width + j] = 0xff000000;
                    }
                    else
                    {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

            BarCodeView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

    private void setAppScreenBrightness(int birghtessValue) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = birghtessValue / 255.0f;
        window.setAttributes(lp);
    }

}