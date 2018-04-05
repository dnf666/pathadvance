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
@ToString
public class RouteTemp {

    private List<List> route;
    public RouteTemp() {
    }

    public RouteTemp(List<List> route) {
        this.route = route;
    }

    public RouteTemp(int count) {
        route = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            route.add(new ArrayList());
        }

    }
}
