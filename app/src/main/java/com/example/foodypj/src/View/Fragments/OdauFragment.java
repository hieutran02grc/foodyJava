package com.example.foodypj.src.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.Controller.OdauController;
import com.example.foodypj.Model.QuanAnModel;
import com.example.foodypj.R;

public class OdauFragment extends Fragment {
    OdauController odauController;
    RecyclerView recyclerOdau;
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau,container,false);
        recyclerOdau = (RecyclerView) view.findViewById(R.id.recycleOdau);
        progressBar = view.findViewById(R.id.progress_barOdau);
        nestedScrollView = view.findViewById(R.id.nestScrollOdau);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        odauController = new OdauController(getContext());
        odauController.getDanhSachQuanAnController(getContext(),nestedScrollView,recyclerOdau, progressBar);
    }
}
