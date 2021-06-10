package com.jcp.domain;

import java.io.Serializable;

public class User{
    private int ID;
    private String password;
    private int type;
    private String name;
    private String unit;
    private long telephone;
    private int can_borrow;

    public User(int ID, String password, int type, String name, String unit, long telephone, int can_borrow) {
        this.ID = ID;
        this.password = password;
        this.type = type;
        this.name = name;
        this.unit = unit;
        this.telephone = telephone;
        this.can_borrow = can_borrow;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public int getCan_borrow() {
        return can_borrow;
    }

    public void setCan_borrow(int can_borrow) {
        this.can_borrow = can_borrow;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", telephone=" + telephone +
                ", can_borrow=" + can_borrow +
                '}';
    }
}
