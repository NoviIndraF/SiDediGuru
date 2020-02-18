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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nifcompany.sidediguru.MainActivity.ROOT_URL;

public class RegisterAccount extends AppCompatActivity {

    private Button btnSelanjutnya;
    private EditText edtEmail, edtPassword1, edtPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        btnSelanjutnya  = findViewById(R.id.BtnBuatAkunSelanjutnya);
        edtEmail        = findViewById(R.id.EdtBuatAkunEmail);
        edtPassword1    = findViewById(R.id.EdtBuatAkunPassword1);
        edtPassword2    = findViewById(R.id.EdtBuatAkunPassword2);

        final String email = edtEmail.getText().toString().trim();

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        btnSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vpassword1 = edtPassword1.getText().toString();
                String vpassword2 = edtPassword2.getText().toString();
                String vEmail = edtEmail.getText().toString();

                if (vpassword1.isEmpty() || vpassword2.isEmpty()||vEmail.isEmpty()){
                    if (vpassword1.isEmpty()){
                        edtPassword1.setError("Password belum diisi");
                    }
                    if (vpassword2.isEmpty()){
                        edtPassword2.setError("Password belum diisi");
                    }
                    if (vEmail.isEmpty()){
                        edtEmail.setError("Email belum diisi");
                    }
                }
                else if (vpassword1.length()<8 || vpassword2.length()<8 || !isEmailValid(vEmail)){
                    if (vpassword1.length()<8){
                        edtPassword1.setError("Password harus lebih dari 8 karakter");
                    }
                    if (vpassword2.length()<8){
                        edtPassword2.setError("Password harus lebih dari 8 karakter");
                    }
                    if (!isEmailValid(vEmail)){
                        edtEmail.setError("Email is not valid");
                    }
                }
                else if (!vpassword2.equals(vpassword1)){
                    edtPassword2.setError("Password harus sama");
                }
                else {
                    konfirmasi(vEmail, vpassword2);
                }
            }
        });
    }


    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void konfirmasi(final String email , final String vpassword2) {
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
                    edtEmail.setError("Email telah ada");
                }
                else {
                    Intent intent = new Intent(RegisterAccount.this, RegisterDataTeacher.class);
                    intent.putExtra("email", email);
                    intent.putExtra("password", vpassword2);
                    startActivity(intent);
                    finish();
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

        final Dialog dialogNoConnection = new Dialog(RegisterAccount.this);
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
