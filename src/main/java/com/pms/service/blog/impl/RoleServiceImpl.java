package com.pms.service.blog.impl;

import com.pms.dao.blog.RoleMapper;
import com.pms.model.blog.Role;
import com.pms.service.blog.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    public int insertRole(Role role) {
        return 0;
    }

    public int deleteRoleByConbinationKey(Role role) {
        return 0;
    }

    public Role selectByConbinationKey(Role role) {
        return null;
    }
}
