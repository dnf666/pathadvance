package com.path.dao;

import com.path.model.QuestionBasic;

public interface QuestionBasicMapper {
    int deleteByPrimaryKey(Integer qId);

    int insert(QuestionBasic record);

    int insertSelective(QuestionBasic record);

    QuestionBasic selectByPrimaryKey(Integer qId);

    int updateByPrimaryKeySelective(QuestionBasic record);

    int updateByPrimaryKey(QuestionBasic record);
}