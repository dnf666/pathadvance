package com.path.service;

import com.path.model.CenterNode;
import com.path.model.CenterNodeKey;

/**
 * @author demo
 */
public interface CenterNodeService {
    int deleteByPrimaryKey(CenterNodeKey key);

    int insert(CenterNode record);

    int insertSelective(CenterNode record);

    CenterNode selectByPrimaryKey(CenterNodeKey key);

    int updateByPrimaryKeySelective(CenterNode record);

    int updateByPrimaryKey(CenterNode record);
}
