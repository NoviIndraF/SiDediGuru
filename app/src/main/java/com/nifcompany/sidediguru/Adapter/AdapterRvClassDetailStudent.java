package com.nifcompany.sidediguru.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nifcompany.sidediguru.Get.GetSpesificClass.StudentsItem;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBackStudentProfile;
import com.nifcompany.sidediguru.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRvClassDetailStudent extends RecyclerView.Adapter<AdapterRvClassDetailStudent.ListViewHolder> {

    private List<StudentsItem> listItem= new ArrayList<StudentsItem>();
    private OnItemClickCallBackStudentProfile onItemClickCallBackStudentProfile;

    public AdapterRvClassDetailStudent(List<StudentsItem> list) {
        this.listItem = list;
    }


    public void setOnItemClickCallBackStudentProfile(OnItemClickCallBackStudentProfile onItemClickCallBackStudentProfile){
        this.onItemClickCallBackStudentProfile = onItemClickCallBackStudentProfile;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_class_detail_student, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int i) {
        listViewHolder.tvStudent.setText(listItem.get(i).getStudentName());
        listViewHolder.tvTanggal.setText(listItem.get(i).getJoinAt());

        Log.d("RESPON Adapter", "onResponse: " + listItem.get(i).getStudentName());

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBackStudentProfile.onItemClicked(listItem.get(listViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudent, tvTanggal,tvNIS;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudent = itemView.findViewById(R.id.TvClassDetailStudentNama);
            tvTanggal   = itemView.findViewById(R.id.TvClassDetailStudentTanggal);
        }
    }
}
