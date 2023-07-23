package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThongTinCaNhanActivity extends AppCompatActivity {
    EditText mUserName, mSDT, mEmail, edten;

    KhachHangDatabase khachHangDatabase;
    DBHelper dbHelper;

    Button btnSua;

    public static final String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME = "username";

    public static final String KEY_NUMPHONE = "phonenum";

    public static final String KEY_REALNAME = "realnum";
    public static SharedPreferences sharedPreferences;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        dbHelper = new DBHelper(ThongTinCaNhanActivity.this);
        sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        khachHangDatabase = new KhachHangDatabase(ThongTinCaNhanActivity.this);
        KhachHang khachHang = khachHangDatabase.GetUserData(email);


        btnSua = findViewById(R.id.btnsuatt);
        mUserName = (EditText) findViewById(R.id.edtusername);
        mSDT = (EditText) findViewById(R.id.edtSDT);
        edten = findViewById(R.id.edthovaten);
        mEmail = (EditText) findViewById(R.id.edtEmail);

        mUserName.setText(khachHang.get_username());
        mSDT.setText(khachHang.get_sdt());
        edten.setText(khachHang.get_ten());
        mEmail.setText(khachHang.get_email());

        mEmail.setEnabled(false);



        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edten.getText().toString();
                String sdt = mSDT.getText().toString();
                String email = mEmail.getText().toString();
                String username = mUserName.getText().toString();
                Intent intent = new Intent(ThongTinCaNhanActivity.this, AccountActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("edttentk", username);
                intent.putExtras(bundle);
                startActivity(intent);
                dbHelper.capnhatdulieu(name, sdt, email, username);
            }
        });


    }
}