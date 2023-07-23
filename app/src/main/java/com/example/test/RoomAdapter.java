package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.Phong> {
    ArrayList<Room> rooms;
    Context context;
    RoomDatabase db;
    public RoomAdapter(ArrayList<Room> rooms, Context context) {
        this.rooms = rooms;
        this.context = context;
    }

    @NonNull
    @Override
    public Phong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.giaodien_dsphong,parent,false);
        return new Phong(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Phong holder, @SuppressLint("RecyclerView") int position) {
        Room room= rooms.get(position);
        holder.tvid.setText(String.valueOf(room.get_roomid()));
        holder.tvgia.setText(String.valueOf(room.get_price()));
        holder.tvmota.setText(room.get_description());

        holder.cvitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new RoomDatabase(context);
                Intent intent = new Intent(view.getContext(),BookActivity.class);
                Bundle b = new Bundle();
                String typeCurrent = getType(room.get_roomid());

                b.putString("typeRoom", typeCurrent);
                b.putLong("idRoom", room.get_roomid());

                intent.putExtras(b);
                context.startActivity(intent);


            }
        });

    }

    public String getType(long id){
        SQLiteDatabase database = db.helper.getWritableDatabase();

        Cursor cursor = database.query(DBHelper.TEN_BANG_PHONG, null, DBHelper.COT_ROOMID + " = ? ", new String[]{String.valueOf(id)}, null, null, null);
        String type = null;
        while (cursor.moveToNext()) {
            String typeRoom = cursor.getString(3);
            type = typeRoom;
        }

        return type;
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    class  Phong extends RecyclerView.ViewHolder{
        TextView tvid,tvgia,tvmota;

        CardView cvitem;

        public Phong(@NonNull View itemView){
            super(itemView);
            cvitem=itemView.findViewById(R.id.cvitem);
            tvid=itemView.findViewById(R.id.tvidphong);
            tvgia=itemView.findViewById(R.id.tvgia);
            tvmota=itemView.findViewById(R.id.tvmota);
        }

    }
}