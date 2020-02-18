package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Post.PostLoginTeacher.Meta;
import com.nifcompany.sidediguru.Post.PostLoginTeacher.PostLoginTeacher;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nifcompany.sidediguru.MainActivity.ROOT_URL;

public class Login extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;

    Button btnKeMasuk, btnKeDaftar;
    EditText edtEmailLogin, edtPasswordLogin;
    Intent intent;
    TextView tvAlertLogin, tvForgetPassword;

    String vEmail, vPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPrefManager = new SharedPrefManager(this);

        edtEmailLogin       = findViewById(R.id.EdtEmailLogin);
        edtPasswordLogin    = findViewById(R.id.EdtPasswordLogin);

        btnKeMasuk          = findViewById(R.id.BtnKeMasuk);
        btnKeDaftar         = findViewById(R.id.BtnKeDaftar);

        tvAlertLogin        = findViewById(R.id.TvAlertLogin);
        tvForgetPassword    = findViewById(R.id.TvForgetPassword);

        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(Login.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        else {
            btnKeMasuk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                vEmail      = edtEmailLogin.getText().toString();
                vPassword   = edtPasswordLogin.getText().toString();

                if (vEmail.isEmpty()){
                    edtEmailLogin.setError("Email belum Diisi");
                }

                if(vPassword.isEmpty()){
                    edtPasswordLogin.setError("Password belum Diisi");
                }
                else if (!vPassword.isEmpty()&&vPassword.length()<8){
                    edtPasswordLogin.setError("Password harus minimal 8 karakter");}

                if (!vEmail.isEmpty()&&!vPassword.isEmpty())
                {
                    loginTeacher(vEmail,vPassword);

                }

                }
            });

            btnKeDaftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(getApplicationContext(), RegisterAccount.class);
                    startActivity(intent);
                }
            });

            tvForgetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(getApplicationContext(), PasswordForgot.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void loginTeacher(final String email, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang mengenali...");
        progressDialog.show();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder oBuilder = new OkHttpClient.Builder();
        oBuilder.addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(5, TimeUnit.SECONDS) // write timeout
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(oBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiServices service = retrofit.create(ApiServices.class);

        Call<PostLoginTeacher> call = service.teacherLogin(email, password);

        call.enqueue(new Callback<PostLoginTeacher>() {
            @Override
            public void onResponse(Call<PostLoginTeacher> call, Response<PostLoginTeacher> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.body().getData()==null){
                        tvAlertLogin.setText("Akun tidak ditemukan");
                        tvAlertLogin.setVisibility(View.VISIBLE);
                    }
                    else{
                        Meta metaLogin = response.body().getMeta();

                        sharedPrefManager.saveSPString(SharedPrefManager.SP_API_TOKEN, metaLogin.getApiToken());
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        finish();
                    }
                }
                else {
                    edtEmailLogin.setError("Email harus diisi dengan benar");
                }
            }

            @Override
            public void onFailure(Call<PostLoginTeacher> call, Throwable t) {
                progressDialog.dismiss();
                ShowPopupNoConnection();
            }
        });
    }
    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(Login.this);
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
