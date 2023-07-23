package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    EditText mEdtUsername, mEdtConfirmPass,mEdtEmail,mEdtPassword, mEdtTen, mEdtSDT;
    Button mBtnDK;
    DBHelper helper;
    ArrayList<KhachHang> khachHangs = new ArrayList<KhachHang>();
    public static KhachHangDatabase khdb;
    public  static final  String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME="username";

    public static final String KEY_NUMPHONE= "phonenum";

    public static final String KEY_REALNAME="realnum";
    public static SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEdtTen = findViewById(R.id.edtTenR);
        khdb = new KhachHangDatabase(RegisterActivity.this);
        mEdtSDT = findViewById(R.id.edtSDThome);
        mEdtUsername = (EditText) findViewById(R.id.edtUsername);
        mEdtEmail = (EditText) findViewById(R.id.inputMail);
        helper=new DBHelper(RegisterActivity.this);
        mEdtPassword = (EditText) findViewById(R.id.edtMK);
        mEdtConfirmPass = (EditText) findViewById(R.id.edtPassword);
        mBtnDK = (Button) findViewById(R.id.btnDK);

        sharedPreferences = getSharedPreferences(SHARE_NAME,MODE_PRIVATE);


        mBtnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!CheckCededentials()) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_USERNAME,mEdtUsername.getText().toString());
                    editor.putString(KEY_EMAIL, mEdtEmail.getText().toString());
                    editor.putString((KEY_NUMPHONE), String.valueOf(Integer.parseInt(mEdtSDT.getText().toString())));
                    editor.putString(KEY_REALNAME, mEdtTen.getText().toString());
                    editor.apply();
                    them(view);
                    startActivity(new Intent(RegisterActivity.this, TrangChuActivity.class));
                }
            }
        });
    }
    private boolean CheckCededentials(){

        String username = mEdtUsername.getText().toString();
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();
        String confirmPass = mEdtConfirmPass.getText().toString();
        String ten = mEdtTen.getText().toString();
        String sdt = mEdtSDT.getText().toString();
        int flag = 0;



        if(ten.isEmpty()) {
            showError(mEdtTen, "name can't be empty");
            flag++;
        }
        if(sdt.isEmpty()){
            showError(mEdtSDT, "phone numbet can't be empty");
        }
        if(username.isEmpty() || username.length() < 8) {
            showError(mEdtUsername, "Your user is not valid");
            flag++;
        }
        if(email.isEmpty() || !email.contains("@")) {

            showError(mEdtEmail, "Your email is not valid!");
            flag++;
        }

        if(password.isEmpty()) {
            showError(mEdtPassword, "Your password can't be empty");
            flag++;
        }
        else if(password.length() < 8) {
            showError(mEdtPassword, "Password should be more than 8 letters");
            flag++;
        }
        if(!confirmPass.matches(password)) {
            showError(mEdtConfirmPass, "Your password does not match");
            flag++;
        }
        if(confirmPass.isEmpty()) {
            showError(mEdtConfirmPass, "Please, confirm your password");
            flag++;
        }
        if(CheckEmailValid(email)){
            Toast.makeText(this, "Email da Ton Tai", Toast.LENGTH_SHORT).show();
            flag++;
        }
        if(CheckPhoneNumValid(sdt)){
            Toast.makeText(this, "So dien thoai da Ton Tai", Toast.LENGTH_SHORT).show();
            flag++;
        }

        return flag > 0;
    }
    @SuppressLint("Range")
    public void UpdateData(){
        if(khachHangs == null){
            khachHangs = new ArrayList<KhachHang>();
        } else{
            khachHangs.removeAll(khachHangs);
        }
        Cursor cursor = khdb.GetDataUser();
        if(cursor != null){
            while(cursor.moveToNext()){
                KhachHang khachHang = new KhachHang();

                khachHang.set_ten(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN)));
                khachHang.set_id(cursor.getLong(cursor.getColumnIndex(DBHelper.COT_USERID)));
                khachHang.set_username(cursor.getString(cursor.getColumnIndex(DBHelper.COT_USERNAME)));
                khachHang.set_email(cursor.getString(cursor.getColumnIndex(DBHelper.COT_EMAIL)));
                khachHang.set_password(cursor.getString(cursor.getColumnIndex(DBHelper.COT_PASS)));
                khachHang.set_ten(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN)));
                khachHang.set_sdt(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SDT)));

                khachHangs.add(khachHang);
            }
        }
    }
    public boolean CheckEmailValid(String email){
        SQLiteDatabase db=helper.getReadableDatabase();
//        email=mEdtEmail.getText().toString();
        Cursor cursor= db.query(DBHelper.TEN_BANG_USER,null,DBHelper.COT_EMAIL + " = ? " ,new String[]{email},null,null,null);
        while (cursor.moveToNext()){
            String emaildb=cursor.getString(2);

            if(email.equals(emaildb)){
                return true;
            }
        }
        return false;
    }
    public boolean CheckPhoneNumValid(String sdt){
        SQLiteDatabase db=helper.getReadableDatabase();
        sdt=mEdtSDT.getText().toString();
        Cursor cursor= db.query(DBHelper.TEN_BANG_USER,null,DBHelper.COT_SDT + " = ? " ,new String[]{sdt},null,null,null);
        while (cursor.moveToNext()){
            String sdtdb=cursor.getString(3);
            if(sdt.equals(sdtdb)){
                return true;
            }
        }
        return false;
    }
    public KhachHang GetDataUser(){

        String username = mEdtUsername.getText().toString();
        String password = mEdtPassword.getText().toString();
        String email = mEdtEmail.getText().toString();
        String ten = mEdtTen.getText().toString();
        String sdt = mEdtSDT.getText().toString();
        KhachHang khachHang = new KhachHang();
        khachHang.set_username(username);
        khachHang.set_password(password);
        khachHang.set_email(email);
        khachHang.set_ten(ten);
        khachHang.set_sdt(sdt);
        khachHang.set_role("user");

        return khachHang;
    }
    public void them(View view){
        KhachHang kh = GetDataUser();
        if(kh != null){
            if(khdb.them(kh) != -1){
                khachHangs.add(kh);
                UpdateData();
                //...
            }
        }
    }
    private void showError(EditText mEdt, String s)
    {
        mEdt.setError(s);
    }
}