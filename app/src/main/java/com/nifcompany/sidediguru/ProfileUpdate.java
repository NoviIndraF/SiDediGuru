package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Get.GetProfileGuru.Data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.nifcompany.sidediguru.MainActivity.API_TOKEN;

public class ProfileUpdate extends AppCompatActivity {
    EditText edtName, edtEmail, edtNIP, edtInstitution;
    String vNama, vNIP,  vGender, vReligion, vEmail,  vInstansi;
    Spinner spAgama;

    ImageButton imgGuru;
    Button btnUpdate;
    RadioButton radioButton, rbLakiLaki, rbPerempuan;
    RadioGroup radioGroup;
    Data data;

    File fileImage;
    Bitmap bipmapImage;
    private int GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        data = getIntent().getParcelableExtra("data");

        edtName = findViewById(R.id.EdtProfileUploadName);
        edtEmail = findViewById(R.id.EdtProfileUploadEmail);
        edtNIP   = findViewById(R.id.EdtProfileUploadNIP);
        spAgama  =findViewById(R.id.SpProfileUploadReligion);
        edtInstitution   = findViewById(R.id.EdtProfileUploadInstitution);
        imgGuru =   findViewById(R.id.ImgProfileUploadGuru);
        btnUpdate = findViewById(R.id.BtnUpdateProfil);

        rbLakiLaki  = findViewById(R.id.RbUpdateProfilLaki);
        rbPerempuan  = findViewById(R.id.RbUpdateProfilPerempuan);

        radioGroup = findViewById(R.id.RgProfileUploadGender);

        vNama = data.getName();
        vNIP = data.getBiodata().getNIP();
        vGender = data.getBiodata().getGender().toLowerCase();
        vReligion = data.getBiodata().getReligion().toLowerCase();
        vEmail = data.getEmail();
        vInstansi = data.getBiodata().getInstitution();

        edtName.setText(vNama);
        edtEmail.setText(vEmail);
        edtNIP.setText(vNIP);
        edtInstitution.setText(vInstansi);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.agama, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAgama.setAdapter(adapter);

        if (vReligion != null) {
            int spinnerPosition = adapter.getPosition(vReligion);
            spAgama.setSelection(spinnerPosition);
        }

        if (vGender.equals("laki-laki")){
            radioGroup.check(R.id.RbUpdateProfilLaki);
        }
        else if (vGender.equals("perempuan")){
            radioGroup.check(R.id.RbUpdateProfilPerempuan);
        }


        Glide.with(imgGuru.getContext())
                .load(data.getBiodata().getImageProfile())
                .apply(new RequestOptions().override(300,300))
                .into(imgGuru);

        imgGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, GALLERY);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vNama       = edtName.getText().toString();
                vNIP        = edtNIP.getText().toString();
                vEmail      = edtEmail.getText().toString();
                vInstansi   = edtInstitution.getText().toString();

                vReligion  = spAgama.getSelectedItem().toString().toLowerCase();

                int selectedId = radioGroup.getCheckedRadioButtonId();

                radioButton =  findViewById(selectedId);
                vGender = radioButton.getText().toString().toLowerCase();

                if (vNama.isEmpty()||vNIP.isEmpty()||vInstansi.isEmpty()||vEmail.isEmpty()){
                    if (vNama.isEmpty()){
                        edtName.setError("Nama belum Diisi");
                    }
                    if(vNIP.isEmpty()){
                        edtNIP.setError("NIP belum Diisi");
                    }
                    if (vInstansi.isEmpty()){
                        edtInstitution.setError("Instansi belum Diisi");
                    }
                    if(vEmail.isEmpty()){
                        edtEmail.setError("Email belum Diisi");
                    }

                }
                else if (vNama.length()<5||vNIP.length()!=16){
                    if (vNama.length()<5) {
                        edtName.setError("Nama harus lebih dari 5 karakter");
                    }
                    if (vNIP.length()!=16){
                        edtNIP.setError("NIP harus 16 angka");
                    }
                }
                else
                    {
                        if (bipmapImage!=null){
                        uploadImage();
                    }
                    updateProfile();
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    bipmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    imgGuru.setImageBitmap(bipmapImage);
                    Glide.with(imgGuru.getContext())
                            .load(contentURI)
                            .into(imgGuru);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ProfileUpdate.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private File createTempFile(Bitmap bitmap) {
        fileImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() +"_image.jpeg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,0, bos);
        byte[] bitmapdata = bos.toByteArray();

        try {
            FileOutputStream fos = new FileOutputStream(fileImage);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileImage;
    }

    private void updateProfile(){
        final ProgressDialog progressDialog = new ProgressDialog(ProfileUpdate.this);
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


        String url = "profile?api_token="+API_TOKEN;
        Call<ResponseBody> call = apiService.updateProfile(url, vNama, vEmail,  vNIP, vGender, vReligion, vInstansi);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ShowPopup();
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                ShowPopupNoConnection();
            }
        });
    }

    private void uploadImage() {

        fileImage = createTempFile(bipmapImage);

        RequestBody _method =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, "PUT");
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), fileImage);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image_profile", fileImage.getName(), reqFile);

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

        String url = "profile/photo?api_token="+API_TOKEN;
        Call<ResponseBody> call = apiService.postUpdateImageProfile(url, _method,body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Toast.makeText(ProfileUpdate.this, "Gambar Berhasil diupload", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                        Toast.makeText(getApplicationContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void ShowPopup() {

        TextView tvName;
        Button btnUpdateClassSuccsess;

        final Dialog dialogUpdateClassSuccess = new Dialog(ProfileUpdate.this);
        dialogUpdateClassSuccess.setContentView(R.layout.pop_up_profile_update);

        tvName                   = dialogUpdateClassSuccess.findViewById(R.id.TvUpdateProfileSuccessName);
        btnUpdateClassSuccsess   = dialogUpdateClassSuccess.findViewById(R.id.BtnUpdateProfileSuccess);

        tvName.setText("Profil Anda telah diperbaharui");

        dialogUpdateClassSuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUpdateClassSuccess.show();

        btnUpdateClassSuccsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile.activityProfile.finish();

                Intent intent = new Intent(ProfileUpdate.this, Profile.class);
                startActivity(intent);
                dialogUpdateClassSuccess.dismiss();
                finish();
            }
        });
    }

    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(ProfileUpdate.this);
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
