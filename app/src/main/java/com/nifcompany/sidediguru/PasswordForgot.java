package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Post.PostPasswordForgot.PostPasswordForgot;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nifcompany.sidediguru.MainActivity.ROOT_URL;

public class PasswordForgot extends AppCompatActivity {

    EditText edtEmail;
    Button btnKonfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_forgot);

        edtEmail    = findViewById(R.id.EdtPasswordForgotEmail);
        btnKonfirmasi   = findViewById(R.id.BtnKonfirmasi);

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vEmail = edtEmail.getText().toString();
                if (vEmail.isEmpty()){
                    edtEmail.setError("Email belum diisi");
                }
                else {
                    konfirmasi(vEmail);
                }
            }
        });
    }

    private void konfirmasi(String email) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang Memeriksa...");
        progressDialog.show();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder oBuilder = new OkHttpClient.Builder();
        oBuilder.addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(oBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiServices service = retrofit.create(ApiServices.class);

        Call<PostPasswordForgot> call = service.passwordForgot(email);

        call.enqueue(new Callback<PostPasswordForgot>() {
            @Override
            public void onResponse(@NotNull Call<PostPasswordForgot> call, @NotNull Response<PostPasswordForgot> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), PasswordValidation.class);
                    intent.putExtra("email", response.body().getData().getEmail());
                    startActivity(intent);
                    finish();
                }
                else {
                    edtEmail.setError("Email tidak ditemukan");
                }
            }

            @Override
            public void onFailure(@NotNull Call<PostPasswordForgot> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("Error",""+t);
                ShowPopupNoConnection();
            }
        });
    }
    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(PasswordForgot.this);
        dialogNoConnection.setContentView(R.layout.pop_up_no_connection);

        btnNoConnectionSuccsess   = dialogNoConnection.findViewById(R.id.BtnNoConnectionSuccess);


        Objects.requireNonNull(dialogNoConnection.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogNoConnection.show();

        btnNoConnectionSuccsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogNoConnection.dismiss();
            }
        });
    }
}
