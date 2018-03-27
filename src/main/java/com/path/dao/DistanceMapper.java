package com.path.dao;

import com.path.model.Distance;
import com.path.model.DistanceKey;

import java.util.List;

public interface DistanceMapper {
    int deleteByPrimaryKey(DistanceKey key);

    int insert(Distance record);

    int insertSelective(Distance record);

    Distance selectByPrimaryKey(DistanceKey key);

    int updateByPrimaryKeySelective(Distance record);

    int updateByPrimaryKey(Distance record);

    int insertAdvance(List<Distance> list);

    List<Distance> produceAllWay(Integer questionId);

    int checkRemainCount(int i);

    DistanceKey selectIfNull(Integer questionId);

    Distance selectNullNode(Integer questionId);
}