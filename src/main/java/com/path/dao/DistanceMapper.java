package com.path.dao;

import com.path.model.Distance;
import com.path.model.DistanceKey;

public interface DistanceMapper {
    int deleteByPrimaryKey(DistanceKey key);

    int insert(Distance record);

    int insertSelective(Distance record);

    Distance selectByPrimaryKey(DistanceKey key);

    int updateByPrimaryKeySelective(Distance record);

    int updateByPrimaryKey(Distance record);
}