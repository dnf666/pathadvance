package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionBasic {
    /**
     * 问题编号
     */
    private Integer qId ;
    /**
     * 问题名字
     */
    private String qName;
    /**
     * 问题描述
     */
    private String qDescript;
    /**
     * 扩展用字段 下同
     */
    private String qRem1;

    private String qRem2;
}