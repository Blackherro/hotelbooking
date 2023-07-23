package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class TrangChuActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button btnSupport;
    ImageView hinhquan1,hinhquan3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
//        btnDichvu=findViewById(R.id.btnService);
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.room1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.room2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.room3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        btnSupport = (Button) findViewById(R.id.btnService);
        hinhquan1=findViewById(R.id.quan1);
        hinhquan3=findViewById(R.id.quan3);

        bottomNavigationView = findViewById(R.id.bottomnavi);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.book:
                        startActivity(new Intent(getApplicationContext(),BookActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.acc:
                        startActivity(new Intent(getApplicationContext(),AccountActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }


        });

        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CallSupport.class));
            }
        });
        hinhquan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://goo.gl/maps/d9dJL7rwgArgtJoa6";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        hinhquan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://goo.gl/maps/Utot5t7BmZfkeAwp6";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    public void onclickdsphongdon(View view) {
        startActivity(new Intent(TrangChuActivity.this,DanhSachPhongActivity.class));
    }

    public void onclickdsphongdoi(View view) {
        startActivity(new Intent(TrangChuActivity.this,DanhSachPhongDoi.class));
    }

    public void onclickdsphongbon(View view) {
        startActivity(new Intent(TrangChuActivity.this,DanhSachPhongbon.class));
    }
}