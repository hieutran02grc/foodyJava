package com.example.foodypj.Controller;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.Controller.Interfaces.OdauInterface;
import com.example.foodypj.Model.QuanAnModel;
import com.example.foodypj.R;
import com.example.foodypj.src.Adapter.AdapterRecycleOdau;

import java.util.ArrayList;
import java.util.List;

public class OdauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecycleOdau adapterRecycleOdau;
    public OdauController(Context context){
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAnController(RecyclerView recyclerOdau){
        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecycleOdau = new AdapterRecycleOdau(quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecycleOdau);
        OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                Log.d("kiemtra", quanAnModel.getHinhquanan()+"");
                quanAnModelList.add(quanAnModel);
                adapterRecycleOdau.notifyDataSetChanged();
            }
        };
        quanAnModel.getDanhSachQuanAn(odauInterface);
    }
}
