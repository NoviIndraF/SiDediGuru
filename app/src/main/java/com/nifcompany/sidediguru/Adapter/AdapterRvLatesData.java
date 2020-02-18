package com.nifcompany.sidediguru.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBack;
import com.nifcompany.sidediguru.R;

import java.util.List;

public class AdapterRvLatesData extends RecyclerView.Adapter<AdapterRvLatesData.ListViewHolder> {

    private List<com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem> listData;
    private OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public AdapterRvLatesData(List<com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem> list)
    {
        this.listData =  list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lates_menu, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int i) {
        ItemsItem data = listData.get(i);
        listViewHolder.tvKelas.setText(data.getClassName());
        listViewHolder.tvTanggal.setText(data.getCreatedAt());
        listViewHolder.tvJumlah.setText(data.getStudents()+" siswa");

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onItemClicked(listData.get(listViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvKelas, tvTanggal,tvJumlah;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKelas = itemView.findViewById(R.id.TvLatesNamaKelas);
            tvTanggal   = itemView.findViewById(R.id.TvLatesTanggal);
            tvJumlah    = itemView.findViewById(R.id.TvLatesJumlahSiswa);
        }
    }
}
