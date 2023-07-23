package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView mTxtDK;
    EditText mEdtEmail;
    EditText mEdtPassword;
    Button mBtnDN;
    KhachHangDatabase khdb ;

    public  static final  String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";
    public static SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTxtDK = (TextView) findViewById(R.id.txtDK);
        mEdtEmail = (EditText) findViewById(R.id.inputMail);
        mEdtPassword = (EditText) findViewById(R.id.edtMK);
        mBtnDN = (Button) findViewById(R.id.btnDK);

        khdb=new KhachHangDatabase(LoginActivity.this);
        mTxtDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        mBtnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEdtEmail.getText().toString();
                String password = mEdtPassword.getText().toString();



                if(khdb.Login(email, password)){
                    CheckCrededentials();
                    LaythongtinUser();
                    sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(KEY_EMAIL,email);
                    editor.apply();
                    Intent i = new Intent(LoginActivity.this, TrangChuActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
    private void CheckCrededentials(){
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();

        if(email.isEmpty() || !email.contains("@")) {
            showError(mEdtEmail, "Your email is not valid!");
        }

        if(password.isEmpty() || password.length() < 8)
            showError(mEdtPassword, "Your password should have more than 8 letters!");
    }

@SuppressLint("Range")
public void LaythongtinUser(){
    String email = mEdtEmail.getText().toString();
    String password = mEdtPassword.getText().toString();
    Cursor cursor = khdb.GetUserLogin(email, password);
    String username = null;
    String ten = null;
    String sdt = null;
    while(cursor == null) {
        username = cursor.getString(cursor.getColumnIndex(DBHelper.COT_USERNAME));
        ten = cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN));
        sdt = cursor.getString(cursor.getColumnIndex(DBHelper.COT_SDT));
    }

}
    private void showError(EditText mEdt, String s){
        mEdt.setError(s);
    }
//    public void getUsername(){
////        Bundle bundle =
//    }

}