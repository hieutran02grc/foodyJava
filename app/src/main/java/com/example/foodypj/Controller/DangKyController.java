package com.example.foodypj.Controller;

import com.example.foodypj.Model.ThanhVienModel;

public class DangKyController {
    ThanhVienModel thanhVienModel;
    public DangKyController(){
        thanhVienModel = new ThanhVienModel();
    }

    public void ThemThongTinThanhVienController(ThanhVienModel thanhVienModel, String uid){
        thanhVienModel.ThemThongTinThanhVien(thanhVienModel, uid);

    }


}
