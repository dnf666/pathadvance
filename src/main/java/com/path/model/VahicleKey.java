package com.path.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VahicleKey {
    /**
     * 问题编号
     */
    private Integer vId =1;
    /**
     * 汽车编号
     */
    private Integer vNum;

}