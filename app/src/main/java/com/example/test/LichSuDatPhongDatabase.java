package com.example.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LichSuDatPhongDatabase {

    SQLiteDatabase db;
    DBHelper helper;

    public LichSuDatPhongDatabase(SQLiteDatabase db, DBHelper helper) {
        this.db = db;
        this.helper = helper;
    }

    public Cursor GetDataBook(){
        String[] cot={
                DBHelper.COT_NGAYDAT,
                DBHelper.COT_NGAYTRA,
                DBHelper.COT_LOAIPHONGDAT,
                DBHelper.COT_BOOKID,
                DBHelper.COT_SOLUONG,
                DBHelper.COT_CHINHANHDAT,
                DBHelper.COT_ROOM_ID
        };

        Cursor cursor=null;
        cursor=db.query(DBHelper.BANG_DAT_PHONG,cot,null, null, null, null,DBHelper.COT_BOOKID + " ");
        return cursor;
    }




}
