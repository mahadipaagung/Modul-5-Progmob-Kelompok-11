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

import com.example.Modul5Progmob.R;
import com.example.Modul5Progmob.ShowDetailActivity;
import com.example.Modul5Progmob.model.indexkegiatan.DataItem;

import java.util.ArrayList;
import java.util.List;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.ViewHolder> {
    List<DataItem> list = new ArrayList();
    Context context;

    public KegiatanAdapter(List<DataItem> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public KegiatanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kegiatan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KegiatanAdapter.ViewHolder holder, int position) {
        DataItem m = list.get(position);
        holder.nama.setText(m.getUser().getName());
        holder.nama_kegiatan.setText(m.getNamaKegiatan());
        holder.bidang.setText(m.getBidangKegiatan());
        holder.deskripsi.setText(m.getDeskripsiKegiatan());
        holder.tgl_kegiatan.setText(m.getTglKegiatan());

        holder.kegiatan_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetailActivity.class);
                intent.putExtra("nama", m.getUser().getName());
                intent.putExtra("nama_kegiatan", m.getNamaKegiatan());
                intent.putExtra("deskripsi", m.getDeskripsiKegiatan());
                intent.putExtra("bidang", m.getBidangKegiatan());
                intent.putExtra("tgl_kegiatan", m.getTglKegiatan());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, nama_kegiatan, tgl_kegiatan, deskripsi, bidang;
        LinearLayout kegiatan_show;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txName);
            nama_kegiatan = itemView.findViewById(R.id.txNama_Kegiatan);
            bidang = itemView.findViewById(R.id.txBidang_Kegiatan);
            deskripsi = itemView.findViewById(R.id.txDeskripsi_Kegiatan);
            tgl_kegiatan = itemView.findViewById(R.id.txTgl_Kegiatan);
            kegiatan_show = itemView.findViewById(R.id.kegiatan_item);
        }
    }

    public void showKegiatan(DataItem list){

    }
}
