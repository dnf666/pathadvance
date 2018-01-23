package com.path.dao;

import com.path.model.FinalSolution;

public interface FinalSolutionMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(FinalSolution record);

    int insertSelective(FinalSolution record);

    FinalSolution selectByPrimaryKey(Integer fId);

    int updateByPrimaryKeySelective(FinalSolution record);

    int updateByPrimaryKey(FinalSolution record);
}