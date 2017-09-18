package com.pms.service.blog;

import com.pms.model.blog.Role;

public interface RoleService {
    /**
     * 添加权限
     * @param role
     * @return
     */
   int insertRole(Role role);

    /**
     * 删除权限
     * @param role
     * @return
     */
   int deleteRoleByConbinationKey(Role role);

    /**
     * 查询权限
     * @param role
     * @return
     */
   Role selectByConbinationKey(Role role);

}
