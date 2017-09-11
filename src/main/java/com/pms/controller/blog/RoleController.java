package com.pms.controller.blog;

import com.pms.service.blog.RoleService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class RoleController {
    @Resource
    private RoleService roleService;

}
