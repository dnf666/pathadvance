package com.pms.service.blog.impl;

import com.pms.dao.blog.RoleMapper;
import com.pms.model.blog.Role;
import com.pms.service.blog.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class RoleServiceImplTest {
    Role r = new Role(1,1,1);
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMapper roleMapper;
    @Test
    public void insertRole() throws Exception {

        Assert.assertEquals(1,roleMapper.insert(r));
    }

    @Test
    public void deleteRoleByRole() throws Exception {
        Assert.assertEquals(1,roleMapper.deleteByConbinationKey(r));
    }

    @Test
    public void selectByRole() throws Exception {
        Assert.assertEquals(r.getRoleId(),roleMapper.selectByConbinationKey(r).getRoleId());
    }

}