package com.moreno.comedor.models;

import com.moreno.comedor.utils.Utilities;

public class Diner {

    private Integer id;
    private String names;
    private String lastNames;
    private boolean male;
    private String dni;
    private String phone;
    private boolean active;
    private String nameUser;
    private String pasword;

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

}
