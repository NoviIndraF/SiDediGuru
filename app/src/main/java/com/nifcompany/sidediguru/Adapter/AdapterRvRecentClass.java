package com.nifcompany.sidediguru.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nifcompany.sidediguru.Get.GetAllClass.Author;
import com.nifcompany.sidediguru.Get.GetAllClass.ItemsItem;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBackRecentClass;
import com.nifcompany.sidediguru.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRvRecentClass extends RecyclerView.Adapter<AdapterRvRecentClass.ListViewHolder> {

    private List<ItemsItem> listItem;
    private List<Author> listAuthor;
    private OnItemClickCallBackRecentClass OnItemClickCallBackRecentClass;

    public void setOnItemClickCallBackRecentClass(OnItemClickCallBackRecentClass OnItemClickCallBackRecentClass){
        this.OnItemClickCallBackRecentClass = OnItemClickCallBackRecentClass;
    }

    public AdapterRvRecentClass(List<ItemsItem> list)
    {
        this.listItem = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recent_menu, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int i) {
        ItemsItem data  = listItem.get(i);
        listViewHolder.tvKelas.setText(data.getClassName());
        listViewHolder.tvTanggal.setText(data.getCreatedAt());
        listViewHolder.tvJumlah.setText(data.getStudents()+" siswa");
        listViewHolder.tvGuru.setText(data.getAuthor().getName());

        Glide.with(listViewHolder.imgKelas.getContext())
                .load(data.getImgUrl())
                .apply(new RequestOptions().override(300,300))
                .into(listViewHolder.imgKelas);

        Glide.with(listViewHolder.civGuru.getContext())
                .load(data.getAuthor().getBiodata().getImage_profile())
                .apply(new RequestOptions().override(300,300))
                .into(listViewHolder.civGuru);

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnItemClickCallBackRecentClass.onItemClicked(listItem.get(listViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvKelas, tvTanggal,tvJumlah,tvGuru;
        ImageView imgKelas;
        CircleImageView civGuru;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKelas = itemView.findViewById(R.id.TvRecentNamaKelas);
            tvGuru  = itemView.findViewById(R.id.TvRecentGuru);
            tvTanggal   = itemView.findViewById(R.id.TvRecentTanggal);
            tvJumlah    = itemView.findViewById(R.id.TvRecentJumlahSiswa);

            imgKelas    = itemView.findViewById(R.id.ImgRecentKelasBg);
            civGuru     = itemView.findViewById(R.id.CivGuru);
        }
    }
}
