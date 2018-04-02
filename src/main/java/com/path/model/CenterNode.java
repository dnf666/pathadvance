package com.path.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CenterNode extends CenterNodeKey {
    /**
     * 中心点名字
     */
    private String cName;
    /**
     * 中心点地址
     */
    private String cAddress;
    /**
     * 中心点类型，0代表自动取款机，1代表分行，2代表大银行
     */
    private Integer cType;
    /**
     * 经度
     */
    private String cLongitude;
    /**
     * 纬度
     */
    private String cLatitude;

    /**
     * 百度格式的经度
     */
    private Float cBaidulongitude;

    /**
     * 百度格式的纬度
     */
    private Float cBaidulatitude;

    /**
     * 存款存量
     */
    private Double cQuatity;
    /**
     * 保留字段，留作扩展用。虽然我觉得不可能会有用
     */
    private String cRem;

}