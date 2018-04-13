package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class FinalSolution {
    /**
     * 解决方案编号
     */
    private Integer fId;
    /**
     * 问题编号
     */
    private Integer qId = 1;

}