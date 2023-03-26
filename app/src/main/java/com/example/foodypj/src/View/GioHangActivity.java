package com.example.foodypj.src.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.foodypj.src.Adapter.AdapterMonAn.datMonList;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.Model.DatMon;
import com.example.foodypj.R;
import com.example.foodypj.src.Adapter.AdapterBinhLuan;
import com.example.foodypj.src.Adapter.DatMonAdapter;

public class GioHangActivity extends AppCompatActivity  {

    RecyclerView datmon_recycler_view ;
    Button button;
    TextView tongGiaTien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        button = findViewById(R.id.test);
        tongGiaTien = findViewById(R.id.tongGiaTien);
        datmon_recycler_view = findViewById(R.id.datmon_recycler_view);

        datmon_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        datmon_recycler_view.setAdapter(new DatMonAdapter(getApplicationContext(),datMonList));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GioHangActivity.this, "Đặt món thành công", Toast.LENGTH_SHORT).show();
                Intent iTrangChu = new Intent(getApplicationContext(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }
        });
        TinhTongGiaTien();
    }

    public void TinhTongGiaTien(){
        long sum =0;
        for(DatMon datMon : datMonList){
            sum += datMon.getGiatien();
        }
        tongGiaTien.setText(sum+"");
    }



}
