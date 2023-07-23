package com.example.test;

import android.app.Application;

import java.util.ArrayList;

public class Room extends Application {

    public Room(long _roomid,  long _user_id, long _price, String _type, String _description, int _status,int _cn) {
        this._roomid = _roomid;
        this._user_id = _user_id;
        this._price = _price;
        this._cn=_cn;
        this._type = _type;
        this._description = _description;
        this._status = _status;
    }

    public Room() {

    }

    private long _roomid;

    private long _user_id;
    private long _price;
    private String _type;
    private int _cn;
    private String _description;
    private int _status;


    //get methods
    long get_roomid() {return _roomid;}
    long get_user_id() {return _user_id;}
    long get_price(){return _price;}
    String get_type(){return _type;}
    Integer get_cn(){return _cn;}


    String get_description(){return _description;}
    int get_status(){return _status;}

    //set methods
    public void set_roomid(long id){this._roomid = id;}
    public void set_user_id(long id){this._user_id = id;}
    public void set_price(long price){this._price = price;}
    public void set_cn(int cn){this._cn=cn;}
    public void set_type(String type){this._type = type;}
    public void set_description(String description){this._description = description;}
    public void set_status(int status){this._status = status;}



    @Override
    public void onCreate() {
        super.onCreate();
    }

}
