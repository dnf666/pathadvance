package com.path.service.centernode.impl;

import com.path.dao.CenterNodeMapper;
import com.path.model.CenterNode;
import com.path.model.CenterNodeKey;
import com.path.service.centernode.CenterNodeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author demo
 */
@Service
public class CenterNodeServiceImpl implements CenterNodeService {
    @Resource
    private CenterNodeMapper centerNodeMapper;
    @Override
    public int deleteByPrimaryKey(CenterNodeKey key) {
        return 0;
    }

    @Override
    public int insert(CenterNode record) {
        return centerNodeMapper.insert(record);
    }

    @Override
    public int insertSelective(CenterNode record) {
        return 0;
    }

    @Override
    public CenterNode selectByPrimaryKey(CenterNodeKey key) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(CenterNode record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CenterNode record) {
        return 0;
    }

    @Override
    public boolean updateAdvance(List<CenterNode> list) {
        List<CenterNode> list1 = list.stream().filter(e->e.getCLatitude()!=null).collect(Collectors.toList());
        List<CenterNode> list2 = list1.stream().filter(e->e.getCLongitude()!=null).collect(Collectors.toList());
        boolean result = centerNodeMapper.updateAdvance(list2);
        return result;
    }

    @Override
    public List<CenterNode> selectAllCenterNodeAddress(String questionId) {
        List<CenterNode> centerNodes = centerNodeMapper.selectAllCenterNodeAddress(questionId);
        return centerNodes;
    }
}
