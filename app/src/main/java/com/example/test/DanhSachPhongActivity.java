package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class DanhSachPhongActivity extends AppCompatActivity {

    RecyclerView rvRoom;


    RoomAdapter roomAdapter;

    RoomDatabase roomDatabase;

    ArrayList<Room> rooms;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phong);

        rvRoom=findViewById(R.id.rvrooms);
        roomDatabase=new RoomDatabase(this);

        ArrayList<Room> list = roomDatabase.GetSingleRoomDataForUser();


        roomAdapter=new RoomAdapter(list, DanhSachPhongActivity.this);

        rvRoom.setLayoutManager(new LinearLayoutManager(DanhSachPhongActivity.this,LinearLayoutManager.VERTICAL,false));

        rvRoom.setAdapter(roomAdapter);



    }



}