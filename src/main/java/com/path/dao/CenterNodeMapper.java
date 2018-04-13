package com.path.dao;

import com.path.model.CenterNode;
import com.path.model.CenterNodeKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterNodeMapper {
    int deleteByPrimaryKey(CenterNodeKey key);

    int insert(CenterNode record);

    int insertSelective(CenterNode record);

    CenterNode selectByPrimaryKey(CenterNodeKey key);

    int updateByPrimaryKeySelective(CenterNode record);

    int updateByPrimaryKey(CenterNode record);

    boolean insertAdvance(List<CenterNode> list);

    boolean updateAdvance(List<CenterNode> list);

    List<CenterNode> selectAllCenterNodeAddress(String questionId);

}