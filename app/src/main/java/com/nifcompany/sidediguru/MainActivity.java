package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Fragment.FragmentHome;
import com.nifcompany.sidediguru.Fragment.FragmentKelas;
import com.nifcompany.sidediguru.Fragment.FragmentReport;
import com.nifcompany.sidediguru.Get.GetAllClass.Data;
import com.nifcompany.sidediguru.Get.GetAllClass.GetAllClass;
import com.nifcompany.sidediguru.Get.GetAllClass.ItemsItem;
import com.nifcompany.sidediguru.Get.GetAllReport.GetAllReport;
import com.nifcompany.sidediguru.Get.GetProfileGuru.GetProfileGuru;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String ROOT_URL ="http://sidedi-um.com/api/";
    public static String API_TOKEN;
    ApiServices apiService;

    Data pulu;
    private com.nifcompany.sidediguru.Get.GetProfileGuru.Data dataProfile;
    SharedPrefManager sharedPrefManager;

    private BottomAppBar bottomAppBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressDialog progress;

    private List<ItemsItem> listDataClass = new ArrayList<>();
    private List<com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem> listDataReport = new ArrayList<>();
    com.nifcompany.sidediguru.Get.GetAllReport.Data dataReport;
    private int openFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = new SharedPrefManager(this);
        progress = new ProgressDialog(this);

        API_TOKEN   = sharedPrefManager.getSpApiToken();

        if (savedInstanceState == null) {
            openFragment =0;
            progress.show();
        } else
            { openFragment=10;}
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("Memuat...");

        ambilData();
        setUpBottomAppBar();

        findViewById(R.id.FabHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClassCreate.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "FAB Clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout = findViewById(R.id.SwRefreshMain);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                        ambilData();
            }
        });


    }

    public void setDataProfile(com.nifcompany.sidediguru.Get.GetProfileGuru.Data dataProfile) {
        this.dataProfile = dataProfile;
    }

    public com.nifcompany.sidediguru.Get.GetProfileGuru.Data getDataProfile() {
        return dataProfile;
    }

    public List<ItemsItem> getListDataClass() {
        return listDataClass;
    }

    public void setListDataClass(List<ItemsItem> listDataClass) {
        this.listDataClass = listDataClass;
    }

    public List<com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem> getListDataReport() {
        return listDataReport;
    }

    public void setListDataReport(List<com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem> listDataReport) {
        this.listDataReport = listDataReport;
    }

    public void ambilData() {

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

        apiService = retrofit.create(ApiServices.class);

        Call<GetProfileGuru> callProfile = apiService.getProfileGuru(API_TOKEN);
        callProfile.enqueue(new Callback<GetProfileGuru>() {
            @Override
            public void onResponse(Call<GetProfileGuru> call, Response<GetProfileGuru> response) {
                progress.dismiss();
                if (response.isSuccessful()) {

                    dataProfile=  response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<GetProfileGuru> call, Throwable t) {
                progress.dismiss();
                ShowPopupNoConnection();
                Toast.makeText(getApplicationContext(), "Error : Silahkan Cek Koneksi Anda :"+call.toString(), Toast.LENGTH_SHORT).show();
            }

        });

        Call<GetAllClass> callAllClass = apiService.getAllClass(API_TOKEN);
        callAllClass.enqueue(new Callback<GetAllClass>() {
            @Override
            public void onResponse(Call<GetAllClass> callAllClass, Response<GetAllClass> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    pulu=  response.body().getData();
                    Toast.makeText(getApplicationContext(), "Berhasil : ", Toast.LENGTH_SHORT).show();
                    listDataClass =  pulu.getItems();

                    switch (openFragment) {
                        case 0:
                        case 1:
                            FragmentHome fragmentHome = new FragmentHome();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_layout, fragmentHome, fragmentHome.getClass().getSimpleName())
                                    .commitAllowingStateLoss();
                            break;
                        case 2:
                            FragmentKelas fragmentKelas = new FragmentKelas();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_layout, fragmentKelas, fragmentKelas.getClass().getSimpleName())
                                    .commitAllowingStateLoss();
                            break;
                        case 3:
                            FragmentReport fragmentReport = new FragmentReport();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_layout, fragmentReport, fragmentReport.getClass().getSimpleName())
                                    .commitAllowingStateLoss();
                            break;
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<GetAllClass> callAllClass, Throwable t) {
                progress.dismiss();
                if(openFragment == 0){
                    FragmentHome fragmentHome = new FragmentHome();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragmentHome, fragmentHome.getClass().getSimpleName())
                            .commit();
                }
                ShowPopupNoConnection();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), "Error : Silahkan Cek Koneksi Anda :"+callAllClass.toString(), Toast.LENGTH_SHORT).show();
            }

        });

        Call<GetAllReport> callReport = apiService.getAllReport(API_TOKEN);
        callReport.enqueue(new Callback<GetAllReport>() {
            @Override
            public void onResponse(Call<GetAllReport> call, Response<GetAllReport> response) {
                if (response.isSuccessful()) {

                    dataReport=  response.body().getData();
                    listDataReport = dataReport.getItems();

                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<GetAllReport> call, Throwable t) {
                progress.dismiss();
                ShowPopupNoConnection();
                Toast.makeText(getApplicationContext(), "Error : Silahkan Cek Koneksi Anda :"+call.toString(), Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void setUpBottomAppBar() {
        bottomAppBar = findViewById(R.id.NbMenu);

        setSupportActionBar(bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        openFragment =1;
                        FragmentHome fragmentHome = new FragmentHome();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_layout, fragmentHome, fragmentHome.getClass().getSimpleName())
                                .commit();
                        return true;

                    case R.id.action_search:
                        openFragment =2;
                        FragmentKelas fragmentKelas = new FragmentKelas();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_layout, fragmentKelas, fragmentKelas.getClass().getSimpleName())
                                .commit();
                        return true;

                    case R.id.action_report:
                        openFragment =3;
                        FragmentReport fragmentReport = new FragmentReport();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_layout, fragmentReport, fragmentReport.getClass().getSimpleName())
                                .commit();
                        return true;
                }
                return false;
            }
        });

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetNavigationFragment.newInstance();
                Bundle b = new Bundle();
                b.putParcelable("pulu", pulu);
                bottomSheetDialogFragment.setArguments(b);
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                break;
            case R.id.action_search:
                break;
            case R.id.action_report:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //Ask the user if they want to quit
            ShowPopupOut();

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void ShowPopupOut() {

        Button btnOutYes, btnOutNo;

        final Dialog dialogDeleteClass = new Dialog(this);
        dialogDeleteClass.setContentView(R.layout.pop_up_out);

        btnOutYes           = dialogDeleteClass.findViewById(R.id.BtnOutYes);
        btnOutNo             = dialogDeleteClass.findViewById(R.id.BtnOutNo);

        dialogDeleteClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogDeleteClass.show();

        btnOutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDeleteClass.dismiss();

                finish();
            }
        });

        btnOutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDeleteClass.dismiss();
            }
        });
    }

    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(this);
        dialogNoConnection.setContentView(R.layout.pop_up_no_connection);

        btnNoConnectionSuccsess   = dialogNoConnection.findViewById(R.id.BtnNoConnectionSuccess);


        dialogNoConnection.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogNoConnection.show();

        btnNoConnectionSuccsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogNoConnection.dismiss();
            }
        });
    }
}
