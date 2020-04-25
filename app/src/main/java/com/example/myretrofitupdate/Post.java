package com.example.myretrofitupdate;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

//dilakukan extends dari class Abstract Item untuk pemodelan data dan view holder
public class Post extends AbstractItem<Post, Post.ViewHolder> {
    private String id;
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    private String no_telp;

    public Post(String id, String nama, String alamat, String jenis_kelamin, String no_telp) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getNo_telp() {
        return no_telp;
    }

    @NonNull
    @Override
    public Post.ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_siswa;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_siswa;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<Post> {
        TextView TextId, TextNama, TextAlamat, TextJk, TextNo;
        //Button ButtonEdit, ButtonHapus;

        public ViewHolder(View itemView) {
            super(itemView);
            TextId = itemView.findViewById(R.id.txt_id);
            TextNama = itemView.findViewById(R.id.txt_nama);
            TextAlamat = itemView.findViewById(R.id.txt_alamat);
            TextJk = itemView.findViewById(R.id.txt_jk);
            TextNo = itemView.findViewById(R.id.txt_no_telp);
        }

        @Override
        public void bindView(final Post item, List<Object> payloads) {
            TextId.setText(item.id);
            TextNama.setText(item.nama);
            TextAlamat.setText(item.alamat);
            TextJk.setText(item.jenis_kelamin);
            TextNo.setText(item.no_telp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("id", item.id);
                    intent.putExtra("nama", item.nama);
                    intent.putExtra("alamat", item.alamat);
                    intent.putExtra("jenis_kelamin", item.jenis_kelamin);
                    intent.putExtra("no_telp", item.no_telp);
                    context.startActivity(intent);
                }
            });
        }
//        public void myOnClickedit(View view) {
//            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
//            startActivity(intent);
//        }
        @Override
        public void unbindView(Post item) {
            TextId.setText(null);
            TextNama.setText(null);
            TextAlamat.setText(null);
            TextJk.setText(null);
            TextNo.setText(null);
        }
    }
}
