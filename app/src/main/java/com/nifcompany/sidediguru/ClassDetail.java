package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Adapter.AdapterTabClassDetail;
import com.nifcompany.sidediguru.Fragment.FragmentClassDetailQuestion;
import com.nifcompany.sidediguru.Fragment.FragmentClassDetailStudent;
import com.nifcompany.sidediguru.Get.GetSpesificClass.Data;
import com.nifcompany.sidediguru.Get.GetSpesificClass.GetSpesificClass;
import com.nifcompany.sidediguru.Get.GetSpesificClass.QuestionsItem;
import com.nifcompany.sidediguru.Get.GetSpesificClass.StudentsItem;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nifcompany.sidediguru.MainActivity.API_TOKEN;


public class ClassDetail extends AppCompatActivity {
    public List<StudentsItem> listStudentDetail = new ArrayList<>();
    public List<QuestionsItem> listQuestionItem = new ArrayList<>();
    Data data;
    public static Integer classIdDetailClass;
    String className;
    Toolbar toolbar;
    Bundle bundle;

    public List<StudentsItem> getListStudentDetail() {
        return listStudentDetail;
    }

    public List<QuestionsItem> getListQuestionItem() {
        return listQuestionItem;
    }

    private TextView tvNama, tvJumlah, tvTanggal, tvCodeRef;
    private ImageView imgClassDetail;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        toolbar =  findViewById(R.id.TbDetailClass);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Window window = this.getWindow();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        toolbar.inflateMenu(R.menu.menu_class);

        tvNama      =  findViewById(R.id.TvClassDetailNamaKelas);
        tvJumlah    = findViewById(R.id.TvClassDetailJumlahSiswa);
        tvTanggal   = findViewById(R.id.TvClassDetailTanggal);
        tvCodeRef   = findViewById(R.id.TvClassDetailCodeRef);
        imgClassDetail = findViewById(R.id.ImgClassDetailKelas);

        classIdDetailClass  = getIntent().getIntExtra("classIdDetailClass",0);

        ambilDataDetailClass();
        pindahData();

        swipeRefreshLayout = findViewById(R.id.SwRefreshClass);
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

    }

    public void ambilDataDetailClass() {

        Log.d("ALUR ", "3");
        final ProgressDialog progressDialog = new ProgressDialog(ClassDetail.this);
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

        String url = "/api/class/"+classIdDetailClass+"?api_token="+API_TOKEN;
        Call<GetSpesificClass> call = apiService.getSpesificClass(url);

        Log.d("ALUR ", "4");
        call.enqueue(new Callback<GetSpesificClass>() {
            @Override
            public void onResponse(Call<GetSpesificClass> call, Response<GetSpesificClass> response) {
                if (response.isSuccessful()) {

                    Log.d("ALUR ", "5");
                    data = response.body().getData();

                    className = data.getClassName();

                    tvNama.setText(data.getClassName());
                    tvJumlah.setText(data.getStudents()+" siswa");
                    tvTanggal.setText(data.getCreatedAt());
                    tvCodeRef.setText("Kode : "+data.getCodeRefferal());

                    listStudentDetail = response.body().getStudents();
                    listQuestionItem = response.body().getMeta().getQuestions();

                        Glide.with(imgClassDetail)
                                .load(data.getImgUrl())
                                .apply(new RequestOptions().override(300,300))
                                .into(imgClassDetail);

                    AdapterTabClassDetail sectionsPagerAdapter = new AdapterTabClassDetail(getApplicationContext(), getSupportFragmentManager(), bundle);
                    ViewPager viewPager = findViewById(R.id.VpClassDetail);
                        sectionsPagerAdapter.getItem(0);
                    viewPager.setAdapter(sectionsPagerAdapter);
                    TabLayout tabs = findViewById(R.id.TbClassDetail);
                    tabs.setupWithViewPager(viewPager);
                    Log.d("ALUR ", "7");

                }
                progressDialog.dismiss();

                Log.d("ALUR ", "8");
            }

            @Override
            public void onFailure(Call<GetSpesificClass> call, Throwable t) {
                progressDialog.dismiss();
                ShowPopupNoConnection();
            }

        });
    }

    private void pindahData() {

        ArrayList<StudentsItem> arrayStudentDetail = new ArrayList<>(listStudentDetail.size());
        arrayStudentDetail.addAll(listStudentDetail);

        ArrayList<QuestionsItem> arrayQuestionItem = new ArrayList<>(listQuestionItem.size());
        arrayQuestionItem.addAll(listQuestionItem);

        bundle = new Bundle();
        bundle.putParcelableArrayList("arrayStudentDetail", arrayStudentDetail );
        bundle.putParcelableArrayList("arrayQuestionItem", arrayQuestionItem );

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        FragmentClassDetailStudent fragInfo = new FragmentClassDetailStudent();
        fragInfo.setArguments(bundle);
        transaction.add(R.id.VpClassDetail,fragInfo);

        FragmentClassDetailQuestion fragQues = new FragmentClassDetailQuestion();
        fragInfo.setArguments(bundle);
        transaction.add(R.id.VpClassDetail,fragQues);
        transaction.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_class, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(ClassDetail.this, ClassUpdate.class);
                intent.putExtra("className", className);
                intent.putExtra("classId", data.getClassId());
                intent.putExtra("imageAddress", data.getImgUrl());
                startActivity(intent);
                finish();
                return true;
            case R.id.action_delete:
                ShowPopupDelete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ShowPopupDelete() {

        final TextView tvDeleteName;
        Button btnDeleteClassSYes, btnDeleteClassNo;

        final Dialog dialogDeleteClass = new Dialog(ClassDetail.this);
        dialogDeleteClass.setContentView(R.layout.pop_up_class_delete);

        tvDeleteName                 = dialogDeleteClass.findViewById(R.id.TvDeleteClassName);
        btnDeleteClassSYes           = dialogDeleteClass.findViewById(R.id.BtnDeleteClassYes);
        btnDeleteClassNo             = dialogDeleteClass.findViewById(R.id.BtnDeleteClassNo);

        tvDeleteName.setText("Hapus Kelas " + className);

        dialogDeleteClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogDeleteClass.show();

        btnDeleteClassSYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDeleteClass.dismiss();

                deleteClass();
            }
        });

        btnDeleteClassNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDeleteClass.dismiss();
            }
        });
    }

    public void deleteClass() {

        Log.d("ALUR ", "3");
        final ProgressDialog progressDialog = new ProgressDialog(ClassDetail.this);
        progressDialog.setMessage("Menghapus Kelas...");
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
        String url = "/api/class/"+classIdDetailClass+"?api_token="+API_TOKEN;
        Call<ResponseBody> call = apiService.getDeleteClass(url);

        Log.d("ALUR ", "4");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Kelas "+className+" telah dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                }

                Log.d("ALUR ", "8");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error : Silahkan Cek Koneksi Anda :"+call.toString(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(ClassDetail.this);
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
}
