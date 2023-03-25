package com.example.foodypj.Controller;

import com.example.foodypj.Model.BinhLuanModel;

public class BinhLuanController {
    BinhLuanModel binhLuanModel;
    public BinhLuanController(){
        binhLuanModel = new BinhLuanModel();
    }
    public void ThemBinhLuan(String maquanan, BinhLuanModel binhLuanModel){
        binhLuanModel.ThemBinhLuan(maquanan,binhLuanModel);

    }

}
