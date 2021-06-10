package com.jcp.domain;

import java.io.Serializable;

public class Circulation implements Serializable {

    private int serialNo;
    private String name;
    private int no;
    private int year;
    private int month;
    private int day;
    private int type;
    private int operator;

    public Circulation(int serialNo, String name, int no, int year, int month, int day, int type, int operator) {
        this.serialNo = serialNo;
        this.name = name;
        this.no = no;
        this.year = year;
        this.month = month;
        this.day = day;
        this.type = type;
        this.operator = operator;
    }
    public Circulation(String name, int no, int year, int month, int day, int type, int operator) {
        this.name = name;
        this.no = no;
        this.year = year;
        this.month = month;
        this.day = day;
        this.type = type;
        this.operator = operator;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
