package com.path.service.route;

import com.path.model.Distance;
import com.path.model.Route;
import com.path.model.RouteKey;
import com.path.model.RouteTemp;

import java.util.List;
import java.util.TreeMap;

/**
 * @author demo
 */
public interface RouteService {
    int deleteByPrimaryKey(RouteKey key);

    int insert(Route record ,String questionId);

    int insertSelective(Route record);

    Route selectByPrimaryKey(RouteKey key);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);
    boolean updateAdvance(List<Route> list);
    List<Route> selectAllCenterNodeAddress(String questionId);
    int insertAdvance(List<Route> list);
    Route calculateTimeAndDistance(List<String> list,int id);

    List<Route> findMinDistanceForFour();
    List<Route> findMinTimeForFour();

    List<Route> findByFid(int postData);

    int geneticAlgorithm(String questionId);
}
