package com.example.foodypj.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DatMon implements Parcelable {
    String tenMonAn;
    int soLuong;
    long giatien;
    List<DatMon> datMons;

    public List<DatMon> getDatMons() {
        return datMons;
    }

    public void setDatMons(List<DatMon> datMons) {
        this.datMons = datMons;
    }

    public DatMon(){

    }
    protected DatMon(Parcel in) {
        tenMonAn = in.readString();
        soLuong = in.readInt();
        giatien = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tenMonAn);
        dest.writeInt(soLuong);
        dest.writeLong(giatien);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DatMon> CREATOR = new Creator<DatMon>() {
        @Override
        public DatMon createFromParcel(Parcel in) {
            return new DatMon(in);
        }

        @Override
        public DatMon[] newArray(int size) {
            return new DatMon[size];
        }
    };

    public long getGiatien() {
        return giatien;
    }

    public void setGiatien(long giatien) {
        this.giatien = giatien;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }


}
