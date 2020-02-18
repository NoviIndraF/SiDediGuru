package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Get.GetProfileGuru.Data;
import com.nifcompany.sidediguru.Get.GetProfileGuru.GetProfileGuru;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nifcompany.sidediguru.MainActivity.API_TOKEN;

public class Profile extends AppCompatActivity {

    TextView tvName, tvEmail, tvNIP, tvJoinAt, tvReligion, tvGender, tvInstitution;
    ImageView imgProfile;

    ImageButton imgBtnProfileEdit;
    SwipeRefreshLayout swipeRefreshLayout;

    Data pulu;

    public static Activity activityProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        activityProfile = this;

        tvName = findViewById(R.id.TvProfileName);
        tvEmail = findViewById(R.id.TvProfileEmail);
        tvNIP   = findViewById(R.id.TvProfileNIP);
        tvJoinAt    = findViewById(R.id.TvProfileJoinAt);
        tvReligion  =findViewById(R.id.TvProfileReligion);
        tvGender    = findViewById(R.id.TvProfileGender);
        tvInstitution   = findViewById(R.id.TvProfileInstitution);

        imgBtnProfileEdit = findViewById(R.id.ImgBtnProfileEdit);
        imgProfile =   findViewById(R.id.ImgProfileGuru);

        swipeRefreshLayout  = findViewById(R.id.SwRefreshProfile);

        ambilData();

        swipeRefreshLayout = findViewById(R.id.SwRefreshProfile);
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

        imgBtnProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ProfileUpdate.class);
                intent.putExtra("data", pulu);
                startActivity(intent);
            }
        });
    }


    public void ambilData() {
        final ProgressDialog progressDialog = new ProgressDialog(Profile.this);
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

        Call<GetProfileGuru> call = apiService.getProfileGuru(API_TOKEN);

        call.enqueue(new Callback<GetProfileGuru>() {
            @Override
            public void onResponse(Call<GetProfileGuru> call, Response<GetProfileGuru> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    pulu=  response.body().getData();
                    tvName.setText(pulu.getName());
                    tvEmail.setText(pulu.getEmail());
                    tvGender.setText(pulu.getBiodata().getGender());
                    tvJoinAt.setText(pulu.getJoinAt());
                    tvNIP.setText(pulu.getBiodata().getNIP());
                    tvInstitution.setText(pulu.getBiodata().getInstitution());
                    tvReligion.setText(pulu.getBiodata().getReligion());

                    Log.d("Image", ""+pulu.getBiodata().toString());

                    Glide.with(imgProfile)
                            .load(pulu.getBiodata().getImageProfile())
                            .apply(new RequestOptions().override(300,300))
                            .into(imgProfile);

                }
            }

            @Override
            public void onFailure(Call<GetProfileGuru> call, Throwable t) {
                progressDialog.dismiss();
                ShowPopupNoConnection();
            }

        });
    }

    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(Profile.this);
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
