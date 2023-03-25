package com.example.foodypj.Controller;

import android.util.Log;

import com.example.foodypj.Controller.Interfaces.ThucDonInterface;
import com.example.foodypj.Model.ThucDonModel;

import java.util.List;

public class ThucDonController {
    ThucDonModel thucDonModel;
    public ThucDonController(){
        thucDonModel = new ThucDonModel();
    }

    public void getDanhSachThucDonQuanAnTheoMa(String maquanan){
        ThucDonInterface thucDonInterface = new ThucDonInterface() {
            @Override
            public void getThucDonThanhCong(List<ThucDonModel> thucDonModelList) {
                for(ThucDonModel thucDonModel : thucDonModelList){

                }
            }
        };
        thucDonModel.getDanhSachThucDonQuanAnTheoMa(maquanan, thucDonInterface);
    }
}