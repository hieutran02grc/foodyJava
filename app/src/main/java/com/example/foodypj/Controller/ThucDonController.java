package com.example.foodypj.Controller;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.Controller.Interfaces.ThucDonInterface;
import com.example.foodypj.Model.ThucDonModel;
import com.example.foodypj.src.Adapter.AdapterThucDon;

import java.util.ArrayList;
import java.util.List;

public class ThucDonController {
    ThucDonModel thucDonModel;
    public ThucDonController(){
        thucDonModel = new ThucDonModel();
    }

    public void getDanhSachThucDonQuanAnTheoMa(Context context, String maquanan, RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));




        ThucDonInterface thucDonInterface = new ThucDonInterface() {
            @Override
            public void getThucDonThanhCong(List<ThucDonModel> thucDonModelList) {
                for(ThucDonModel thucDonModel: thucDonModelList){
                    Log.d("kiemtra", " " + thucDonModel.getTenthucdon());
                    AdapterThucDon adapterThucDon = new AdapterThucDon(context, thucDonModelList);
                    recyclerView.setAdapter(adapterThucDon);
                    adapterThucDon.notifyDataSetChanged();
                }


            }
        };
        thucDonModel.getDanhSachThucDonQuanAnTheoMa(maquanan, thucDonInterface);
    }
}