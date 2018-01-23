package com.path.model;

public class CenterNode extends CenterNodeKey {
    private String cName;

    private String cAddress;

    private Integer cType;

    private String cLongitude;

    private String cLatitude;

    private Float cBaidulongitude;

    private Float cBaidulatitude;

    private Double cQuatity;

    private String cRem;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress == null ? null : cAddress.trim();
    }

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public String getcLongitude() {
        return cLongitude;
    }

    public void setcLongitude(String cLongitude) {
        this.cLongitude = cLongitude == null ? null : cLongitude.trim();
    }

    public String getcLatitude() {
        return cLatitude;
    }

    public void setcLatitude(String cLatitude) {
        this.cLatitude = cLatitude == null ? null : cLatitude.trim();
    }

    public Float getcBaidulongitude() {
        return cBaidulongitude;
    }

    public void setcBaidulongitude(Float cBaidulongitude) {
        this.cBaidulongitude = cBaidulongitude;
    }

    public Float getcBaidulatitude() {
        return cBaidulatitude;
    }

    public void setcBaidulatitude(Float cBaidulatitude) {
        this.cBaidulatitude = cBaidulatitude;
    }

    public Double getcQuatity() {
        return cQuatity;
    }

    public void setcQuatity(Double cQuatity) {
        this.cQuatity = cQuatity;
    }

    public String getcRem() {
        return cRem;
    }

    public void setcRem(String cRem) {
        this.cRem = cRem == null ? null : cRem.trim();
    }
}