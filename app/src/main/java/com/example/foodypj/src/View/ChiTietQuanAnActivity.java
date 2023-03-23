package com.example.foodypj.src.View;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodypj.Model.QuanAnModel;
import com.example.foodypj.R;

public class ChiTietQuanAnActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietquanan);

        QuanAnModel quanAnModel = getIntent().getParcelableExtra("quanan");
        Log.d("kiemtra", quanAnModel.getTenquanan());
    }
}
