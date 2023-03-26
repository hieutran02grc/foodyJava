package com.example.foodypj.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MonAnModel implements Parcelable {
    String mamon;
    long giatien;
    String hinhanh;
    String tenmon;

    public MonAnModel(){

    }

    protected MonAnModel(Parcel in) {
        mamon = in.readString();
        giatien = in.readLong();
        hinhanh = in.readString();
        tenmon = in.readString();
    }

    public static final Creator<MonAnModel> CREATOR = new Creator<MonAnModel>() {
        @Override
        public MonAnModel createFromParcel(Parcel in) {
            return new MonAnModel(in);
        }

        @Override
        public MonAnModel[] newArray(int size) {
            return new MonAnModel[size];
        }
    };

    public String getMamon() {
        return mamon;
    }

    public long getGiatien() {
        return giatien;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setMamon(String mamon) {
        this.mamon = mamon;
    }

    public void setGiatien(long giatien) {
        this.giatien = giatien;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mamon);
        dest.writeLong(giatien);
        dest.writeString(hinhanh);
        dest.writeString(tenmon);
    }
}
