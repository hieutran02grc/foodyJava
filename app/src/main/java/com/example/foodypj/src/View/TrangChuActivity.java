package com.example.foodypj.src.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;

import com.example.foodypj.R;
import com.example.foodypj.src.Adapter.AdapterViewPagerTrangChu;

public class TrangChuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager viewPagerTrangChu;
    RadioButton rdOdau, rdAngi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        viewPagerTrangChu = findViewById(R.id.viewpager_trangchu);
        rdOdau = findViewById(R.id.select_places);
        rdAngi = findViewById(R.id.select_food);

        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
        viewPagerTrangChu.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
       switch (position){
           case 0:
               rdOdau.setChecked(true);
               break;
           case 1:
               rdAngi.setChecked(true);
               break;
       }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}