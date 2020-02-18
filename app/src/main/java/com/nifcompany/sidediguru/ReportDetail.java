package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.nifcompany.sidediguru.Adapter.AdapterRvReportDetail;
import com.nifcompany.sidediguru.Get.GetSpesificReport.AnswersItem;
import com.nifcompany.sidediguru.Get.GetSpesificReport.Report;
import com.nifcompany.sidediguru.Get.GetSpesificReport.StudentsItem;
import com.robertlevonyan.views.expandable.Expandable;

import java.util.List;


public class ReportDetail extends AppCompatActivity {

    Report report;
    StudentsItem studentsItem;
    List<AnswersItem> listAnswerItem;

    String vName, vNIS, vEksklusif="", vIntoleran="", vEkstream="", vKekerasan="";
    Double vRataRata;
    Integer vKetEksklusif, vKetIntoleran, vKetEkstream, vKetKekerasan;

    RecyclerView rvReportDetail;
    AdapterRvReportDetail adapterRvReportDetail;

    TextView tvName, tvNIS, tvEksklusif, tvIntoleran, tvKekerasan, tvEkstream;

    TextView tvKetEksklusif, tvKetIntoleran, tvKetKekerasan, tvKetEkstream;
    Expandable ExplHasilAnalisis, ExplHasilJawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        rvReportDetail = findViewById(R.id.RvReportDetailStudent);

        tvName = findViewById(R.id.TvReportDetailName);
        tvNIS = findViewById(R.id.TvReportDetailNIS);
        tvEksklusif = findViewById(R.id.TvReportDetailEksklusif);
        tvEkstream = findViewById(R.id.TvReportDetailEkstream);
        tvIntoleran = findViewById(R.id.TvReportDetailIntoleranTeks);
        tvKekerasan = findViewById(R.id.TvReportDetailKekerasan);


        tvKetEksklusif = findViewById(R.id.TvKetReportDetailEksklusif);
        tvKetEkstream = findViewById(R.id.TvKetReportDetailEkstream);
        tvKetIntoleran = findViewById(R.id.TvKetReportDetailIntoleran);
        tvKetKekerasan = findViewById(R.id.TvKetReportDetailKekerasan);

        ExplHasilAnalisis = findViewById(R.id.ExlReportDetailStudent);
        ExplHasilAnalisis.setAnimateExpand(true);

        ExplHasilJawaban = findViewById(R.id.ExlReportDetailStudentAnswer);
        ExplHasilJawaban.setAnimateExpand(true);

        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            studentsItem = b.getParcelable("studentItem");
            report = b.getParcelable("report");
            listAnswerItem = studentsItem.getAnswers();
        }

        vName = studentsItem.getStudentName();
        vNIS = studentsItem.getNISN();
        vEksklusif = report.getEksklusifTeks();
        vEkstream = report.getEkstreamTeks();
        vIntoleran = report.getIntoleranTeks();
        vKekerasan = report.getKekerasanTeks();
        vRataRata   = report.getAverage();

        vKetEksklusif = report.getEksklusif();
        vKetEkstream = report.getEkstream();
        vKetIntoleran = report.getIntoleran();
        vKetKekerasan = report.getKekerasan();

        tvName.setText(vName);
        tvNIS.setText(vNIS);

        GetDetailValueReport(tvKetEksklusif, vKetEksklusif);
        GetDetailValueReport(tvKetKekerasan, vKetKekerasan);
        GetDetailValueReport(tvKetIntoleran, vKetIntoleran);
        GetDetailValueReport(tvKetEkstream, vKetEkstream);

        tvEksklusif.setText(vEksklusif);
        tvEkstream.setText(vEkstream);
        tvIntoleran.setText(vIntoleran);
        tvKekerasan.setText(vKekerasan);

        showRecyclerList();

    }

    private void showRecyclerList() {
        LinearLayoutManager classDetailStudent = new LinearLayoutManager(getApplicationContext());

        adapterRvReportDetail = new AdapterRvReportDetail(listAnswerItem);
        rvReportDetail.setLayoutManager(classDetailStudent);
        rvReportDetail.setAdapter( adapterRvReportDetail );

        rvReportDetail.setHasFixedSize(true);
    }

    private void GetDetailValueReport(TextView object, int val){
        if (val==3){
            object.setText("Tinggi");
            object.setTextColor(this.getResources().getColor(R.color.colorTinggi));
        }else if (val==2){
            object.setText("Sedang");
            object.setTextColor(this.getResources().getColor(R.color.colorSedang));
        } else if (val==1){
            object.setText("Rendah");
            object.setTextColor(this.getResources().getColor(R.color.colorRendah));
        }

    }
}
