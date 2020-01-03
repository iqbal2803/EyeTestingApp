package com.iqbal.eyetesting.ui.History.Model;

/**
 * Created by IQBAL on 22/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HistoryModel implements Parcelable {


    @SerializedName("id_history")
    private String id_history;
    @SerializedName("nama")
    private String nama;
    @SerializedName("umur")
    private String umur;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("tgl_test")
    private String tgl_test;
    @SerializedName("nilai")
    private String nilai;


    public HistoryModel(String id_history, String nama, String umur, String jenis_kelamin, String tgl_test, String nilai) {
        this.id_history = id_history;
        this.nama = nama;
        this.umur = umur;
        this.jenis_kelamin = jenis_kelamin;
        this.tgl_test = tgl_test;
        this.nilai = nilai;
    }

    public HistoryModel(){

    }

    public static final Creator<HistoryModel> CREATOR = new Creator<HistoryModel>() {
        @Override
        public HistoryModel createFromParcel(Parcel in) {
            return new HistoryModel(in);
        }

        @Override
        public HistoryModel[] newArray(int size) {
            return new HistoryModel[size];
        }
    };

    public String getId_history() {
        return id_history;
    }

    public void setId_history(String id_history) {
        this.id_history = id_history;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTgl_test() {
        return tgl_test;
    }

    public void setTgl_test(String tgl_test) {
        this.tgl_test = tgl_test;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_history);
        dest.writeString(this.nama);
        dest.writeString(this.umur);
        dest.writeString(this.jenis_kelamin);
        dest.writeString(this.tgl_test);
        dest.writeString(this.nilai);
    }

    protected HistoryModel(Parcel in) {
        id_history = in.readString();
        nama = in.readString();
        umur = in.readString();
        jenis_kelamin = in.readString();
        tgl_test = in.readString();
        nilai = in.readString();
    }
}
