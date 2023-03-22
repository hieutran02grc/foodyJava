package com.example.foodypj.src.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodypj.Model.QuanAnModel;
import com.example.foodypj.R;

public class OdauFragment extends Fragment {
    QuanAnModel quanAnModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        quanAnModel = new QuanAnModel();
        quanAnModel.getDanhSachQuanAn();
    }
}
