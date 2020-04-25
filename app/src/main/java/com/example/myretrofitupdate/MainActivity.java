package com.example.myretrofitupdate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_title);
        final RecyclerView mahasiswa_mobileView = findViewById(R.id.rv_siswa);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);

        final List mahasiswa_mobile = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.113/rest_ci/index.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPost();
        if (TextUtils.isEmpty(getIntent().getStringExtra("nis"))) {
            call = jsonPlaceHolderApi.getPost();
        } else {
            call = jsonPlaceHolderApi.getPostById(getIntent().getStringExtra("nis"));
        }
        //Call<List<Post>> call = jsonPlaceHolderApi.getPostById("5156");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for(Post post:posts) {
                    mahasiswa_mobile.add(new Post(post.getId(), post.getNama(), post.getAlamat(), post.getJenis_kelamin(), post.getNo_telp()));
                }
                itemAdapter.add(mahasiswa_mobile);
                mahasiswa_mobileView.setAdapter(fastAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                mahasiswa_mobileView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
    public void myOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }
//    public void myOnClickedit(View view) {
//        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
//        startActivity(intent);
//    }

}
