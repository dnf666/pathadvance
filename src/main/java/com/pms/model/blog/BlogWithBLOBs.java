package com.pms.model.blog;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogWithBLOBs extends Blog {
    private String context;//正文

    private String createTime;//创建时间

    private String delTime;//删除时间

}