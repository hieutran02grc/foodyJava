package com.example.foodypj.src.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodypj.R;


public class BinhLuanActivity extends AppCompatActivity {
    TextView txtTenQuanAn, txtDiaChiQuanAn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binhluan);
        txtTenQuanAn = findViewById(R.id.txtTenQuanAn);
        txtDiaChiQuanAn = findViewById(R.id.txtDiaChiQuanAn);
    }
}
