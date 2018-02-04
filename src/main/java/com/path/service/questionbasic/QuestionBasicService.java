package com.path.service.questionbasic;

import com.path.model.QuestionBasic;

/**
 * @author demo
 */
public interface QuestionBasicService {
    int deleteByPrimaryKey(Integer qId);

    int insert(QuestionBasic record);

    int insertSelective(QuestionBasic record);

    QuestionBasic selectByPrimaryKey(Integer qId);

    int updateByPrimaryKeySelective(QuestionBasic record);

    int updateByPrimaryKey(QuestionBasic record);
}
