package com.path.service.distance.impl;

import com.path.dao.DistanceMapper;
import com.path.model.Distance;
import com.path.model.DistanceKey;
import com.path.service.distance.DistanceService;
import org.springframework.stereotype.Service;
import sun.security.x509.DistributionPointName;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author demo
 */
@Service
public class DistanceServiceImpl implements DistanceService{
    @Resource
    private DistanceMapper distanceMapper;
    @Override
    public int deleteByPrimaryKey(DistanceKey key) {
        return 0;
    }

    @Override
    public int insert(Distance record) {
                return  distanceMapper.insert(record);
    }

    @Override
    public int insertSelective(Distance record) {
        return distanceMapper.insertSelective(record);
    }

    @Override
    public Distance selectByPrimaryKey(DistanceKey key) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Distance record) {
        return  distanceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Distance record) {
        return 0;
    }

    @Override
    public boolean updateAdvance(List<Distance> list) {
        return false;
    }

    @Override
    public List<Distance> selectAllCenterNodeAddress(String questionId) {
        return null;
    }

    @Override
    public int insertAdvance(List<Distance> list) {
        return distanceMapper.insertAdvance(list);
    }

    @Override
    public DistanceKey selectIfNull(Integer questionId) {
        return distanceMapper.selectIfNull(questionId);
    }

    @Override
    public Distance selectNullNode(Integer questionId) {
         return distanceMapper.selectNullNode(questionId);
    }

    @Override
    public List<String> produceAllWay(Integer questionId) {

        return distanceMapper.produceAllWay(questionId);
    }

    @Override
    public int checkRemainCount(int i) {
         int remainCount = distanceMapper.checkRemainCount(i);
         return remainCount;
    }
}
