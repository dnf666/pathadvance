package com.path.service.impl;

import com.path.dao.CenterNodeMapper;
import com.path.model.CenterNode;
import com.path.model.CenterNodeKey;
import com.path.service.CenterNodeService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author demo
 */
@Service
public class CenterNodeServiceImpl implements CenterNodeService{
    @Resource
    private CenterNodeMapper centerNodeMapper;
    @Override
    public int deleteByPrimaryKey(CenterNodeKey key) {
        int result= centerNodeMapper.deleteByPrimaryKey(key);
        return result;
    }

    @Override
    public int insert(CenterNode record) {
        return 0;
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
}
