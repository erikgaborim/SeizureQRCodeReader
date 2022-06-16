package com.example.codescanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Apreensao implements Parcelable {
    private Long id;

    private Date data;

    private String descricao;

    private List<Produto> itens;

    public String[] nomeItens;

    protected Apreensao(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        descricao = in.readString();
    }

    public static final Creator<Apreensao> CREATOR = new Creator<Apreensao>() {
        @Override
        public Apreensao createFromParcel(Parcel in) {
            return new Apreensao(in);
        }

        @Override
        public Apreensao[] newArray(int size) {
            return new Apreensao[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String[] getNomeItens() {
        return nomeItens;
    }

    public void setNomeItens(String[] nomeItens) {
        this.nomeItens = nomeItens;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(descricao);
    }
}
