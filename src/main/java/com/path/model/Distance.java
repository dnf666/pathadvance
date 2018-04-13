package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Distance extends DistanceKey {
    /**
     * 与问题编号一致
     */
    private Integer dId = 1;
    /**
     * 出发点编号
     */
    private String startId;
    /**
     * 终点编号
     */
    private String endId;
    /**
     * 出发点地址
     */
    private String startAddress;
    /**
     * 终点地址
     */
    private String endAddress;
    /**
     * 两点之间距离
     */
    private Integer standardDis;
    /**
     * 两点需要的时间
     */
    private Integer standardTime;

}