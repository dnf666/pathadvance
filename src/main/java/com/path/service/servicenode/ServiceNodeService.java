package com.path.service.servicenode;

import com.path.model.ServiceNode;
import com.path.model.ServiceNodeKey;

import javax.xml.ws.Service;
import java.util.List;

/**
 * create by 戴哥 on 2018/2/7
 */
public interface ServiceNodeService {
    int deleteByPrimaryKey(ServiceNodeKey key);

    int insert(ServiceNode record);

    int insertSelective(ServiceNode record);

    ServiceNode selectByPrimaryKey(ServiceNodeKey key);

    int updateByPrimaryKeySelective(ServiceNode record);

    int updateByPrimaryKey(ServiceNode record);
    boolean updateAdvance(List<ServiceNode> list);
    List<ServiceNode> selectAllServiceNodeAddress(String questionId);

}
