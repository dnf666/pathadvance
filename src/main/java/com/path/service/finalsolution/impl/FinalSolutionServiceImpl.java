package com.path.service.finalsolution.impl;

import com.path.dao.FinalSolutionMapper;
import com.path.model.FinalSolution;
import com.path.service.finalsolution.FinalSolutionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author demo
 */
@Service
public class FinalSolutionServiceImpl implements FinalSolutionService {
    @Resource
    private FinalSolutionMapper finalSolutionMapper;

    @Override
    public int deleteByPrimaryKey(FinalSolution key) {
        return 0;
    }

    @Override
    public int insert(FinalSolution record) {
        finalSolutionMapper.insert(record);
        return 0;
    }

    @Override
    public int insertSelective(FinalSolution record) {
        return 0;
    }

    @Override
    public FinalSolution selectByPrimaryKey(FinalSolution key) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(FinalSolution record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(FinalSolution record) {
        return 0;
    }

    @Override
    public boolean updateAdvance(List<FinalSolution> list) {
        return false;
    }

    @Override
    public int insertAdvance(List<FinalSolution> list) {
        return 0;
    }
}
