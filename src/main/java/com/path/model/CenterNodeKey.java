package com.path.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CenterNodeKey {
    /**
     * 和问题id一致
     */
    private Integer cId = 1;
    /**
     * 中心点编号
     */
    private String cNum;


}