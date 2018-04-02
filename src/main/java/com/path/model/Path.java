package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author demo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Path {
    /**
     * 项目路径
     */
    private String projectPath;
    /**
     * 取properties文件的路径
     */
    private String path;
    /**
     * 问题编号
     */
    private Integer questionId = 1;
}
