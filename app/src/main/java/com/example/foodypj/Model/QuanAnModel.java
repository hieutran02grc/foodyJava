package com.example.foodypj.Model;

import android.graphics.Bitmap;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodypj.Controller.Interfaces.OdauInterface;
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
    List<String> tienich;
    List<String> hinhquanan ;
    List<BinhLuanModel> binhLuanModelList;
    List<Bitmap> bitmapList;

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    long luotthich;
    DatabaseReference rootNode ;

    public QuanAnModel(){
        rootNode = FirebaseDatabase.getInstance().getReference();
    }


    public List<BinhLuanModel> getBinhLuanModelList() {
        return binhLuanModelList;
    }

    public void setBinhLuanModelList(List<BinhLuanModel> binhLuanModelList) {
        this.binhLuanModelList = binhLuanModelList;
    }



    public List<String> getHinhquanan() {
        return hinhquanan;
    }

    public void setHinhquanan(List<String> hinhquanan) {
        this.hinhquanan = hinhquanan;
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

    private DataSnapshot dataRoot;

    public void getDanhSachQuanAn(final OdauInterface odauInterface, int itemTiepTheo, int itemDaCo){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataRoot = snapshot;
                LayDanhSachQuanAn(snapshot,odauInterface,itemTiepTheo,itemDaCo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        if(dataRoot != null){
            LayDanhSachQuanAn(dataRoot,odauInterface,itemTiepTheo,itemDaCo);
        }else {
            rootNode.addValueEventListener(valueEventListener);
        }

    }

    private void LayDanhSachQuanAn(DataSnapshot snapshot, OdauInterface odauInterface, int itemTiepTheo, int itemDaCo ){
        DataSnapshot dataSnapshotQuanAn = snapshot.child("quanans");
        int i = 0;
        //Lay danh sach quan an
        for (DataSnapshot valueQuanAn:dataSnapshotQuanAn.getChildren()){
            if(i == itemTiepTheo){
                break;
            }
            if(i < itemDaCo){
                i++;
                continue;
            }
            i++;
            QuanAnModel quanAnModel = valueQuanAn.getValue(QuanAnModel.class);
            quanAnModel.setMaquanan(valueQuanAn.getKey());

            //Lay danh sach hinh anh cua quan an theo ma;
            DataSnapshot dataSnapshotHinhQuanAn = snapshot.child("hinhanhquanans").child(valueQuanAn.getKey());
            List<String> hinhanhlst =  new ArrayList<>();
            for(DataSnapshot valueHinhQuanAn: dataSnapshotHinhQuanAn.getChildren()){
                hinhanhlst.add(valueHinhQuanAn.getValue(String.class));
            }
            quanAnModel.setHinhquanan(hinhanhlst);

            //Lay danh sach binh luan cua mot quan theo ma quan an
            DataSnapshot dataSnapshotBinhLuan = snapshot.child("binhluans").child(quanAnModel.getMaquanan());
            List<BinhLuanModel> binhLuanModelList = new ArrayList<>();
            for(DataSnapshot valueBinhLuan: dataSnapshotBinhLuan.getChildren()){
                BinhLuanModel binhLuanModel = valueBinhLuan.getValue(BinhLuanModel.class);
                binhLuanModel.setMabinhluan(valueBinhLuan.getKey());
                ThanhVienModel thanhVienModel = snapshot.child("thanhviens").child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                binhLuanModel.setThanhVienModel(thanhVienModel);

                List<String> hinhanhBinhLuanList = new ArrayList<>();

                DataSnapshot snapshotHinhAnhBinhLuan = snapshot.child("hinhanhbinhluans").child(binhLuanModel.getMabinhluan());

                for(DataSnapshot valueHinhBinhLuan : snapshotHinhAnhBinhLuan.getChildren()){
                    hinhanhBinhLuanList.add(valueHinhBinhLuan.getValue(String.class));

                }
                binhLuanModel.setHinhAnhList(hinhanhBinhLuanList);
                binhLuanModelList.add(binhLuanModel);
            }
            quanAnModel.setBinhLuanModelList(binhLuanModelList);
            odauInterface.getDanhSachQuanAnModel(quanAnModel);
        }
    }

}
