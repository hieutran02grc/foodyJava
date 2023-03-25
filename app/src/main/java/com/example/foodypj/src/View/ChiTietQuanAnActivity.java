package com.example.foodypj.src.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.Controller.ThucDonController;
import com.example.foodypj.Model.QuanAnModel;
import com.example.foodypj.Model.TienIchModel;
import com.example.foodypj.R;
import com.example.foodypj.src.Adapter.AdapterBinhLuan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAnActivity extends AppCompatActivity {
    TextView txtTenQuanAn, txtDiaChi,txtThoiGianHoatDong,
            txtTrangThaiHoatDong, txtTongSoHinhAnh,
            txtTongSoBinhLuan, txtTongSoLuuLai, txtTongSoCheckIn, txtTieuDeToolBar,txtGioiHanGia;
    ImageView imgHinhQuanAn,imgPlayVideo;
    QuanAnModel quanAnModel;
    Toolbar toolbar;
    RecyclerView recyclerViewBinhLuan,recyclerThucDon;
    AdapterBinhLuan adapterBinhLuan;
    LinearLayout khungTienIch;
    VideoView videotrailer;
    ThucDonController thucDonController;
    Button btnBinhLuan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_chitietquanan);

         quanAnModel = getIntent().getParcelableExtra("quanan");

        txtTenQuanAn = findViewById(R.id.txtTenQuanAn);
        txtDiaChi = findViewById(R.id.txtDiaChiQuanAn);
        txtThoiGianHoatDong = findViewById(R.id.thoiGianHoatDong);
        txtTrangThaiHoatDong = findViewById(R.id.trangThaiHoatDong);
        txtTongSoHinhAnh = findViewById(R.id.tongSoHinhAnh);
        txtTongSoBinhLuan = findViewById(R.id.tongSoBinhLuan);
        txtTongSoLuuLai = findViewById(R.id.tongSoLuuLai);
        txtTongSoCheckIn = findViewById(R.id.tongSoCheckIn);
        txtGioiHanGia = findViewById(R.id.txtGioiHanGia);
        imgHinhQuanAn = findViewById(R.id.imgHinhQuanAn);
        txtTieuDeToolBar = findViewById(R.id.txtTieuDeToolBar);
        toolbar = findViewById(R.id.toolbar);
        khungTienIch = findViewById(R.id.khungTienIch);
        recyclerViewBinhLuan = findViewById(R.id.recycleBinhLuanChiTietQuanAn);
        videotrailer = findViewById(R.id.videoTrailer);
        imgPlayVideo = findViewById(R.id.imgPlayTrailer);
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
        recyclerThucDon = findViewById(R.id.recyclerThucDon);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        thucDonController = new ThucDonController();

        HienThiChiTietQuanAn();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void HienThiChiTietQuanAn(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        String giohientai = dateFormat.format(calendar.getTime());
        Log.d("Kiem tra", giohientai);
        String giomocua = quanAnModel.getGiomocua();
        String giodongcua = quanAnModel.getGiodongcua();

        try {
            Date dateHientai = dateFormat.parse(giohientai);
            Date dateDongCua = dateFormat.parse(giodongcua);
            Date dateMoCua = dateFormat.parse(giomocua);

            if(dateHientai.after(dateMoCua) && dateHientai.before(dateDongCua)){
                txtTrangThaiHoatDong.setText(getString(R.string.damocua));
            }else{
                txtTrangThaiHoatDong.setText(getString(R.string.dadongcua));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtTieuDeToolBar.setText(quanAnModel.getTenquanan());
        txtTenQuanAn.setText(quanAnModel.getTenquanan());
        /*txtDiaChi.setText(quanAnModel.ge);*/
        txtThoiGianHoatDong.setText(quanAnModel.getGiomocua() + " - " +quanAnModel.getGiodongcua());
        txtTongSoHinhAnh.setText(quanAnModel.getHinhquanan().size()+ "");
        txtTongSoBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");
        txtThoiGianHoatDong.setText(giomocua + " - " + giodongcua);

        DownLoadHinhTienIch(khungTienIch);

        if(quanAnModel.getGiatoida() != 0 && quanAnModel.getGiatoithieu() != 0){
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String giatoithieu = numberFormat.format(quanAnModel.getGiatoithieu() + " đ");
            String giatoida = numberFormat.format(quanAnModel.getGiatoida() + "đ");
            txtGioiHanGia.setText(giatoida + " - " + giatoida);
        }else{
            txtGioiHanGia.setVisibility(View.VISIBLE);
        }

        StorageReference storageHinhQuanAn = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhquanan().get(0));
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imgHinhQuanAn.setImageBitmap(bitmap);
            }
        });

        if(quanAnModel.getVideogioithieu() != null){
            videotrailer.setVisibility(View.VISIBLE);
            imgPlayVideo.setVisibility(View.VISIBLE);
            imgHinhQuanAn.setVisibility(View.GONE);
            StorageReference storageVideo = FirebaseStorage.getInstance().getReference().child("videos").child(quanAnModel.getVideogioithieu());
            storageVideo.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    videotrailer.setVideoURI(uri);
                    videotrailer.seekTo(1);
                }
            });

            imgPlayVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videotrailer.start();
                    MediaController mediaController = new MediaController(ChiTietQuanAnActivity.this);
                    videotrailer.setMediaController(mediaController);
                    imgPlayVideo.setVisibility(View.GONE);
                }
            });
        }else{
            imgHinhQuanAn.setVisibility(View.VISIBLE);
            videotrailer.setVisibility(View.GONE);
            imgPlayVideo.setVisibility(View.GONE);

        }



        //Load Danh sach binh luan cua quan an
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBinhLuan.setLayoutManager(layoutManager);
        adapterBinhLuan = new AdapterBinhLuan(this,R.layout.custom_layout_binhluan,quanAnModel.getBinhLuanModelList());
        recyclerViewBinhLuan.setAdapter(adapterBinhLuan);
        adapterBinhLuan.notifyDataSetChanged();

        thucDonController.getDanhSachThucDonQuanAnTheoMa(this,quanAnModel.getMaquanan(), recyclerThucDon);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    private void DownLoadHinhTienIch(final LinearLayout khungTienIch) {

        for (String matienich : quanAnModel.getTienich()) {
            DatabaseReference nodeTienIch = FirebaseDatabase.getInstance().getReference().child("quanlytienichs").child(matienich);
            nodeTienIch.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    TienIchModel tienIchModel = snapshot.getValue(TienIchModel.class);
                    if(tienIchModel.getHinhtienich() != null || tienIchModel.getTentienich() != null){
                        StorageReference storageHinhQuanAn = FirebaseStorage.getInstance().getReference().child("hinhtienich");
                        long ONE_MEGABYTE = 1024 * 1024;
                        storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                ImageView imageTienIch = new ImageView(ChiTietQuanAnActivity.this);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50,50);
                                layoutParams.setMargins(10,10,10,10);
                                imageTienIch.setLayoutParams(layoutParams);
                                imageTienIch.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageTienIch.setPadding(5,5,5,5);

                                imageTienIch.setImageBitmap(bitmap);
                                khungTienIch.addView(imageTienIch);
                            }
                        });
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}
