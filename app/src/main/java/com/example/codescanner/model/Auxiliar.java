package com.example.codescanner.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Auxiliar implements Parcelable {
    private String[] nomeItens;
    private String data;

    public Auxiliar(Parcel in) {
        nomeItens = in.createStringArray();
        data = in.readString();
    }

    public static final Creator<Auxiliar> CREATOR = new Creator<Auxiliar>() {
        @Override
        public Auxiliar createFromParcel(Parcel in) {
            return new Auxiliar(in);
        }

        @Override
        public Auxiliar[] newArray(int size) {
            return new Auxiliar[size];
        }
    };

    public Auxiliar() {

    }

    public String[] getNomeItens() {
        return nomeItens;
    }

    public void setNomeItens(String[] nomeItens) {
        this.nomeItens = nomeItens;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(nomeItens);
        parcel.writeString(data);
    }
}
