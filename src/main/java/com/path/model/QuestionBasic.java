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
    private Integer qId;

    private String qName;

    private String qDescript;

    private String qRem1;

    private String qRem2;
}