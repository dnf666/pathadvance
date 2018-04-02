package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 这个类和distance的区别。它只包含了主键。主要用于查询。返回的数据用distance
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DistanceKey {
    /**
     * 问题编号
     */
    private Integer dId =1;
    /**
     * 起点编号
     */
    private String startId;
    /**
     * 终点编号
     */
    private String endId;

}