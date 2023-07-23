package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CallSupport extends AppCompatActivity {
ImageView call1, call2, call3;
TextView sdt1, sdt2, sdt3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_support);

        call1 = findViewById(R.id.call1);
        call2 = findViewById(R.id.call2);
        call3 = findViewById(R.id.call3);

        sdt1 = findViewById(R.id.sdt1);
        sdt2 = findViewById(R.id.sdt2);
        sdt3 = findViewById(R.id.sdt3);
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callPhone = new Intent(Intent.ACTION_DIAL);
                callPhone.setData(Uri.parse("tel:" + sdt1.getText().toString()));
                startActivity(callPhone);
            }
        });
        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callPhone = new Intent(Intent.ACTION_DIAL);
                callPhone.setData(Uri.parse("tel:" + sdt2.getText().toString()));
                startActivity(callPhone);
            }
        });
        call3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callPhone = new Intent(Intent.ACTION_DIAL);
                callPhone.setData(Uri.parse("tel:" + sdt3.getText().toString()));
                startActivity(callPhone);
            }
        });
    }

}