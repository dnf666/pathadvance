package com.path.dao;

import com.path.model.Route;
import com.path.model.RouteKey;

public interface RouteMapper {
    int deleteByPrimaryKey(RouteKey key);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(RouteKey key);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);
}