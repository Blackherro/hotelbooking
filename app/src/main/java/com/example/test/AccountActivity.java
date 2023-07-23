package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AccountActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView info;
    KhachHangDatabase khachHangDatabase;
    DBHelper dbHelper;

    TextView tvten, tvemail;

    LinearLayout lsdatphong;
    SharedPreferences sharedPreferences;
    LinearLayout layoutAdmin;

    public static final String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        khachHangDatabase = new KhachHangDatabase(AccountActivity.this);
        tvten = findViewById(R.id.tvten);
        tvemail = findViewById(R.id.tvemail);
        bottomNavigationView = findViewById(R.id.bottomnaviacc);
        bottomNavigationView.setSelectedItemId(R.id.acc);
        lsdatphong=findViewById(R.id.lsdatphong);
        layoutAdmin = findViewById(R.id.layoutql);
        sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if(sharedPreferences == null){
            tvten.setText("Guest");
            tvemail.setText("Example@gmail.com");
        }
        else {
            KhachHang khachHang = khachHangDatabase.GetUserData(email);
            tvten.setText(khachHang.get_username());
            tvemail.setText(email);
        }


        if (!tvemail.getText().toString().equals("tranduythanh@gmail.com")) {
            layoutAdmin.setVisibility(View.GONE);
        } else {
            layoutAdmin.setVisibility(View.VISIBLE);
        }

        lsdatphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this,lichsudatphong.class));
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), TrangChuActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.book:
                        startActivity(new Intent(getApplicationContext(), BookActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.acc:
                        return true;
                }
                return false;
            }
        });
        info = (TextView) findViewById(R.id.txtinfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, ThongTinCaNhanActivity.class));
            }
        });
    }

    public void onclick(View view) {
        startActivity(new Intent(AccountActivity.this, LoginActivity.class));

    }


    public void quanlyphong(View view) {
        startActivity(new Intent(AccountActivity.this, DSPhongAdmin.class));
    }


}