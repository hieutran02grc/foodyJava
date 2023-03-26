package com.example.foodypj.src.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.foodypj.R;
import com.example.foodypj.src.Adapter.AdapterViewPagerTrangChu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class TrangChuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    ViewPager viewPagerTrangChu;
    RadioButton rdOdau, rdAngi;
    AdapterViewPagerTrangChu adapterViewPagerTrangChu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FirebaseAuth firebaseAuth;
    Toolbar toolbar;
    TextView logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        logOut = findViewById(R.id.logout);
        viewPagerTrangChu = findViewById(R.id.viewpager_trangchu);
        rdOdau = findViewById(R.id.select_places);
        rdAngi = findViewById(R.id.select_food);

        rdOdau.setOnClickListener(this);
        rdAngi.setOnClickListener(this);
        logOut.setOnClickListener(this);

        adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
        viewPagerTrangChu.addOnPageChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.logout:
                Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                startActivity(iDangNhap);
                firebaseAuth.signOut();
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}