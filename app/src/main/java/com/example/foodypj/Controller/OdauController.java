package com.example.foodypj.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.Controller.Interfaces.OdauInterface;
import com.example.foodypj.Model.QuanAnModel;
import com.example.foodypj.R;
import com.example.foodypj.src.Adapter.AdapterRecycleOdau;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class OdauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecycleOdau adapterRecycleOdau;
    int itemDaCo =3;
    public OdauController(Context context){
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAnController(Context context,NestedScrollView nestedScrollView,RecyclerView recyclerOdau, ProgressBar progressBar){
        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecycleOdau = new AdapterRecycleOdau(context,quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecycleOdau);
        progressBar.setVisibility(View.VISIBLE);
        final OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
/*
                quanAnModelList.add(quanAnModel);
                adapterRecycleOdau.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);*/
                final List<Bitmap> bitmaps = new ArrayList<>();
                for(String linkhinh : quanAnModel.getHinhquanan()){
                    StorageReference storageReferenceHinhAnh  = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageReferenceHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmaps.add(bitmap);
                            quanAnModel.setBitmapList(bitmaps);
                            if(quanAnModel.getBitmapList().size()== quanAnModel.getHinhquanan().size()){
                                quanAnModelList.add(quanAnModel);
                                adapterRecycleOdau.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }

            }
        };

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(v.getChildAt(v.getChildCount()-1 )!= null){
                    if(scrollY >= (v.getChildAt(v.getChildCount()-1)).getMeasuredHeight()-v.getMeasuredHeight()){
                        itemDaCo += 3;
                        quanAnModel.getDanhSachQuanAn(odauInterface,itemDaCo,itemDaCo-3);
                    }
                }
            }
        });

        quanAnModel.getDanhSachQuanAn(odauInterface,itemDaCo,0);

    }
}
