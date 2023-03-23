package com.example.foodypj.src.View;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodypj.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtTenQuanAn, txtDiaChiQuanAn;
    androidx.appcompat.widget.Toolbar toolbar;
    ImageButton btnChonHinh;
    final int PICK_IMAGE_REQUEST = 99;

    final int REQUEST_CHONHINHBINHLUAN = 11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binhluan);
        txtTenQuanAn = findViewById(R.id.txtTenQuanAn);
        txtDiaChiQuanAn = findViewById(R.id.txtDiaChiQuanAn);
        toolbar = findViewById(R.id.toolbar);
        btnChonHinh = findViewById(R.id.btnChonHinhAnh);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);*/

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
        switch (id){
            case R.id.btnChonHinhAnh:
                Intent iChonHinhBinhLuan = new Intent(this, ChonHinhBinhLuanActivity.class);
                startActivityForResult(iChonHinhBinhLuan,11);
                /*
//                iChonHinhBinhLuan.setType("image/*");
//                iChonHinhBinhLuan.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(iChonHinhBinhLuan, "Select Picture"), PICK_IMAGE_REQUEST);
//                break;*/
                List<Bitmap> images = new ArrayList<>();
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        images.add(bitmap);
                    }
                    cursor.close();
                }

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
