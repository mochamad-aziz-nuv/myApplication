package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BarangView implements Parcelable {
    Integer idBarang;
    String kdBarang;
    String namaBarang;
    String stok;

    public static final Creator<BarangView> CREATOR = new Creator<BarangView>() {
        @Override
        public BarangView createFromParcel(Parcel in) {
            return new BarangView(in);
        }

        @Override
        public BarangView[] newArray(int size) {
            return new BarangView[size];
        }
    };

    public Integer getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Integer idBarang) {
        this.idBarang = idBarang;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        stok = stok;
    }

    public BarangView() {}

    private BarangView(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        this.idBarang = Integer.valueOf(data[0]);
        this.kdBarang = data[1];
        this.namaBarang = data[2];
        this.stok = data[3];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {String.valueOf(this.idBarang), this.kdBarang,
                this.namaBarang, this.stok});
    }
}
