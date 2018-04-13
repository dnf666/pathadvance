package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RouteKey {
    /**
     * 路线编号,自增的
     */
    private Integer rId;
    /**
     * 解决方案编号
     */
    private Integer fId ;
    /**
     * 车辆编号
     */
    private Integer vId;

}