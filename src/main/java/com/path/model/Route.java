package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Route extends RouteKey {
    /**
     * 路线
     */
    private String route;
    /**
     * 总时间
     */
    private Double totalTime;
    /**
     * 总距离
     */
    private Double totalDis;


}