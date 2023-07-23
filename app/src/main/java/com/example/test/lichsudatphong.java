package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class lichsudatphong extends AppCompatActivity {

    DBHelper dbHelper;
    public static LichSuPhongAdapter adapter;

    Button btnhuy;
    RecyclerView recyclerView;
    ArrayList<LichSuPhong> list = null;

    public  static final  String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";
    public static SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsudatphong);
        recyclerView = findViewById(R.id.rvlsdatphong);
        dbHelper = new DBHelper(lichsudatphong.this);

        btnhuy=findViewById(R.id.btnhuy);

        sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        list = getLichSuPhong(getMyID(email));
        adapter = new LichSuPhongAdapter(lichsudatphong.this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(lichsudatphong.this,LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(adapter);


    }

    public ArrayList<LichSuPhong> getLichSuPhong(long myID){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ArrayList<LichSuPhong> list= new ArrayList<>();
        Cursor cursor = database.query(DBHelper.BANG_DAT_PHONG, null, DBHelper.COT_USER_ID_BOOK + " = ? ", new String[]{String.valueOf(myID)}, null, null, null);
        while (cursor.moveToNext()){
            int bookid=cursor.getInt(2);
            long maPhong = cursor.getLong(6);
            String ngaydat = cursor.getString(0);
            String ngaytra = cursor.getString(1);
            String chinhanh = cursor.getString(4);
            int soluong = cursor.getInt(3);
            String loaiPhong = cursor.getString(5);


            list.add(new LichSuPhong(ngaydat, ngaytra, chinhanh, soluong, loaiPhong, maPhong ,bookid));
        }

        return list;
    }

    public long getMyID(String email){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TEN_BANG_USER, null, null , null, null, null, null);

        long id = 0;
        while (cursor.moveToNext()){
            if(cursor.getString(2).equals(email))
                id = cursor.getLong(0);
        }

        return  id;
    }


}