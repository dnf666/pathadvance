package com.path.service.route.impl;

import com.path.dao.DistanceMapper;
import com.path.dao.RouteMapper;
import com.path.model.Distance;
import com.path.model.DistanceKey;
import com.path.model.Route;
import com.path.model.RouteKey;
import com.path.service.route.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author demo
 */
@Service
public class RouteServiceImpl implements RouteService{
    @Resource
    private RouteMapper routeMapper;
    @Resource
    private DistanceMapper distanceMapper;

    @Override
    public int deleteByPrimaryKey(RouteKey key) {
        return 0;
    }

    @Override
    public int insert(Route record) {
        return routeMapper.insert(record);
    }

    @Override
    public int insertSelective(Route record) {
        return 0;
    }

    @Override
    public Route selectByPrimaryKey(RouteKey key) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Route record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Route record) {
        return 0;
    }

    @Override
    public boolean updateAdvance(List<Route> list) {
        return false;
    }

    @Override
    public List<Route> selectAllCenterNodeAddress(String questionId) {
        return null;
    }

    @Override
    public int insertAdvance(List<Route> list) {
        return 0;
    }


    @Override
    public Route calculateTimeAndDistance(List<String> list, int id) {
        double totalTime = 0;
        double totalDis = 0;
        DistanceKey distanceKey = new Distance();
        Route route = new Route();
        if (list.isEmpty()){
            return null;
        }else {
            for (int i = 0; i < list.size() - 1; i++) {
                String startId = list.get(i);
                String endId = list.get(i+1);
                distanceKey.setStartId(startId);
                distanceKey.setEndId(endId);
                distanceKey.setDId(id);
                Distance distance = distanceMapper.selectByPrimaryKey(distanceKey);
               totalDis  = totalDis + distance.getStandardDis();
               totalTime = totalTime + distance.getStandardTime();
            }
            route.setTotalDis(totalDis);
            route.setTotalTime(totalTime);

            return route;
        }
    }

    @Override
    public List<Route> findMinDistanceForFour() {
       List<Route> list =  routeMapper.findMinDistanceForFour();
       return list;
    }

    @Override
    public List<Route> findMinTimeForFour() {
        List<Route> list =  routeMapper.findMinTimeForFour();
        return list;
    }

    @Override
    public List<Route> findByFid(int postData) {

        return routeMapper.findRouteByFid(postData);
    }

}
