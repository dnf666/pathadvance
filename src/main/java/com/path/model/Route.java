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
    private String route;

    private Double totalTime;

    private Double totalDis;


}