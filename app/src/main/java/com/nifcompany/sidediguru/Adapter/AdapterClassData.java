package com.nifcompany.sidediguru.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nifcompany.sidediguru.Get.GetAllClass.ItemsItem;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBackRecentClass;
import com.nifcompany.sidediguru.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterClassData extends RecyclerView.Adapter<AdapterClassData.ListViewHolder> {

    private List<ItemsItem> listData;
    private OnItemClickCallBackRecentClass onItemClickCallBackRecentClass;

    public void setOnItemClickCallBack(OnItemClickCallBackRecentClass onItemClickCallBackRecentClass){
        this.onItemClickCallBackRecentClass = onItemClickCallBackRecentClass;
    }

    public AdapterClassData(List<ItemsItem> list)
    {
        this.listData = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_class_menu, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int i) {
        ItemsItem data = listData.get(i);
        listViewHolder.tvKelas.setText(data.getClassName());
        listViewHolder.tvTanggal.setText(data.getCreatedAt());

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBackRecentClass.onItemClicked(listData.get(listViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvKelas, tvTanggal;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKelas = itemView.findViewById(R.id.TvClassNamaKelas);
            tvTanggal   = itemView.findViewById(R.id.TvClassTanggal);
        }
    }

    public void setFilter(ArrayList<ItemsItem> filterList){
        listData = new ArrayList<>();
        listData.addAll(filterList);
        notifyDataSetChanged();
    }
}
