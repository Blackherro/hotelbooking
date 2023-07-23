package com.example.test;

public class LichSuPhong {

    private String ngaydat;
    private String ngaytra;
    private String chinhanh;
    private int soluong;
    private String loaiphong;

    private long roomid;

    int bookid;

    public LichSuPhong() {
    }

    public LichSuPhong(String ngaydat, String ngaytra, String chinhanh, int soluong, String loaiphong, long roomid, int bookid) {
        this.ngaydat = ngaydat;
        this.ngaytra = ngaytra;
        this.chinhanh = chinhanh;
        this.soluong = soluong;
        this.loaiphong = loaiphong;
        this.roomid = roomid;
        this.bookid = bookid;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public String getChinhanh() {
        return chinhanh;
    }

    public void setChinhanh(String chinhanh) {
        this.chinhanh = chinhanh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    public long getRoomid() {
        return roomid;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
}
