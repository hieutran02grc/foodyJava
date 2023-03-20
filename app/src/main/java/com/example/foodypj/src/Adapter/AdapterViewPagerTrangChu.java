package com.example.foodypj.src.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;


import com.example.foodypj.src.View.Fragments.AnGiFragment;
import com.example.foodypj.src.View.Fragments.OdauFragment;

public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {
    AnGiFragment anGiFragment;
    OdauFragment odauFragment;



    public AdapterViewPagerTrangChu(FragmentManager fm) {
        super(fm);
        anGiFragment = new AnGiFragment();
        odauFragment = new OdauFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return odauFragment;
            case 1:
                return anGiFragment;

            default:return null;
        }
    }



    @Override
    public int getCount() {
        return 2;
    }
}
