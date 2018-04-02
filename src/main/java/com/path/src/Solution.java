package com.path.src;

import java.util.ArrayList;
import java.util.List;

public class Solution implements Comparable{
    private float probability;
    private List<List<Integer>> routes = new ArrayList<>();

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

    public List<List<Integer>> getRoutes() {
        return routes;
    }

    public void setRoutes(List<List<Integer>> routes) {
        this.routes = routes;
    }

    @Override
    public int compareTo(Object o) {
        Solution solution = (Solution)o;
        if (this.probability > solution.getProbability())
            return 1;
        else if(this.probability < solution.getProbability())
            return -1;
        else
            return 0;

    }
}
