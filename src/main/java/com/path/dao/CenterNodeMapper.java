package com.path.dao;

import com.path.model.CenterNode;
import com.path.model.CenterNodeKey;

public interface CenterNodeMapper {
    int deleteByPrimaryKey(CenterNodeKey key);

    int insert(CenterNode record);

    int insertSelective(CenterNode record);

    CenterNode selectByPrimaryKey(CenterNodeKey key);

    int updateByPrimaryKeySelective(CenterNode record);

    int updateByPrimaryKey(CenterNode record);
}