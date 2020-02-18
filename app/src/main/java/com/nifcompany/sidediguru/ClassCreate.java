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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nifcompany.sidediguru.Post.PostCreateClass.Data;
import com.nifcompany.sidediguru.Post.PostCreateClass.PostCreateClass;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nifcompany.sidediguru.MainActivity.API_TOKEN;


public class ClassCreate extends AppCompatActivity  {

    private Button btnBuatKelas, btnAmbilGambar;
    private ImageView gambarClass;
    private EditText edtNameClass;

    String vNameClass;
    File fileImage;
    Bitmap bipmapImage;
    private int GALLERY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_create);


        btnBuatKelas = findViewById(R.id.BtnCreateClass);
        edtNameClass    = findViewById(R.id.EdtCreateClassNamaKelas);
        gambarClass = findViewById(R.id.ImgCreateClassKelas);
        btnAmbilGambar =  findViewById(R.id.BtnAmbilGambar);

        btnAmbilGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, GALLERY);
            }
        });

        btnBuatKelas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            vNameClass = edtNameClass.getText().toString();

            if (vNameClass.isEmpty()) {
                edtNameClass.setError("Nama Kelas Harus Diisi");
            } else if (bipmapImage==null){
                edtNameClass.setError("Gambar Harus Dipilih");
            }
            else {
                uploadImage();
                Log.d("RESPON 1: ", "BitMap : " +bipmapImage.toString());
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
                    Log.d("RESPON 2: ", "BitMap : " +bipmapImage.toString());
                    gambarClass.setImageBitmap(bipmapImage);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ClassCreate.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void uploadImage(){
        final ProgressDialog progressDialog = new ProgressDialog(ClassCreate.this);
        progressDialog.setMessage("Membuat Kelas...");
        progressDialog.show();

        Log.d("RESPON 3: ", "BitMap : " +bipmapImage.toString());
        fileImage = createTempFile(bipmapImage);

        RequestBody name_class =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, vNameClass);
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

        Call<PostCreateClass> call = apiService.createClass(API_TOKEN, name_class, body);

        call.enqueue(new Callback<PostCreateClass>() {
            @Override
            public void onResponse(Call<PostCreateClass> call, Response<PostCreateClass> response) {
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Data data = response.body().getData();
                        Log.d("data response : ", data.getId() +" , "+ data.getNameClass());//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();

                        Integer newClassId  =   data.getId();
                        String newClassName =   data.getNameClass();

                        ShowPopup(newClassId, newClassName);

                        Toast.makeText(ClassCreate.this, "Image Uploaded Successfully!!", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PostCreateClass> call, Throwable t) {
                progressDialog.dismiss();
                ShowPopupNoConnection();
            }
        });
    }


    private File createTempFile(Bitmap bitmap) {

        Log.d("RESPON 1: ", "BitMap : " +bitmap.toString());
        fileImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() +"_image.jpeg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,0, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file

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

    public void ShowPopup(final Integer newClassId, String newClassName) {

        TextView tvName;
        Button btnCreateClassSuccsess;

        final Dialog dialogCreateClassSuccess = new Dialog(ClassCreate.this);
        dialogCreateClassSuccess.setContentView(R.layout.pop_up_class_create);

        tvName                   = dialogCreateClassSuccess.findViewById(R.id.TvCreateClassSuccessName);
        btnCreateClassSuccsess   = dialogCreateClassSuccess.findViewById(R.id.BtnCreateClassSuccess);

        tvName.setText("Kelas : " + newClassName +" telah dibuat");

        dialogCreateClassSuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogCreateClassSuccess.show();

        btnCreateClassSuccsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassCreate.this, ClassDetail.class);
                intent.putExtra("classIdDetailClass", newClassId);
                startActivity(intent);
                dialogCreateClassSuccess.dismiss();
                finish();
            }
        });
    }

    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(ClassCreate.this);
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