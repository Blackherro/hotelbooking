package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DanhSachPhongbon extends AppCompatActivity {

    RecyclerView rvRoom;

    RoomAdapter roomAdapter;

    ArrayList<Room> rooms;

    RoomDatabase roomDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phong);

        rvRoom=findViewById(R.id.rvrooms);

        roomDatabase=new RoomDatabase(this);

        ArrayList<Room> list = roomDatabase.GetSquadRoomDataForUser();


        roomAdapter=new RoomAdapter(list, DanhSachPhongbon.this);

        rvRoom.setLayoutManager(new LinearLayoutManager(DanhSachPhongbon.this,LinearLayoutManager.VERTICAL,false));

        rvRoom.setAdapter(roomAdapter);



    }


}