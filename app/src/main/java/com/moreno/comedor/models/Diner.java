package com.moreno.comedor.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.moreno.comedor.utils.Utilities;

public class Diner implements Parcelable {

    private Integer id;
    private String names;
    private String lastNames;
    private boolean male;
    private String dni;
    private String phone;
    private boolean active;
    private String nameUser;
    private String pasword;
    public Diner(){

    }
    protected Diner(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        names = in.readString();
        lastNames = in.readString();
        male = in.readByte() != 0;
        dni = in.readString();
        phone = in.readString();
        active = in.readByte() != 0;
        nameUser = in.readString();
        pasword = in.readString();
    }

    public static final Creator<Diner> CREATOR = new Creator<Diner>() {
        @Override
        public Diner createFromParcel(Parcel in) {
            return new Diner(in);
        }

        @Override
        public Diner[] newArray(int size) {
            return new Diner[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPasword() {
        return Utilities.desencriptar(pasword);
    }

    public void setPasword(String pasword) {
        this.pasword = Utilities.encriptar(pasword);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSex(){
        return isMale()?"MASCULINO":"FEMENINO";
    }

    public String getStade(){
        return isActive()?"SI":"NO";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(names);
        dest.writeString(lastNames);
        dest.writeByte((byte) (male ? 1 : 0));
        dest.writeString(dni);
        dest.writeString(phone);
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeString(nameUser);
        dest.writeString(pasword);
    }
}
