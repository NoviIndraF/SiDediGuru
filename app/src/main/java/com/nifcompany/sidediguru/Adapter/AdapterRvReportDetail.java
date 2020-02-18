package com.nifcompany.sidediguru.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nifcompany.sidediguru.Get.GetSpesificReport.AnswersItem;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBack;
import com.nifcompany.sidediguru.R;

import java.util.List;

public class AdapterRvReportDetail extends RecyclerView.Adapter<AdapterRvReportDetail.ListViewHolder> {

    private List<AnswersItem> listData;
    private OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public AdapterRvReportDetail(List<AnswersItem> list)
    {
        this.listData = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_report_detail_student, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int i) {
        AnswersItem data = listData.get(i);
        listViewHolder.tvQuestion.setText(""+data.getQuestion().getQuestionId()+". "+data.getQuestion().getBody());
        String vValue ="";
        if (data.getAnswerValue()==1){
            vValue="Jawaban : Sangat Setuju";
        }else if (data.getAnswerValue()==2){
            vValue="Jawaban : Setuju";
        }else if (data.getAnswerValue()==3){
            vValue="Jawaban : Tidak Setuju";
        }
        else if (data.getAnswerValue()==4){
            vValue="Jawaban : Sangat Tidak Setuju";
        }

        listViewHolder.tvAnswer.setText(vValue);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion, tvAnswer;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.TvReportDetailStudentQuestion);
            tvAnswer   = itemView.findViewById(R.id.TvReportDetailStudentAnswer);
        }
    }
}
