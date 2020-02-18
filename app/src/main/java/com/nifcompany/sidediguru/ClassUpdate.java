package com.nifcompany.sidediguru;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

import static com.nifcompany.sidediguru.ClassDetail.classIdDetailClass;
import static com.nifcompany.sidediguru.MainActivity.API_TOKEN;

public class ClassUpdate extends AppCompatActivity {

    private Button btnUpdateClass, btnUpdateImage;
    private ImageView imageClass;
    private EditText edtNameClass;

    String vNameClass, vImageAddress;
    Integer getClassId;
    File fileImage;
    Bitmap bipmapImage;
    private int GALLERY = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_update);

        btnUpdateClass = findViewById(R.id.BtnUpdateClass);
        edtNameClass    = findViewById(R.id.EdtUpdateClassNamaKelas);
        imageClass = findViewById(R.id.ImgUpdateClassKelas);
        btnUpdateImage =  findViewById(R.id.BtnUpdateImage);

        vNameClass      = getIntent().getStringExtra("className");
        getClassId      = getIntent().getIntExtra("classId",0);
        vImageAddress   = getIntent().getStringExtra("imageAddress");

        Glide.with(imageClass.getContext())
                .load(vImageAddress)
                .into(imageClass);

        edtNameClass.setText(vNameClass);

        btnUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, GALLERY);
            }
        });

        btnUpdateClass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                vNameClass = edtNameClass.getText().toString();

                if (vNameClass.isEmpty()) {
                    edtNameClass.setError("Nama Kelas Harus Diisi");
                }
                else {
                    if (bipmapImage!=null){
                        uploadImage();
                    }
                    uploadNameClass();
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
                    imageClass.setImageBitmap(bipmapImage);
                    Glide.with(imageClass.getContext())
                            .load(contentURI)
                            .into(imageClass);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ClassUpdate.this, "Failed!", Toast.LENGTH_SHORT).show();
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

    private void uploadNameClass(){
        final ProgressDialog progressDialog = new ProgressDialog(ClassUpdate.this);
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


        String url = "/api/class/"+getClassId+"?api_token="+API_TOKEN;
        Call<ResponseBody> call = apiService.updateNameClass(url, vNameClass);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Toast.makeText(ClassUpdate.this, "Update Name Successfully!!", Toast.LENGTH_SHORT).show();
                        String newClassName =   vNameClass;

                        ShowPopup(newClassName);

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
        final ProgressDialog progressDialog = new ProgressDialog(ClassUpdate.this);
        progressDialog.setMessage("Memperbaharui Data...");
        progressDialog.show();

        fileImage = createTempFile(bipmapImage);
        RequestBody _method =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, "PUT");
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), fileImage);

        MultipartBody.Part body = MultipartBody.Part.createFormData("header_image", fileImage.getName(), reqFile);

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


        String url = "/api/class/"+classIdDetailClass+"/image?api_token="+API_TOKEN;
        Call<ResponseBody> call = apiService.updateImageClass(url, _method,body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Toast.makeText(ClassUpdate.this, "Image Uploaded Successfully!!", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public void ShowPopup(final String newUpdateName) {

        TextView tvName;
        Button btnUpdateClassSuccsess;

        final Dialog dialogUpdateClassSuccess = new Dialog(ClassUpdate.this);
        dialogUpdateClassSuccess.setContentView(R.layout.pop_up_class_update);

        tvName                   = dialogUpdateClassSuccess.findViewById(R.id.TvUpdateClassSuccessName);
        btnUpdateClassSuccsess   = dialogUpdateClassSuccess.findViewById(R.id.BtnUpdateClassSuccess);

        tvName.setText("Kelas : " + newUpdateName +" telah dibuat");

        dialogUpdateClassSuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUpdateClassSuccess.show();

        btnUpdateClassSuccsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassUpdate.this, ClassDetail.class);
                intent.putExtra("classIdDetailClass", getClassId);

                startActivity(intent);
                dialogUpdateClassSuccess.dismiss();
                finish();
            }
        });
    }
    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(ClassUpdate.this);
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