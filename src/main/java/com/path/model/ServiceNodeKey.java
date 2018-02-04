package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceNodeKey {
    private Integer sId;

    private String sNum;

//    public Integer getsId() {
//        return sId;
//    }
//
//    public void setsId(Integer sId) {
//        this.sId = sId;
//    }
//
//    public String getsNum() {
//        return sNum;
//    }
//
//    public void setsNum(String sNum) {
//        this.sNum = sNum == null ? null : sNum.trim();
//    }
}