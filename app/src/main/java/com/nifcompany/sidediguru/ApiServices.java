package com.nifcompany.sidediguru;


import com.nifcompany.sidediguru.Get.GetAllClass.GetAllClass;
import com.nifcompany.sidediguru.Get.GetAllReport.GetAllReport;
import com.nifcompany.sidediguru.Get.GetProfileGuru.GetProfileGuru;
import com.nifcompany.sidediguru.Get.GetSpesificClass.GetSpesificClass;
import com.nifcompany.sidediguru.Get.GetSpesificReport.GetSpesificReport;
import com.nifcompany.sidediguru.Post.PostCreateClass.PostCreateClass;
import com.nifcompany.sidediguru.Post.PostLoginTeacher.PostLoginTeacher;
import com.nifcompany.sidediguru.Post.PostPasswordForgot.PostPasswordForgot;
import com.nifcompany.sidediguru.Post.PostPasswordValidation.PostPasswordValidation;
import com.nifcompany.sidediguru.Post.PostRegisterTeacher.PostRegisterTeacher;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiServices {

    //Auth
    @Headers({
            "Accept: application/json",
    })

    @POST("auth/forget")
    @FormUrlEncoded
    Call<PostPasswordForgot> passwordForgot(
            @Field("email") String email
    );


    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })

    @POST("auth/reset")
    @FormUrlEncoded
    Call<PostPasswordValidation> passwordValidation(
            @Field("email") String email,
            @Field("password") String password
    );


    //Author
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })

    @POST("login")
    @FormUrlEncoded
    Call<PostLoginTeacher> teacherLogin(
            @Field("email") String email,
            @Field("password") String password
    );


    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @POST("register")
    @FormUrlEncoded
    Call<PostRegisterTeacher> teacherRegister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("NIP") String NIP,
            @Field("gender") String gender,
            @Field("religion") String religion,
            @Field("institution") String institution
    );

    @Headers({
            "Accept: application/json"
    })


    @GET("profile?api_token=")
    Call<GetProfileGuru> getProfileGuru(@Query("api_token") String api_token);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @PUT
    @FormUrlEncoded
    Call<ResponseBody> updateProfile(@Url String url,
                                         @Field("name") String name,
                                         @Field("email") String email,
                                         @Field("NIP") String NIP,
                                         @Field("gender") String gender,
                                         @Field("religion") String religion,
                                         @Field("institution") String institution
    );

    @Multipart
    @POST
    Call<ResponseBody> postUpdateImageProfile(
            @Url String url,
            @Part("_method") RequestBody _method,
            @Part MultipartBody.Part header_image);


    //Class
    @Headers({
            "Accept: application/json"
    })

    @Multipart
    @POST("class?api_token=")
    Call<PostCreateClass> createClass(
            @Query("api_token") String api_token,
            @Part("class_name") RequestBody class_name,
            @Part MultipartBody.Part header_image
    );

    @GET("class?api_token")
    Call<GetAllClass> getAllClass(@Query("api_token") String api_token);

    @Headers({
            "Accept: application/json"
    })
    @GET
    Call<GetSpesificClass> getSpesificClass(@Url String url);

    @DELETE
    Call <ResponseBody>getDeleteClass(@Url String url);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @PUT
    @FormUrlEncoded
    Call<ResponseBody> updateNameClass(@Url String url,
                                       @Field("class_name") String class_name);

    @Headers({
            "Accept: application/json"
    })

    @Multipart
    @POST()
    Call<ResponseBody> updateImageClass(
            @Url String url,
            @Part("_method") RequestBody _method,
            @Part MultipartBody.Part header_image
    );


    //Report
    @Headers({
            "Accept: application/json"
    })

    @GET
    Call<GetSpesificReport> getSpesificReport(@Url String url);


    @GET("report?api_token")
    Call<GetAllReport> getAllReport(
            @Query("api_token") String api_token
    );
}
