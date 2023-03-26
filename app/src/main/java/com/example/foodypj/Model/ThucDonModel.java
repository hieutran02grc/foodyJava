package com.example.foodypj.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodypj.Controller.Interfaces.ThucDonInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ThucDonModel {
    public ThucDonModel(String mathucdon, String tenthucdon) {
        this.mathucdon = mathucdon;
        this.tenthucdon = tenthucdon;
    }

    public ThucDonModel() {

    }

    String mathucdon;
    String tenthucdon;
    List<MonAnModel> monAnModels;

    public List<MonAnModel> getMonAnModels() {
        return monAnModels;
    }

    public void setMonAnModels(List<MonAnModel> monAnModels) {
        this.monAnModels = monAnModels;
    }

    public String getMathucdon() {
        return mathucdon;
    }

    public String getTenthucdon() {
        return tenthucdon;
    }

    public void setMathucdon(String mathucdon) {
        this.mathucdon = mathucdon;
    }

    public void setTenthucdon(String tenthucdon) {
        this.tenthucdon = tenthucdon;
    }

    public void getDanhSachThucDonQuanAnTheoMa(String maquanan, ThucDonInterface thucDonInterface){
        DatabaseReference nodeThucDonQuanAn = FirebaseDatabase.getInstance().getReference().child("thucdonquanans").child(maquanan);
        nodeThucDonQuanAn.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<ThucDonModel> thucDonModels = new ArrayList<>();

               for (DataSnapshot valueThucDon : snapshot.getChildren()){
                   final ThucDonModel thucDonModel = new ThucDonModel();

                   DatabaseReference nodeThucDon = FirebaseDatabase.getInstance().getReference().child("thucdons").child(valueThucDon.getKey());
                   nodeThucDon.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshotThucDon) {
                           String mathucdon = snapshotThucDon.getKey();
                           thucDonModel.setMathucdon(mathucdon);
                           thucDonModel.setTenthucdon(snapshotThucDon.getValue(String.class));
                           List<MonAnModel> monAnModels = new ArrayList<>();

                           for(DataSnapshot valueMonAn : snapshot.child(mathucdon).getChildren()){
                               MonAnModel monAnModel = valueMonAn.getValue(MonAnModel.class);
                               monAnModel.setMamon(valueMonAn.getKey());
                               monAnModels.add(monAnModel);
                               Log.d("Check", monAnModel.getTenmon()+"");
                           }
                           thucDonModel.setMonAnModels(monAnModels);
                           thucDonModels.add(thucDonModel);
                           thucDonInterface.getThucDonThanhCong(thucDonModels);
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

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
