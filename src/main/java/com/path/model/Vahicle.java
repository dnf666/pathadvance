package com.path.model;

import java.util.Date;

public class Vahicle extends VahicleKey {
    private String vType;

    private Float vCapacity;

    private Float vOil;

    private Float vPrice;

    private Date vDate;

    private String vRem;

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType == null ? null : vType.trim();
    }

    public Float getvCapacity() {
        return vCapacity;
    }

    public void setvCapacity(Float vCapacity) {
        this.vCapacity = vCapacity;
    }

    public Float getvOil() {
        return vOil;
    }

    public void setvOil(Float vOil) {
        this.vOil = vOil;
    }

    public Float getvPrice() {
        return vPrice;
    }

    public void setvPrice(Float vPrice) {
        this.vPrice = vPrice;
    }

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public String getvRem() {
        return vRem;
    }

    public void setvRem(String vRem) {
        this.vRem = vRem == null ? null : vRem.trim();
    }
}