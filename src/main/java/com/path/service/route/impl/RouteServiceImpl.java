package com.path.service.route.impl;

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
}
