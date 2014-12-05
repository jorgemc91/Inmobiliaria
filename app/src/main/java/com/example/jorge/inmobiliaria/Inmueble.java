package com.example.jorge.inmobiliaria;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Inmueble<ArrayList> implements Serializable, Parcelable{
    private String idInmueble, direccion, numcalle, precio, tipo, desc, img;

    public Inmueble(String idInmueble, String direccion, String numcalle, String precio, String tipo, String desc, String img) {
        this.idInmueble = idInmueble;
        this.direccion = direccion;
        this.numcalle = numcalle;
        this.precio = precio;
        this.tipo = tipo;
        this.desc = desc;
        this.img = img;
    }

    public Inmueble(){}

    public String getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumcalle() {
        return numcalle;
    }

    public void setNumcalle(String numcalle) {
        this.numcalle = numcalle;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.idInmueble);
        parcel.writeString(this.direccion);
        parcel.writeString(this.numcalle);
        parcel.writeString(this.precio);
        parcel.writeString(this.tipo);
        parcel.writeString(this.desc);
        parcel.writeString(this.img);
    }

    public static final Parcelable.Creator<Inmueble> CREATOR = new Parcelable.Creator<Inmueble>(){

        @Override
        public Inmueble createFromParcel(Parcel parcel) {
            String idInmueble = parcel.readString();
            String direccion = parcel.readString();
            String numcalle = parcel.readString();
            String precio = parcel.readString();
            String tipo = parcel.readString();
            String desc = parcel.readString();
            String img = parcel.readString();
            return new Inmueble(idInmueble,direccion,numcalle,precio,tipo,desc,img);
        }

        @Override
        public Inmueble[] newArray(int i) {
            return new Inmueble[0];
        }
    };
}
