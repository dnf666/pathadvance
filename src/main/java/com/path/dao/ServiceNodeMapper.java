package com.path.dao;

import com.path.model.ServiceNode;
import com.path.model.ServiceNodeKey;

import java.util.List;

public interface ServiceNodeMapper {
    int deleteByPrimaryKey(ServiceNodeKey key);

    int insert(ServiceNode record);

    int insertSelective(ServiceNode record);

    ServiceNode selectByPrimaryKey(ServiceNodeKey key);

    int updateByPrimaryKeySelective(ServiceNode record);

    int updateByPrimaryKey(ServiceNode record);

    boolean insertAdvance(List<ServiceNode> list);
}