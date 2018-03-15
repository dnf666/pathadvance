package com.path.service.distance;

import com.path.model.Distance;
import com.path.model.DistanceKey;

import java.util.List;

/**
 * @author demo
 */
public interface DistanceService {
    int deleteByPrimaryKey(DistanceKey key);

    int insert(Distance record);

    int insertSelective(Distance record);

    Distance selectByPrimaryKey(DistanceKey key);

    int updateByPrimaryKeySelective(Distance record);

    int updateByPrimaryKey(Distance record);
    boolean updateAdvance(List<Distance> list);
    List<Distance> selectAllCenterNodeAddress(String questionId);
    int insertAdvance(List<Distance> list);
}
