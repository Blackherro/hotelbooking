package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RoomAdapterForAdmin extends RecyclerView.Adapter<RoomAdapterForAdmin.Phong> {
    Context context;

    ArrayList<Room> rooms;

    public ArrayList<Integer> Indexes = new ArrayList<Integer>();

    public RoomAdapterForAdmin(ArrayList<Room> rooms, Context context) {
        this.context = context;
        this.rooms = rooms;

    }

    @NonNull
    @Override
    public Phong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.giaodien_dsphong_admin, parent, false);
        return new RoomAdapterForAdmin.Phong(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Phong holder, @SuppressLint("RecyclerView") int position) {
        Room room = rooms.get(position);
        holder.tvten.setText(String.valueOf(room.get_roomid()));
        holder.tvmota.setText(room.get_description());
        holder.tvgiatien.setText(String.valueOf(room.get_price()));
        holder.tvloai.setText(room.get_type());
        holder.tvtrangthai.setText(String.valueOf(room.get_status()));
        holder.tvchinhanh.setText(String.valueOf(room.get_cn()));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DSPhongAdmin.edtchinhanh.setText(String.valueOf(room.get_cn()));
                DSPhongAdmin.edtgia.setText(String.valueOf(room.get_price()));
                DSPhongAdmin.edtloai.setText(String.valueOf(room.get_type()));
                DSPhongAdmin.edttrangthai.setText(String.valueOf(room.get_status()));
                DSPhongAdmin.edtmota.setText(String.valueOf(room.get_description()));
                DSPhongAdmin.edtid.setText(String.valueOf(room.get_roomid()));
            }
        });


    }


    @Override
    public int getItemCount() {
        return rooms.size();
    }

    class Phong extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvten, tvmota, tvgiatien, tvloai, tvtrangthai, tvchinhanh;


        public Phong(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.idsua);
            tvten = itemView.findViewById(R.id.tvtenphongadmin);
            tvmota = itemView.findViewById(R.id.tvmota);
            tvgiatien = itemView.findViewById(R.id.tvgiatienphong);
            tvchinhanh = itemView.findViewById(R.id.tvchinhanh);
            tvloai = itemView.findViewById(R.id.tvloaiphong);
            tvtrangthai = itemView.findViewById(R.id.tvtrangthaiphong);

        }

    }
}
