package com.example.foodypj.src.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterHienThiBinhLuanDuocChon extends RecyclerView.Adapter<AdapterHienThiBinhLuanDuocChon.ViewHolder>{

    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;

        public  ViewHolder(View itemView){
            super(itemView);
            /*imageView = itemView.findViewById(R.id.item_image_view);*/

        }
    }

    @NonNull
    @Override
    public AdapterHienThiBinhLuanDuocChon.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
/*        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout_chonhinhbinhluan, parent, false);
        return new ViewHolder(view);*/
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHienThiBinhLuanDuocChon.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
