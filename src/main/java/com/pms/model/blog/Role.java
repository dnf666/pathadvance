package com.pms.model.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    private String userId;//访问者id
    private int roleId;//权限的表示数字
    private String masterId;//被访问者id
}
