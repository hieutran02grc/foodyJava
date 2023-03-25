package com.example.foodypj.src.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodypj.Controller.BinhLuanController;
import com.example.foodypj.Model.BinhLuanModel;
import com.example.foodypj.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtTenQuanAn, txtDiaChiQuanAn;
    androidx.appcompat.widget.Toolbar toolbar;
    ImageButton btnChonHinh;
    final int PICK_IMAGE_REQUEST = 99;
    String maquanan;
    final int REQUEST_CHONHINHBINHLUAN = 11;
    TextView dangBinhLuan;
    EditText edTieuDe, edNoiDungBInhLuan;
    SharedPreferences sharedPreferences;
    BinhLuanController binhLuanController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binhluan);

        maquanan = getIntent().getStringExtra("maquanan");
        sharedPreferences = getSharedPreferences("luudangnhap", MODE_PRIVATE);
        binhLuanController = new BinhLuanController();

        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDungBInhLuan = findViewById(R.id.edNoiDungBinhLuan);
        dangBinhLuan = findViewById(R.id.txtDangBinhLuan);
        txtTenQuanAn = findViewById(R.id.txtTenQuanAn);
        txtDiaChiQuanAn = findViewById(R.id.txtDiaChiQuanAn);
        toolbar = findViewById(R.id.toolbar);
        btnChonHinh = findViewById(R.id.btnChonHinhAnh);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);*/

        dangBinhLuan.setOnClickListener(this);
        btnChonHinh.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.txtDangBinhLuan:
                CharSequence txtNoiDungBL = edNoiDungBInhLuan.getText();
                if(TextUtils.isEmpty(txtNoiDungBL)){
                    Toast.makeText(this, "Nhập nội dung", Toast.LENGTH_SHORT).show();
                }else{
                    BinhLuanModel binhLuanModel = new BinhLuanModel();
                    String tieude = edTieuDe.getText().toString();
                    String noidung = edNoiDungBInhLuan.getText().toString();
                    String[] words = noidung.trim().split("\\s+");
                    if(words.length < 10) {
                        Toast.makeText(this, "Nội dung bình luận phải dài hơn 10 từ", Toast.LENGTH_SHORT).show();
                    }else{
                        String mauser = sharedPreferences.getString("mauser","");
                        binhLuanModel.setTieude(tieude);
                        binhLuanModel.setNoidung(noidung);
                        binhLuanModel.setChamdiem(0);
                        binhLuanModel.setLuotthich(0);
                        binhLuanModel.setMauser(mauser);
                        binhLuanController.ThemBinhLuan(maquanan, binhLuanModel);

                        Toast.makeText(this, "Đăng bình luận thành công", Toast.LENGTH_SHORT).show();
                    }

                }

                
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
/*        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Use the bitmap to load the image into an ImageView or manipulate the image data as needed.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
