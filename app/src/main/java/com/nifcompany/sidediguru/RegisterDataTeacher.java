package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Post.PostRegisterTeacher.Meta;
import com.nifcompany.sidediguru.Post.PostRegisterTeacher.PostRegisterTeacher;

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

public class RegisterDataTeacher extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;

    Button btnDaftar;
    RadioButton radioButton;
    RadioGroup radioGroup;
    TextView tvClassCode;
    EditText edtNama, edtNIP,  edtInstansi;
    Spinner spAgama;

    String vNama, vNIP,  vJk, vAgama, vEmail, vPassword, vInstansi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data_teacher);
        sharedPrefManager = new SharedPrefManager(this);

        btnDaftar   = findViewById(R.id.BtnDaftar);
        tvClassCode = findViewById(R.id.TvDaftar);

        edtNama     = findViewById(R.id.EdtNama);
        edtNIP     = findViewById(R.id.EdtNIP);
        edtInstansi = findViewById(R.id.EdtInstansi);


        spAgama = findViewById(R.id.SpAgama);

        radioGroup = findViewById(R.id.RgJenisKelamin);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        vEmail      = getIntent().getStringExtra("email");
        vPassword   = getIntent().getStringExtra("password");

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                vNama       = edtNama.getText().toString();
                vNIP        = edtNIP.getText().toString();
                vInstansi   = edtInstansi.getText().toString();

                vAgama  = spAgama.getSelectedItem().toString().toLowerCase();

                radioButton =  findViewById(selectedId);
                vJk = radioButton.getText().toString().toLowerCase();

                if (vNama.isEmpty()||vNIP.isEmpty()||vInstansi.isEmpty()){
                    if (vNama.isEmpty()){
                        edtNama.setError("Nama belum Diisi");
                    }
                    if(vNIP.isEmpty()){
                        edtNIP.setError("NIP belum Diisi");
                    }
                    if (vInstansi.isEmpty()){
                        edtInstansi.setError("Instansi belum Diisi");
                    }

                }
                else if (vNama.length()<5||vNIP.length()!=16){
                    if (vNama.length()<5) {
                        edtNama.setError("Nama harus lebih dari 5 karakter");
                    }
                    if (vNIP.length()!=16){
                        edtNIP.setError("NIP harus 16 angka");
                    }
                }
                else{
                    teacherProfile(vNama, vEmail, vPassword, vNIP, vJk, vAgama, vInstansi);
                }
            }
        });
    }

    private void teacherProfile(final String nama, final String email, final String password, final String nip, final String jk, final String agama, final String instansi) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang Mendaftarkan...");
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

        Call<PostRegisterTeacher> call = service.teacherRegister(nama, email, password, nip, jk, agama,instansi);

        call.enqueue(new Callback<PostRegisterTeacher>() {
            @Override
            public void onResponse(@NotNull Call<PostRegisterTeacher> call, @NotNull Response<PostRegisterTeacher> response) {
                progressDialog.dismiss();

                assert response.body() != null;
                if (response.isSuccessful()) {
                    Meta metaDaftar = response.body().getMeta();

                    sharedPrefManager.saveSPString(SharedPrefManager.SP_API_TOKEN, metaDaftar.getApiToken());
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(@NotNull Call<PostRegisterTeacher> call, Throwable t) {
                progressDialog.dismiss();
                ShowPopupNoConnection();
            }
        });
    }
    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(RegisterDataTeacher.this);
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
