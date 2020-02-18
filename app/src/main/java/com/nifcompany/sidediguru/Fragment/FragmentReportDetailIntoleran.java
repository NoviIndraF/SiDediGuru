package com.nifcompany.sidediguru.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Adapter.AdapterRvReportDetailIntoleran;
import com.nifcompany.sidediguru.Get.GetSpesificReport.GetSpesificReport;
import com.nifcompany.sidediguru.Get.GetSpesificReport.Report;
import com.nifcompany.sidediguru.Get.GetSpesificReport.StudentsItem;
import com.nifcompany.sidediguru.Get.GetSpesificReport.KecenderunganNegatif;
import com.nifcompany.sidediguru.ApiServices;
import com.nifcompany.sidediguru.MainActivity;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBackReport;
import com.nifcompany.sidediguru.R;
import com.nifcompany.sidediguru.ReportDetail;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nifcompany.sidediguru.MainActivity.API_TOKEN;
import static com.nifcompany.sidediguru.Report.classId;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReportDetailIntoleran extends Fragment {
    private List<StudentsItem> listKecenderunganNegatif = new ArrayList<>();
    private KecenderunganNegatif kecenderunganNegatif;

    private RecyclerView rvReportKecenderunganNegatif;
    AdapterRvReportDetailIntoleran adapterRvReportKecenderunganNegatif;

    public FragmentReportDetailIntoleran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_detail_intoleran, container, false);
        rvReportKecenderunganNegatif = rootView.findViewById(R.id.RvReportDetailIntoleran);

        ambilDataDetailClass();
        Log.d("RESPON Fragment 1", "onResponse: " + kecenderunganNegatif);

        Log.d("RESPON DETAIL INTOLERAN", "classId: " + classId);

        return rootView;
    }

    private void showRecyclerList() {
        Log.d("RESPON Fragment 2", "onResponse: " + listKecenderunganNegatif);
        LinearLayoutManager classDetailStudent = new LinearLayoutManager(getContext());

        adapterRvReportKecenderunganNegatif = new AdapterRvReportDetailIntoleran(listKecenderunganNegatif);

        rvReportKecenderunganNegatif.setLayoutManager(classDetailStudent);

        rvReportKecenderunganNegatif.setAdapter(adapterRvReportKecenderunganNegatif);

        adapterRvReportKecenderunganNegatif.setOnItemClickCallBack(new OnItemClickCallBackReport() {
            @Override
            public void onItemClicked(StudentsItem studentsItem) {
                pindahData(studentsItem);
            }
        });

        rvReportKecenderunganNegatif.setHasFixedSize(true);
    }

    private void pindahData(StudentsItem studentsItem) {
        Report report = studentsItem.getReport();

        Intent intent = new Intent(getContext(), ReportDetail.class);
        Bundle b = new Bundle();
        b.putParcelable("studentItem", studentsItem);
        b.putParcelable("report", report);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void ambilDataDetailClass() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Mengambil Data...");
        progressDialog.show();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder oBuilder = new OkHttpClient.Builder();
        oBuilder.addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .client(oBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiServices apiService = retrofit.create(ApiServices.class);

        String url = "/api/report/"+classId+"?api_token="+API_TOKEN;
        Call<GetSpesificReport> call = apiService.getSpesificReport(url);

        call.enqueue(new Callback<GetSpesificReport>() {
            @Override
            public void onResponse(Call<GetSpesificReport> call, Response<GetSpesificReport> response) {
                if (response.isSuccessful()) {

                    kecenderunganNegatif = response.body().getMeta().getKecenderunganNegatif();
                    listKecenderunganNegatif = kecenderunganNegatif.getStudents();
                    showRecyclerList();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetSpesificReport> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Error : Silahkan Cek Koneksi Anda :" + call.toString(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}