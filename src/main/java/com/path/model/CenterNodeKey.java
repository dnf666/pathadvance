package com.path.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CenterNodeKey {
    private Integer cId;

    private String cNum;

//    public Integer getcId() {
//        return cId;
//    }
//
//    public void setcId(Integer cId) {
//        this.cId = cId;
//    }
//
//    public String getcNum() {
//        return cNum;
//    }
//
//    public void setcNum(String cNum) {
//        this.cNum = cNum == null ? null : cNum.trim();
//    }
}