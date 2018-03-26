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

    /**
     * 选择性添加
     * @param record 记录
     * @return 插入结果
     */
    int insertSelective(Distance record);

    /**
     * 产生每个问题下存储数据库的点之间的路径
     * @param questionId
     * @return
     */
    List<Distance> produceAllWay(Integer questionId);


    /**
     * 查询还剩多少条需要更新的路线
     * @param i 问题id
     * @return 剩下的路线条数
     */
    int checkRemainCount(int i);




    Distance selectByPrimaryKey(DistanceKey key);

    int updateByPrimaryKeySelective(Distance record);

    int updateByPrimaryKey(Distance record);
    boolean updateAdvance(List<Distance> list);
    List<Distance> selectAllCenterNodeAddress(String questionId);
    int insertAdvance(List<Distance> list);


}
