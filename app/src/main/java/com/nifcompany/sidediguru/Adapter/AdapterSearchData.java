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

import java.util.List;

public class AdapterSearchData extends RecyclerView.Adapter<AdapterSearchData.ListViewHolder> {

    private List<ItemsItem> listData;
    private OnItemClickCallBackRecentClass OnItemClickCallBackRecentClass;

    public void setOnItemClickCallBack(OnItemClickCallBackRecentClass OnItemClickCallBackRecentClass){
        this.OnItemClickCallBackRecentClass = OnItemClickCallBackRecentClass;
    }

    public AdapterSearchData(List<ItemsItem> list)
    {
        this.listData = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search_menu, viewGroup, false);
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
                OnItemClickCallBackRecentClass.onItemClicked(listData.get(listViewHolder.getAdapterPosition()));
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
            tvKelas = itemView.findViewById(R.id.TvSearchNamaKelas);
            tvTanggal   = itemView.findViewById(R.id.TvSearchTanggal);
        }
    }
}
