package com.example.foodypj.src.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.Model.DatMon;
import com.example.foodypj.Model.QuanAnModel;
import com.example.foodypj.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DatMonAdapter  extends  RecyclerView.Adapter<DatMonAdapter.ViewHolder>{

    List<DatMon> datMons ;
    Context context;
    public DatMonAdapter(Context context, List<DatMon> datMons){
        this.datMons = datMons;
        this.context = context;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView nameTextView, priceTextView, amountTextView;

        public  ViewHolder(View itemView){
            super(itemView);
            nameTextView =itemView.findViewById(R.id.datmon_name);
            priceTextView =itemView.findViewById(R.id.datmon_price);
            amountTextView =itemView.findViewById(R.id.datmon_amount);
        }
    }

    @NonNull
    @Override
    public DatMonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_giohang,parent,false);
        DatMonAdapter.ViewHolder holder = new DatMonAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DatMonAdapter.ViewHolder holder, int position) {
        DatMon data = datMons.get(position);
        holder.nameTextView.setText(data.getTenMonAn());
        holder.priceTextView.setText((int) data.getGiatien());
        holder.amountTextView.setText(data.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
