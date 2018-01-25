package com.path.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

}