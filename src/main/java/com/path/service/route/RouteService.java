package com.path.service.route;

import com.path.model.Route;
import com.path.model.RouteKey;

import java.util.List;

/**
 * @author demo
 */
public interface RouteService {
    int deleteByPrimaryKey(RouteKey key);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(RouteKey key);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);
    boolean updateAdvance(List<Route> list);
    List<Route> selectAllCenterNodeAddress(String questionId);
    int insertAdvance(List<Route> list);
}
