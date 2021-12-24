package com.example.Modul5Progmob.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Modul5Progmob.EditEventActivity;
import com.example.Modul5Progmob.R;
import com.example.Modul5Progmob.model.getkegiatanuser.DataItem;

import java.util.ArrayList;
import java.util.List;

public class KegiatanUserAdapter extends RecyclerView.Adapter<KegiatanUserAdapter.ViewHolder> {
    List<DataItem> list = new ArrayList();
    Context context;

    public KegiatanUserAdapter(List<DataItem> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public KegiatanUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kegiatan_user_item, parent, false);
        return new KegiatanUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KegiatanUserAdapter.ViewHolder holder, int position) {
        DataItem m = list.get(position);
        holder.nama_kegiatan.setText(m.getNamaKegiatan());
        holder.bidang.setText(m.getBidangKegiatan());
        holder.tgl_kegiatan.setText(m.getTglKegiatan());



        holder.kegiatan_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditEventActivity.class);
                intent.putExtra("id_kegiatan", m.getId());
                intent.putExtra("nama_kegiatan", m.getNamaKegiatan());
                intent.putExtra("bidang", m.getBidangKegiatan());
                intent.putExtra("deskripsi", m.getDeskripsiKegiatan());
                intent.putExtra("tgl_kegiatan", m.getTglKegiatan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return list.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_kegiatan, tgl_kegiatan, bidang, deskripsi;
        LinearLayout kegiatan_show;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_kegiatan = itemView.findViewById(R.id.txNama_Kegiatan);
            bidang = itemView.findViewById(R.id.txBidang_Kegiatan);
            deskripsi = itemView.findViewById(R.id.txDeskripsi_Kegiatan);
            tgl_kegiatan = itemView.findViewById(R.id.txTgl_Kegiatan);
            kegiatan_show = itemView.findViewById(R.id.kegiatan_user_item);
        }
    }
}
