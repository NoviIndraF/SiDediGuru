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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Post.PostPasswordValidation.PostPasswordValidation;

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

public class PasswordValidation extends AppCompatActivity {

    String vEmail;
    EditText edtPassword1, edtPassword2;
    Button btnValidasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_validation);

        vEmail = getIntent().getStringExtra("email");

        edtPassword1    = findViewById(R.id.EdtPasswordValidationPassword1);
        edtPassword2   = findViewById(R.id.EdtPasswordValidationPassword2);

        btnValidasi = findViewById(R.id.BtnValidation);

        btnValidasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vpassword1 = edtPassword1.getText().toString();
                String vpassword2 = edtPassword2.getText().toString();

                if (vpassword1.isEmpty() || vpassword2.isEmpty()){
                    if (vpassword1.isEmpty()){
                        edtPassword1.setError("Password belum diisi");
                    }
                    if (vpassword2.isEmpty()){
                        edtPassword2.setError("Password belum diisi");
                    }
                }
                else if (vpassword1.length()<8 || vpassword2.length()<8){
                    if (vpassword1.length()<8){
                        edtPassword1.setError("Password harus lebih dari 8 karakter");
                    }
                    if (vpassword2.length()<8){
                        edtPassword2.setError("Password harus lebih dari 8 karakter");
                    }
                }
                else if (!vpassword2.equals(vpassword1)){
                        edtPassword2.setError("Password harus sama");
                    }
                else {
                    Validasi(vEmail, vpassword2);
                }
            }
        });
    }

    private void Validasi(String vEmail, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang Mengubah Password...");
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

        Call<PostPasswordValidation> call = service.passwordValidation(vEmail, password);

        call.enqueue(new Callback<PostPasswordValidation>() {
            @Override
            public void onResponse(@NotNull Call<PostPasswordValidation> call, @NotNull Response<PostPasswordValidation> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if (response.isSuccessful()) {
                    ShowPopup();
                }
            }

            @Override
            public void onFailure(@NotNull Call<PostPasswordValidation> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                ShowPopupNoConnection();
            }
        });
    }

    public void ShowPopup() {

        Button btnChangePasswordSuccess;

        final Dialog dialogUpdateClassSuccess = new Dialog(PasswordValidation.this);
        dialogUpdateClassSuccess.setContentView(R.layout.pop_up_password_validation_done);

        btnChangePasswordSuccess = dialogUpdateClassSuccess.findViewById(R.id.BtnChangePasswordSuccess);


        Objects.requireNonNull(dialogUpdateClassSuccess.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUpdateClassSuccess.show();

        btnChangePasswordSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasswordValidation.this, Login.class);
                startActivity(intent);
                dialogUpdateClassSuccess.dismiss();
                finish();
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
