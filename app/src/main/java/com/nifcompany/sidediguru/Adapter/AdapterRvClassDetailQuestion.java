package com.nifcompany.sidediguru.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nifcompany.sidediguru.Get.GetSpesificClass.QuestionsItem;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBack;
import com.nifcompany.sidediguru.R;

import java.util.List;

public class AdapterRvClassDetailQuestion extends RecyclerView.Adapter<AdapterRvClassDetailQuestion.ListViewHolder> {

    private List<QuestionsItem> listItem;
    private OnItemClickCallBack onItemClickCallBack;

    public AdapterRvClassDetailQuestion(List<QuestionsItem> list) {
        this.listItem = list;
    }


    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_class_detail_question, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int i) {
        listViewHolder.tvQuestion.setText(listItem.get(i).getQuestion());

        Log.d("RESPON Adapter", "onResponse: " + listItem.get(i).getQuestion());

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.TvClassDetailQuestion);
        }
    }
}
