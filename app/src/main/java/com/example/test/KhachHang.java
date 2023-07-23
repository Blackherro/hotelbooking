package com.example.test;

public class KhachHang {
    private String _ten;
    private long _id;
    private String _email;
    private String _sdt;
    private String _username;
    private String _password;
    private String _role;


    //get methods
    long get_id() {return _id;}
    String get_ten(){return _ten;}
    String get_sdt(){return _sdt;}
    String get_username(){return _username;}
    String get_password(){return _password;}
    String get_email(){return _email;}
    String get_role(){return _role;}

    //set methods
    public void set_id(long id){this._id = id;}
    public void set_ten(String ten){this._ten = ten;}
    public void set_sdt(String sdt){this._sdt = sdt;}
    public void set_email(String email){this._email = email;}
    public void set_username(String username){this._username = username;}
    public void set_password(String password){this._password = password;}
    public void set_role(String role){this._role = role;}
}
