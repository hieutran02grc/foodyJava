package com.example.foodypj.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ThucDonModel {
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

    public void getDanhSachThucDonQuanAnTheoMa(String maquanan){
        DatabaseReference nodeThucDon = FirebaseDatabase.getInstance().getReference().child("thucdonquanans").child(maquanan);
        nodeThucDon.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("kiem tra", snapshot + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
