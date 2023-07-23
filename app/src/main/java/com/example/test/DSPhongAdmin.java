package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DSPhongAdmin extends AppCompatActivity {


    ArrayList<Room> rooms =new ArrayList<Room>();
    public static EditText edtgia,edtloai,edtmota,edtchinhanh,edttrangthai,edtid;
    String loai,mota;
    Integer status,cn;
    RoomDatabase roomDatabase;

    long giatien;
    long id;
     Button btnthem,btnsua,btnxoa;
    public static RecyclerView rvSingleRoomAdmin;
    DBHelper dbHelper;

    RoomAdapterForAdmin roomAdapterForAdmin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsphongdon_admin);
        dbHelper = new DBHelper(DSPhongAdmin.this);
        roomDatabase=new RoomDatabase(this);
        rvSingleRoomAdmin=findViewById(R.id.rycsingleroom);
        edtid=findViewById(R.id.edtid);
        edtgia=findViewById(R.id.edtgia);
        edtloai=findViewById(R.id.edtloai);
        edtmota=findViewById(R.id.edtmota);
        edtchinhanh=findViewById(R.id.edtchinhanh);
        edttrangthai=findViewById(R.id.edttrangthai);
        btnxoa=findViewById(R.id.btnxoa);
        btnsua=findViewById(R.id.btnsua);

        btnthem=findViewById(R.id.btnthem);
        rvSingleRoomAdmin.setLayoutManager(new LinearLayoutManager(DSPhongAdmin.this,LinearLayoutManager.VERTICAL,false));
        capnhatDuLieu();
        roomAdapterForAdmin= new RoomAdapterForAdmin(rooms, DSPhongAdmin.this);
        rvSingleRoomAdmin.setAdapter(roomAdapterForAdmin);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room room1=laydulieuNguoiDung();
                if(room1!=null){
                    if(roomDatabase.them(room1)!=-1){
                        rooms.add(room1);
                        capnhatDuLieu();
                    }
                }
                roomAdapterForAdmin=new RoomAdapterForAdmin(rooms, DSPhongAdmin.this);
                rvSingleRoomAdmin.setAdapter(roomAdapterForAdmin);

            }
        });

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Room room=laydulieuNguoiDung();
                    roomDatabase.xoa(room);
                    capnhatDuLieu();
                roomAdapterForAdmin.notifyDataSetChanged();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room room1=laydulieuNguoiDung();
                if(room1!=null&&id!=-1){
                    roomDatabase.sua(room1);
                    capnhatDuLieu();
                    roomAdapterForAdmin.notifyDataSetChanged();
                }
            }
        });


    }

    @SuppressLint("Range")
    public void capnhatDuLieu(){
        if(rooms==null){
            rooms=new ArrayList<Room>();
        } else {
            rooms.removeAll(rooms);
        }
        Cursor cursor=roomDatabase.GetRoomAData();
        if(cursor!=null){
            while (cursor.moveToNext()){
                Room room=new Room();
                room.set_roomid(cursor.getLong(cursor.getColumnIndex(DBHelper.COT_ROOMID)));
                room.set_price(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_GIATIEN)));
                room.set_type(cursor.getString(cursor.getColumnIndex(DBHelper.COT_LOAIPHONG)));
                room.set_cn(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_CHINHANH)));
                room.set_status(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_STATUS)));
                room.set_description(cursor.getString(cursor.getColumnIndex(DBHelper.COT_MOTA)));
                room.set_user_id(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_USER_ID)));

                rooms.add(room);

            }
        }
    }
    public Room laydulieuNguoiDung(){

        giatien= Long.parseLong(edtgia.getText().toString());
        loai=edtloai.getText().toString();
        mota=edtmota.getText().toString();
        status= Integer.parseInt(edttrangthai.getText().toString());
        cn=Integer.parseInt(edtchinhanh.getText().toString());
//        id=Integer.parseInt(edtid.getText().toString());
        if (loai.trim().length()==0||mota.trim().length()==0)
            return null;
        Room room = new Room();
        room.set_roomid(id);
        room.set_type(loai);
        room.set_status(status);
        room.set_cn(cn);
        room.set_price(giatien);
        room.set_description(mota);
        room.set_user_id(1);
        return room;
    }
}