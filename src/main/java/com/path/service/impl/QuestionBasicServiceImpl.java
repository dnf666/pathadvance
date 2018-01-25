package com.path.service.impl;

import com.path.dao.QuestionBasicMapper;
import com.path.model.QuestionBasic;

/**
 * @author demo
 */
public class QuestionBasicServiceImpl implements QuestionBasicMapper {

    @Override
    public int deleteByPrimaryKey(Integer qId) {
        return 0;
    }

    @Override
    public int insert(QuestionBasic record) {
        return 0;
    }

    @Override
    public int insertSelective(QuestionBasic record) {
        return 0;
    }

    @Override
    public QuestionBasic selectByPrimaryKey(Integer qId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(QuestionBasic record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(QuestionBasic record) {
        return 0;
    }
}
