package com.example.foodypj.src.View;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


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
    DatMonAdapter datMonAdapter;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        button = findViewById(R.id.test);

        datmon_recycler_view = findViewById(R.id.datmon_recycler_view);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(DatMon datMon : datMonList){
                    Log.d("Mon an", datMon.getTenMonAn());
                }
            }
        });

        HienTHiMonDaDat();
    }

    public void HienTHiMonDaDat(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        datmon_recycler_view.setLayoutManager(layoutManager);
        datMonAdapter = new DatMonAdapter(this, datMonList);

        datmon_recycler_view.setAdapter(datMonAdapter);
        datMonAdapter.notifyDataSetChanged();
    }

    /*    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_giohang);
*//*        datmon_recycler_view = findViewById(R.id.datmon_recycler_view);*//*
        button = findViewById(R.id.test);

*//*        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        datmon_recycler_view.setLayoutManager(layoutManager);
        datMonAdapter = new DatMonAdapter(this, datMonList);

        datmon_recycler_view.setAdapter(datMonAdapter);
        datMonAdapter.notifyDataSetChanged();*//*


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(DatMon datMon : datMonList){
                    Log.d("Mon an", datMon.getTenMonAn());
                }
            }
        });

    }*/
}
