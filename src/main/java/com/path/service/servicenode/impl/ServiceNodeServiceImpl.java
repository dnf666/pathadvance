package com.path.service.servicenode.impl;

import com.path.dao.ServiceNodeMapper;
import com.path.model.ServiceNode;
import com.path.model.ServiceNodeKey;
import com.path.model.ServiceNode;
import com.path.model.ServiceNodeKey;
import com.path.service.servicenode.ServiceNodeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create by 戴哥 on 2018/2/7
 */
public class ServiceNodeServiceImpl implements ServiceNodeService {
    @Resource
    private ServiceNodeMapper serviceNodeMapper;
    @Override
    public int deleteByPrimaryKey(ServiceNodeKey key) {
        return 0;
    }

    @Override
    public int insert(ServiceNode record) {
        return serviceNodeMapper.insert(record);
    }

    @Override
    public int insertSelective(ServiceNode record) {
        return 0;
    }

    @Override
    public ServiceNode selectByPrimaryKey(ServiceNodeKey key) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ServiceNode record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ServiceNode record) {
        return 0;
    }

    @Override
    public boolean updateAdvance(List<ServiceNode> list) {
        List<ServiceNode> list1 = list.stream().filter(e->e.getSId()!=null).collect(Collectors.toList());
        List<ServiceNode> list2 = list1.stream().filter(e->e.getSNum()!=null).collect(Collectors.toList());
        boolean result = serviceNodeMapper.updateAdvance(list2);
        return result;
    }

    @Override
    public List<String> selectAllServiceNodeAddress(String questionId) {
        List<String> ServiceNodes = serviceNodeMapper.selectAllServiceNodeAddress(questionId);
        return ServiceNodes;
    }
}
