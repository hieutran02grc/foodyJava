package com.example.foodypj.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
    boolean giaohang;
    String giodongcua;
    String giomocua,tenquanan,videogioithieu,maquanan;
    List<String> tienich ;
    long luotthich;


    public QuanAnModel(){

    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public List<String> getTienich() {
        return tienich;
    }


}
