package com.path.service.finalsolution;

import com.path.model.FinalSolution;

import java.util.List;

/**
 * @author demo
 */
public interface FinalSolutionService {
    int deleteByPrimaryKey(FinalSolution key);

    int insert(FinalSolution record);

    int insertSelective(FinalSolution record);

    FinalSolution selectByPrimaryKey(FinalSolution key);

    int updateByPrimaryKeySelective(FinalSolution record);

    int updateByPrimaryKey(FinalSolution record);
    boolean updateAdvance(List<FinalSolution> list);
    int insertAdvance(List<FinalSolution> list);
}
