package com.example.test;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RoomDatabase {
    SQLiteDatabase db;
    DBHelper helper;


    public  RoomDatabase(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public Cursor GetRoomAData(){
        String[] col = {
                DBHelper.COT_ROOMID,
                DBHelper.COT_GIATIEN,
                DBHelper.COT_LOAIPHONG,
                DBHelper.COT_USER_ID,
                DBHelper.COT_STATUS,
                DBHelper.COT_MOTA,
                DBHelper.COT_ID_CHINHANH
        };

        Cursor cursor = null;
        cursor = db.query(DBHelper.TEN_BANG_PHONG, col, null, null, null, null, DBHelper.COT_ROOMID + " ");
        return cursor;
    }
//    public boolean FindRoom(){
//        ContentValues values = new ContentValues();
//        String[] col = {DBHelper.COT_LOAIPHONG, DBHelper.COT_ROOMID};
//    }
    public long them(Room room){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_GIATIEN, room.get_price());
        values.put(DBHelper.COT_LOAIPHONG, room.get_type());
        values.put(DBHelper.COT_USER_ID, room.get_user_id());
        values.put(DBHelper.COT_STATUS, room.get_status());
        values.put(DBHelper.COT_MOTA, room.get_description());
        values.put(DBHelper.COT_ID_CHINHANH,room.get_cn());

        return db.insert(DBHelper.TEN_BANG_PHONG, null, values);
    }

    public long xoa(Room room){
        return db.delete(DBHelper.TEN_BANG_PHONG, DBHelper.COT_ROOMID + " = " + "'" + room.get_roomid() + "'", null );
    }

    public long sua(Room room){
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_GIATIEN, room.get_price());
        values.put(DBHelper.COT_LOAIPHONG, room.get_type());
        values.put(DBHelper.COT_ID_CHINHANH, room.get_cn());
        values.put(DBHelper.COT_STATUS, room.get_status());
        values.put(DBHelper.COT_MOTA, room.get_description());
        values.put(DBHelper.COT_USER_ID,room.get_user_id());

        return db.update(DBHelper.TEN_BANG_PHONG, values, DBHelper.COT_ROOMID + " = "  + room.get_roomid(), null);
    }

    public ArrayList<Room> GetSingleRoomDataForUser(){
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor=database.query(DBHelper.TEN_BANG_PHONG,null,helper.COT_STATUS + " = ?  and " + helper.COT_LOAIPHONG + " = ? " ,new String[]{"0","Single Room"},null,null,null);
        ArrayList<Room> listRoom = new ArrayList<>();
        while (cursor.moveToNext()){
            long roomID = cursor.getInt(0);
            int price = cursor.getInt(1);
            int idLocation = cursor.getInt(2);
            String room_type = cursor.getString(3);
            int status = cursor.getInt(4);
            String des = cursor.getString(5);
            int iduser = cursor.getInt(6);

            listRoom.add(new Room(roomID, iduser, price, room_type, des, status, idLocation));
        }

       return listRoom;
    }

    public ArrayList<Room> GetDoubleRoomDataForUser(){
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor=database.query(DBHelper.TEN_BANG_PHONG,null,helper.COT_STATUS + " = ? and " + helper.COT_LOAIPHONG + " = ? " ,new String[]{"0","Double Room"},null,null,null);
        ArrayList<Room> listRoom = new ArrayList<>();
        while (cursor.moveToNext()){
            long roomID = cursor.getInt(0);
            int price = cursor.getInt(1);
            int idLocation = cursor.getInt(2);
            String room_type = cursor.getString(3);
            int status = cursor.getInt(4);
            String des = cursor.getString(5);
            int iduser = cursor.getInt(6);

            listRoom.add(new Room(roomID, iduser, price, room_type, des, status, idLocation));
        }

        return listRoom;
    }

    public ArrayList<Room> GetSquadRoomDataForUser(){
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor=database.query(DBHelper.TEN_BANG_PHONG,null,helper.COT_STATUS + " = ? and " + helper.COT_LOAIPHONG + " = ? " ,new String[]{"0","Squad Room"},null,null,null);
        ArrayList<Room> listRoom = new ArrayList<>();
        while (cursor.moveToNext()){
            long roomID = cursor.getInt(0);
            int price = cursor.getInt(1);
            int idLocation = cursor.getInt(2);
            String room_type = cursor.getString(3);
            int status = cursor.getInt(4);
            String des = cursor.getString(5);
            int iduser = cursor.getInt(6);

            listRoom.add(new Room(roomID, iduser, price, room_type, des, status, idLocation));
        }

        return listRoom;
    }
}
