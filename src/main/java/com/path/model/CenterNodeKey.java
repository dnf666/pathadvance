package com.path.model;

public class CenterNodeKey {
    private Integer cId;

    private String cNum;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcNum() {
        return cNum;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum == null ? null : cNum.trim();
    }
}