package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Adapter.AdapterTabReportDetail;
import com.nifcompany.sidediguru.Fragment.FragmentReportDetailIntoleran;
import com.nifcompany.sidediguru.Fragment.FragmentReportDetailToleran;
import com.nifcompany.sidediguru.Get.GetSpesificReport.Data;
import com.nifcompany.sidediguru.Get.GetSpesificReport.GetSpesificReport;
import com.nifcompany.sidediguru.Get.GetSpesificReport.KecenderunganNegatif;
import com.nifcompany.sidediguru.Get.GetSpesificReport.KecenderunganPositif;
import com.nifcompany.sidediguru.Get.GetSpesificReport.Meta;
import com.robertlevonyan.views.expandable.Expandable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nifcompany.sidediguru.MainActivity.API_TOKEN;

public class Report extends AppCompatActivity {

    public static Integer classId;
    private String  sNama, sTanggal;
    private Integer vValEksklusif, vValIntoleran, vValEkstream, vValKekerasan;
    private Integer vJumlahPositif=0, vJumlahNegatif=0;

    private TextView tvNamar, tvJumlahr, tvTanggalr, tvJumlahPositif, tvJumlahNegatif;
    private TextView tvEksklusif, tvIntoleran, tvEkstream, tvKekerasan;
    private TextView tvValEksklusif, tvValIntoleran, tvValEkstream, tvValKekerasan;
    private ImageView imgReport;
    private SwipeRefreshLayout swipeRefreshLayout;

    Data data;
    Meta meta;
    KecenderunganPositif kecenderunganPositif;
    KecenderunganNegatif kecenderunganNegatif;
    Expandable expReportClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        tvNamar =  findViewById(R.id.TvReportDetailNamaKelas);
        tvJumlahr = findViewById(R.id.TvReportDetailJumlahTotal);
        tvTanggalr = findViewById(R.id.TvReportDetailTanggal);
        tvJumlahPositif = findViewById(R.id.TvReportDetailToleran);
        tvJumlahNegatif = findViewById(R.id.TvReportDetailInToleran);

        tvEksklusif = findViewById(R.id.TvReportClassDetailEksklusif);
        tvIntoleran = findViewById(R.id.TvReportClassDetailIntoleranTeks);
        tvEkstream = findViewById(R.id.TvReportClassDetailEkstream);
        tvKekerasan = findViewById(R.id.TvReportClassDetailKekerasan);

        tvValEksklusif = findViewById(R.id.TvValReportClassDetailEksklusif);
        tvValIntoleran = findViewById(R.id.TvValReportClassDetailIntoleran);
        tvValEkstream = findViewById(R.id.TvValReportClassDetailEkstream);
        tvValKekerasan = findViewById(R.id.TvValReportClassDetailKekerasan);

        imgReport   = findViewById(R.id.ImgClassReport);

        expReportClass = findViewById(R.id.ExlReportClassDetail);
        expReportClass.setAnimateExpand(true);

        classId     = getIntent().getIntExtra("reportClassId",0);

        ambilDataDetailClass();

        swipeRefreshLayout = findViewById(R.id.SwRefreshReport);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        finish();
                        startActivity(getIntent());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        AdapterTabReportDetail sectionsPagerAdapter = new AdapterTabReportDetail(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        if (savedInstanceState==null){
            sectionsPagerAdapter.getItem(0);
        }
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.TbReportDetail);
        tabs.setupWithViewPager(viewPager);
    }

    public void ambilDataDetailClass() {
        final ProgressDialog progressDialog = new ProgressDialog(Report.this);
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
                    data = response.body().getData();
                    meta = response.body().getMeta();
                    kecenderunganPositif =  response.body().getMeta().getKecenderunganPositif();
                    kecenderunganNegatif =  response.body().getMeta().getKecenderunganNegatif();

                    Log.d("RESPON 0001", "kecenderungan Positif = " +kecenderunganPositif);
                    Log.d("RESPON 0002", "kecenderungan Negatif = " +kecenderunganNegatif);

                    Bundle b = new Bundle();
                    b.putParcelable("kecenderunganPositif", kecenderunganPositif);
                    b.putParcelable("kecenderunganNegatif", kecenderunganNegatif);

                    FragmentReportDetailToleran fragmentReportDetailToleran = new FragmentReportDetailToleran();
                    fragmentReportDetailToleran.setArguments(b);

                    FragmentReportDetailIntoleran fragmentReportDetailIntoleran = new FragmentReportDetailIntoleran();
                    fragmentReportDetailIntoleran.setArguments(b);

                    vJumlahPositif  = kecenderunganPositif.getStudentsCount();
                    vJumlahNegatif  = kecenderunganNegatif.getStudentsCount();
                    sNama           = data.getClassName();
                    sTanggal        = data.getCreatedAt();

                    vValEksklusif   = meta.getAverageClassReport().getValEkslusif();
                    vValIntoleran   = meta.getAverageClassReport().getValIntoleran();
                    vValKekerasan   = meta.getAverageClassReport().getValKekerasan();
                    vValEkstream   = meta.getAverageClassReport().getValEkstream();

                        Glide.with(imgReport)
                                .load(data.getImgUrl())
                                .apply(new RequestOptions().override(300,300))
                                .into(imgReport);


                    Integer sumStudent = vJumlahNegatif + vJumlahPositif;

                    tvNamar.setText(""+sNama);
                    tvTanggalr.setText(""+sTanggal);
                    tvJumlahr.setText(""+sumStudent);
                    tvJumlahPositif.setText(""+vJumlahPositif);
                    tvJumlahNegatif.setText(""+vJumlahNegatif);

                    GetDetailValueReport(tvValEksklusif, vValEksklusif);
                    GetDetailValueReport(tvValIntoleran, vValIntoleran);
                    GetDetailValueReport(tvValKekerasan, vValKekerasan);
                    GetDetailValueReport(tvValEkstream, vValEkstream);

                    tvEksklusif.setText(""+meta.getAverageClassReport().getEkslusif());
                    tvIntoleran.setText(""+meta.getAverageClassReport().getIntoleran());
                    tvEkstream.setText(""+meta.getAverageClassReport().getEkstream());
                    tvKekerasan.setText(""+meta.getAverageClassReport().getKekerasan());

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetSpesificReport> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("Error :", ""+t);
                ShowPopupNoConnection();
            }

        });
    }

    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(Report.this);
        dialogNoConnection.setContentView(R.layout.pop_up_no_connection);

        btnNoConnectionSuccsess   = dialogNoConnection.findViewById(R.id.BtnNoConnectionSuccess);


        dialogNoConnection.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogNoConnection.show();

        btnNoConnectionSuccsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogNoConnection.dismiss();
                finish();
            }
        });
    }

    private void GetDetailValueReport(TextView object, int val){
        if (val==3){
            object.setText(" = Tinggi");
            object.setTextColor(this.getResources().getColor(R.color.colorTinggi));
        }else if (val==2){
            object.setText(" = Sedang");
            object.setTextColor(this.getResources().getColor(R.color.colorSedang));
        } else if (val==1){
            object.setText(" = Rendah");
            object.setTextColor(this.getResources().getColor(R.color.colorRendah));
        }
    }
}
