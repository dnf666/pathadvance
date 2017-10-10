package com.pms.model.blog;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {
    private Integer id;//唯一识别号，递增

    private String title;//题目

    private String createBy;//作者

    private Boolean delFlag;//是否被删除了

    private Boolean isPrivate;//是否私有

}