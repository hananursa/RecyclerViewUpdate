package com.example.myretrofitupdate;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    //disini terdapat @GET("mahasiswa") artinya kita mengambil data mahasiswa
    @GET("mahasiswa_mobile")
    Call<List<Post>> getPost();

    //terdapat tambahan untuk mengambil data mahasiswa berdasarkan nim nya
    @GET("mahasiswa_mobile")
    Call<List<Post>> getPostById(
            @Query("id") String id
    );

    //lalu disini terdapat @POST("mahasiswa") artinya kita membuat pengiriman data siswa dengan method post
    @POST("mahasiswa_mobile")
    Call<ResponseBody> createPost(@Body Post post);

    //kode dibawah untuk mengubah data mahasiswa dengan method put
    @PUT("mahasiswa_mobile")
    Call<ResponseBody> editPost(@Body Post post);

//    @FormUrlEncoded
//    @POST("mahasiswa_mobile")
//    Call<ResponseBody> setPost(
//            @Field("id") String id, @Field("nama") String nama,
//            @Field("alamat") String alamat,
//            @Field("jenis_kelamin") String jenis_kelamin,
//            @Field("no_telp") String no_telp
//    );
//    @DELETE("mahasiswa/{nim}")
//    Call<ResponseBody> deletePost(
//            @Path("nim") String nim
//    );
}
