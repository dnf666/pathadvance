package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceNode extends ServiceNodeKey {
    private String sName;

    private String sAddress;

    private Integer sType;

    private String sLongitude;

    private String sLatitude;

    private Float sBaidulongitude;

    private Float sBaidulatitude;

    private Double sQuatity;

    private String sRem;

//    public String getsName() {
//        return sName;
//    }
//
//    public void setsName(String sName) {
//        this.sName = sName == null ? null : sName.trim();
//    }
//
//    public String getsAddress() {
//        return sAddress;
//    }
//
//    public void setsAddress(String sAddress) {
//        this.sAddress = sAddress == null ? null : sAddress.trim();
//    }
//
//    public Integer getsType() {
//        return sType;
//    }
//
//    public void setsType(Integer sType) {
//        this.sType = sType;
//    }
//
//    public String getsLongitude() {
//        return sLongitude;
//    }
//
//    public void setsLongitude(String sLongitude) {
//        this.sLongitude = sLongitude == null ? null : sLongitude.trim();
//    }
//
//    public String getsLatitude() {
//        return sLatitude;
//    }
//
//    public void setsLatitude(String sLatitude) {
//        this.sLatitude = sLatitude == null ? null : sLatitude.trim();
//    }
//
//    public Float getsBaidulongitude() {
//        return sBaidulongitude;
//    }
//
//    public void setsBaidulongitude(Float sBaidulongitude) {
//        this.sBaidulongitude = sBaidulongitude;
//    }
//
//    public Float getsBaidulatitude() {
//        return sBaidulatitude;
//    }
//
//    public void setsBaidulatitude(Float sBaidulatitude) {
//        this.sBaidulatitude = sBaidulatitude;
//    }
//
//    public Float getsQuatity() {
//        return sQuatity;
//    }
//
//    public void setsQuatity(Float sQuatity) {
//        this.sQuatity = sQuatity;
//    }
//
//    public String getsRem() {
//        return sRem;
//    }
//
//    public void setsRem(String sRem) {
//        this.sRem = sRem == null ? null : sRem.trim();
//    }
}