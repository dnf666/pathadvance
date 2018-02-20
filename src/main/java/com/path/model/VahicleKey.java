package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VahicleKey {
    private Integer vId;

    private Integer vNum;
//
//    public Integer getvId() {
//        return vId;
//    }
//
//    public void setvId(Integer vId) {
//        this.vId = vId;
//    }
//
//    public Integer getvNum() {
//        return vNum;
//    }
//
//    public void setvNum(Integer vNum) {
//        this.vNum = vNum;
//    }
}