package com.nifcompany.sidediguru.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nifcompany.sidediguru.Get.GetSpesificReport.StudentsItem;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBackReport;
import com.nifcompany.sidediguru.R;

import java.util.List;

public class AdapterRvReportDetailToleran extends RecyclerView.Adapter<AdapterRvReportDetailToleran.ListViewHolder> {

    private List<StudentsItem> listItem;
    private OnItemClickCallBackReport onItemClickCallBackReport;


    public AdapterRvReportDetailToleran(List<StudentsItem> listKecPositif) {
        this.listItem = listKecPositif;
    }



    public void setOnItemClickCallBack(OnItemClickCallBackReport onItemClickCallBackReport){
        this.onItemClickCallBackReport = onItemClickCallBackReport;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_report_detail, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int i) {
        listViewHolder.tvName.setText(listItem.get(i).getStudentName());

        Log.d("RESPON Adapter", "onResponse: " + listItem.get(i).getStudentName());

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBackReport.onItemClicked(listItem.get(listViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.TvReportDetail);
        }
    }
}
