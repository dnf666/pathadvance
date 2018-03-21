package com.path.service.centernode;

import com.path.model.CenterNode;
import com.path.model.CenterNodeKey;

import java.util.List;

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
    boolean updateAdvance(List<CenterNode> list);
    List<CenterNode> selectAllCenterNodeAddress(String questionId);

}
