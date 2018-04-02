package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author demo
 */
@Data
@AllArgsConstructor
@ToString
public class RouteTemp {
    private int id;
    private List<List> route;

    public RouteTemp() {
    }

    public RouteTemp(int count) {
        route = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            route.add(new ArrayList());
        }

    }
}
