package com.example.test;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LichSuPhongAdapter extends RecyclerView.Adapter<LichSuPhongAdapter.LsDatPhong> {
    Context context;
    ArrayList<LichSuPhong> lsphong;

    DBHelper dbHelper;

    public LichSuPhongAdapter(Context context, ArrayList<LichSuPhong> lsphong) {
        this.context = context;
        this.lsphong = lsphong;
    }

    @NonNull
    @Override
    public LichSuPhongAdapter.LsDatPhong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.giaodien_lichsudatphong, parent, false);
        return new LichSuPhongAdapter.LsDatPhong(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuPhongAdapter.LsDatPhong holder, @SuppressLint("RecyclerView") int position) {
        dbHelper = new DBHelper(context);
        LichSuPhong lsDatPhong = lsphong.get(position);
        holder.ngaydat.setText(lsDatPhong.getNgaydat());
        holder.maphong.setText(String.valueOf(lsDatPhong.getRoomid()));
        holder.ngaytra.setText(lsDatPhong.getNgaytra());
        holder.soluong.setText(String.valueOf(lsDatPhong.getSoluong()));
        holder.chinhanh.setText(lsDatPhong.getChinhanh());
        holder.loagiuong.setText(lsDatPhong.getLoaiphong());


        holder.btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRoom(lsDatPhong.getBookid());
                convertStatusbookcancle(lsDatPhong.getRoomid());
                Toast.makeText(context, "Đã hủy lịch đặt phòng ", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(view.getContext(),AccountActivity.class));
            }
        });

    }
    public void convertStatusbookcancle(long id){
        SQLiteDatabase databaseud = dbHelper.getWritableDatabase();
        ContentValues contentValues1=new ContentValues();
        contentValues1.put(DBHelper.COT_STATUS, 0);
        databaseud.update(DBHelper.TEN_BANG_PHONG,contentValues1,DBHelper.COT_ROOMID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteRoom(int id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.BANG_DAT_PHONG, DBHelper.COT_BOOKID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public int getItemCount() {
        return lsphong.size();
    }

    class LsDatPhong extends RecyclerView.ViewHolder {

        TextView ngaydat, ngaytra, soluong, chinhanh, loagiuong, maphong;
        Button btnhuy;

        public LsDatPhong(@NonNull View itemView) {
            super(itemView);
            ngaydat = itemView.findViewById(R.id.tvngaydat);
            maphong = itemView.findViewById(R.id.tvmadat);
            ngaytra = itemView.findViewById(R.id.tvngaytra);
            soluong = itemView.findViewById(R.id.tvsonguoi);
            chinhanh = itemView.findViewById(R.id.tvchinhanh);
            loagiuong = itemView.findViewById(R.id.tvloaiphong);
            btnhuy = itemView.findViewById(R.id.btnhuy);
        }
    }
}
