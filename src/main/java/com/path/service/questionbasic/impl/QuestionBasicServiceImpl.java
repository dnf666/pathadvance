package com.path.service.questionbasic.impl;

import com.path.dao.QuestionBasicMapper;
import com.path.model.QuestionBasic;
import com.path.service.questionbasic.QuestionBasicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author demo
 */
@Service
public class QuestionBasicServiceImpl implements QuestionBasicService {
@Resource
private QuestionBasicMapper questionBasicMapper;
    @Override
    public int deleteByPrimaryKey(Integer qId) {
        return 0;
    }

    @Override
    public int insert(QuestionBasic record) {
        int i = questionBasicMapper.insert(record);
        return i;
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
