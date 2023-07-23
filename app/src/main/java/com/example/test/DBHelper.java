package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper{



    private static final String TEN_DATABASE= "QuanLyKhachSan.db";


    // Du lieu bang user
    public static final String TEN_BANG_USER = "USER";

    public static final String COT_USERID = "_userid";
    public static final String COT_TEN = "_ten";
    public static final String COT_EMAIL = "_email";
    public static final String COT_SDT = "_sdt";
    public static final String COT_USERNAME = "_usr";
    public static final String COT_PASS = "_pass";
    public static final String COT_ROLE = "_role";





//Du lieu bang phong
    public static final String TEN_BANG_PHONG = "_room";
    public static final String COT_ROOMID = "_roomid";
    public static final String COT_GIATIEN = "_price";
    public static final String COT_LOAIPHONG = "_type";
    public static final String COT_USER_ID = "_user_id";
    public static final String COT_ID_CHINHANH = "_idlocation";
    public static final String COT_MOTA = "_description";
    public static final String COT_STATUS = "_status";

//Du lieu bang dat phong
    public static final String BANG_DAT_PHONG = "_book";
    public static final String COT_BOOKID = "_bookid";
    public static final String COT_ROOM_ID = "_room_id";
    public static final String COT_NGAYDAT = "_date1";
    public static final String COT_NGAYTRA = "_date2";

    public static final String COT_SOLUONG = "_soluong";
    public static final String COT_CHINHANHDAT="_chinhanhdat";
    public static final String COT_LOAIPHONGDAT="_loaiphongdat";

    public static final String TEN_BANG_HOTEL = "_hotel";
    public static final String COT_CHINHANH = "_chinhanh";

    public static final String COT_USER_ID_BOOK="_useridbook";

    public static final String COT_TEN_CHINHANH = "_tenchinhanh";
    public static final String COT_EMAIL_KS = "_emal_ks";
    public static final String COT_SDT_KS = "_sdt_ks";

    //Tao bang user
    private static final String TAO_BANG_USER = ""
            + "create table " + TEN_BANG_USER + " ( "
            + COT_USERID + " integer primary key unique, "
            + COT_TEN + " text, "
            + COT_EMAIL + " text not null, "
            + COT_SDT + " text, "
            + COT_USERNAME + " text not null, "
            + COT_PASS + " text not null, "
            + COT_ROLE + " text not null ); ";

    //Tao bang phong
    private static final String TAO_BANG_PHONG= ""
            + "create table " + TEN_BANG_PHONG + " ("
            + COT_ROOMID + " integer primary key unique, "
            + COT_GIATIEN + " integer not null, "
            + COT_ID_CHINHANH + " integer not null, "
            + COT_LOAIPHONG + " text not null, "
            + COT_STATUS + " boolean not null, "
            + COT_MOTA + " text, "
            + COT_USER_ID + " integer,"
            + "foreign key (" + COT_USER_ID + ") references " + TEN_BANG_USER + "(" + COT_USERID +") , "
            + "foreign key (" + COT_ID_CHINHANH+ ") references " + TEN_BANG_HOTEL + "(" + COT_CHINHANH + ") ) ";


    //Tao bang dat phong
    private static final String TAO_BANG_DP = ""
            + "create table " + BANG_DAT_PHONG + "("
            + COT_NGAYDAT + " date, "
            + COT_NGAYTRA + " date, "
            + COT_BOOKID + " integer primary key autoincrement, "
            + COT_SOLUONG + " text not null, "
            + COT_CHINHANHDAT + " text not null, "
            + COT_LOAIPHONGDAT + " text not null, "
            + COT_ROOM_ID + " integer,"
            + COT_USER_ID_BOOK + " interger ,"
            + "foreign key (" + COT_USER_ID_BOOK + ") references " + TEN_BANG_USER + "(" + COT_USERID +") , "
            + "foreign key (" + COT_ROOM_ID + ") references " + TEN_BANG_PHONG + "(" + COT_ROOMID + ") ) ";



    private static final String TAO_BANG_KHACHSAN = ""
            + " create table " + TEN_BANG_HOTEL + " ("
            + COT_CHINHANH + " integer primary key unique , "
            + COT_EMAIL_KS + " text not null, "
            + COT_TEN_CHINHANH + " text not null, "
            + COT_SDT_KS + " integer not null );";




    public SQLiteDatabase openDBOption1() {
        File dbFile = context.getDatabasePath(TEN_DATABASE);

        if (!dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }
    Context context;
    public DBHelper(Context context) {
        super(context, TEN_DATABASE, null, 1);
    }
    private void copyDatabase(File dbFile) throws IOException {
        InputStream is = context.getAssets().open(TEN_DATABASE);
        OutputStream os = new FileOutputStream(dbFile);

        byte[] buffer = new byte[1024];
        while (is.read(buffer) > 0) {
            os.write(buffer);
        }

        os.flush();
        os.close();
        is.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAO_BANG_USER);
        db.execSQL(TAO_BANG_PHONG);
        db.execSQL(TAO_BANG_DP);
        db.execSQL(TAO_BANG_KHACHSAN);

        ContentValues contentValues=new ContentValues();
        contentValues.put(COT_EMAIL_KS,"123@gmail.com");
        contentValues.put(COT_TEN_CHINHANH,"quan 1");
        contentValues.put(COT_SDT_KS, 1232);

        ContentValues contentValues2=new ContentValues();
        contentValues2.put(COT_EMAIL_KS,"1234@gmail.com");
        contentValues2.put(COT_TEN_CHINHANH,"quan 3");
        contentValues2.put(COT_SDT_KS, 1233);

        db.insert(TEN_BANG_HOTEL,null,contentValues);
        db.insert(TEN_BANG_HOTEL,null,contentValues2);


    }
         String UPDATETABLE = " ALTER TABLE BANG_DAT_PHONG ADD COT_SOLUONG text not null ";

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(UPDATETABLE);
    }

    public void capnhatdulieu(String name, String sdt,String email,String username){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COT_TEN,name);
        contentValues.put(COT_SDT,sdt);
        contentValues.put(COT_EMAIL,email);
        contentValues.put(COT_USERNAME,username);
        db.update(TEN_BANG_USER,contentValues,COT_EMAIL + " = ? ",new String[]{email});
        db.close();
    }

}

