package com.example.foodypj.src.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodypj.Model.BinhLuanModel;
import com.example.foodypj.Model.QuanAnModel;
import com.example.foodypj.R;
import com.example.foodypj.src.View.ChiTietQuanAnActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecycleOdau extends RecyclerView.Adapter<AdapterRecycleOdau.ViewHolder> {

    List<QuanAnModel> quanAnModelList;
    int resource;
    Context context;
    public AdapterRecycleOdau(Context context,List<QuanAnModel> quanAnModelList, int resource){
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
        this.context = context;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtTenQuanAnOdau, txtTieuDeBinhLuan2,
                txtTieuDeBinhLuan, txtNoiDungBinhLuan, txtNoiDungBinhLuan2,
                txtChamDiemBinhLuan, txtChamDiemBinhLuan2, txtTongBinhLuan,
                txtTongHinhAnhBinhLuan, txtDiemTrungBinhQuanAN;
        CircleImageView cicleImgUser, cicleImgUser2;
        Button btnDatMonOdau;
        ImageView imgHinhQuanAnOdau;
        LinearLayout containerBinhLuan,containerBinhLuan2;

        public  ViewHolder(View itemView){
            super(itemView);
            txtTenQuanAnOdau = (TextView) itemView.findViewById(R.id.txtTenQuanAnOdau);
            btnDatMonOdau = (Button) itemView.findViewById(R.id.btnDatMonOdau);
            imgHinhQuanAnOdau =(ImageView) itemView.findViewById(R.id.imgHinhQuanAnOdau);
            txtNoiDungBinhLuan = itemView.findViewById(R.id.txtNoiDungBinhLuan);
            txtNoiDungBinhLuan2 = itemView.findViewById(R.id.txtNoiDungBinhLuan2);
            txtTieuDeBinhLuan = itemView.findViewById(R.id.txtTieuDeBinhLuan);
            txtTieuDeBinhLuan2 = itemView.findViewById(R.id.txtTieuDeBinhLuan2);
            cicleImgUser = (CircleImageView) itemView.findViewById(R.id.cicleImgUser);
            cicleImgUser2 = (CircleImageView) itemView.findViewById(R.id.cicleImgUser2);
            containerBinhLuan = itemView.findViewById(R.id.containerBinhLuan1);
            containerBinhLuan2 = itemView.findViewById(R.id.containerBinhLuan2);
            txtChamDiemBinhLuan = itemView.findViewById(R.id.txtChamdiem);
            txtChamDiemBinhLuan2 = itemView.findViewById(R.id.txtChamdiem2);
            txtTongBinhLuan = itemView.findViewById(R.id.txtTongBinhLuan);
            txtTongHinhAnhBinhLuan = itemView.findViewById(R.id.txtTongHinhAnhBinhLuan);
            txtDiemTrungBinhQuanAN = itemView.findViewById(R.id.txtDiemTrungBinh);
        }
    }

    @NonNull
    @Override
    public AdapterRecycleOdau.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleOdau.ViewHolder holder, int position) {
        QuanAnModel quanAnModel = quanAnModelList.get(position);
        holder.txtTenQuanAnOdau.setText(quanAnModel.getTenquanan());
        if(quanAnModel.isGiaohang() == true){
            holder.btnDatMonOdau.setVisibility(View.VISIBLE);
        }
        if(quanAnModel.getBitmapList().size() > 0){
            holder.imgHinhQuanAnOdau.setImageBitmap(quanAnModel.getBitmapList().get(0));

            /*StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhquanan().get(0));
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinh User.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                holder.imgHinhQuanAnOdau.setImageBitmap(bitmap);
            });*/
        }

        if(quanAnModel.getBinhLuanModelList().size() > 0 ){
            BinhLuanModel binhLuanModel = quanAnModel.getBinhLuanModelList().get(0);
            holder.txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
            holder.txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
            setHinhAnhBinhLuan(holder.cicleImgUser,binhLuanModel.getThanhVienModel().getHinhanh());
            holder.txtChamDiemBinhLuan.setText(binhLuanModel.getChamdiem() + "");
            if(quanAnModel.getBinhLuanModelList().size() >= 2 ){
                BinhLuanModel binhLuanModel2 = quanAnModel.getBinhLuanModelList().get(1);
                holder.txtTieuDeBinhLuan2.setText(binhLuanModel2.getTieude());
                holder.txtNoiDungBinhLuan2.setText(binhLuanModel2.getNoidung());
                setHinhAnhBinhLuan(holder.cicleImgUser2,binhLuanModel2.getThanhVienModel().getHinhanh());
                holder.txtChamDiemBinhLuan2.setText(binhLuanModel2.getChamdiem() + "");
            }
            holder.txtTongBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");

            int tongSoBinhLuan = 0;
            double tongdiem = 0;
            for(BinhLuanModel binhLuanModel1 : quanAnModel.getBinhLuanModelList()){
                tongSoBinhLuan += binhLuanModel1.getHinhAnhList().size();
                tongdiem += binhLuanModel1.getChamdiem();
            }

            double diemtrungbinh  = tongdiem/quanAnModel.getBinhLuanModelList().size();
            holder.txtDiemTrungBinhQuanAN.setText(String.format("%.1f",diemtrungbinh)+"");
            if(tongSoBinhLuan >0){
                holder.txtTongHinhAnhBinhLuan.setText(tongSoBinhLuan + "");
            }

        }else {
            holder.containerBinhLuan.setVisibility(View.GONE);
            holder.containerBinhLuan2.setVisibility(View.GONE);
        }

        holder.txtTenQuanAnOdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChitietquanan = new Intent(context, ChiTietQuanAnActivity.class);
                iChitietquanan.putExtra("quanan", quanAnModel);
                context.startActivity(iChitietquanan);
            }
        });
    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh){

        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            circleImageView.setImageBitmap(bitmap);
        });

    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }

}