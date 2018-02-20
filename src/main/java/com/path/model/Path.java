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
    private String projectPath;
    private String path;
    private Integer questionId;
}
