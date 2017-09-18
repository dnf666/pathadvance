package com.pms.controller.blog;

import com.pms.model.blog.Role;
import com.pms.model.user.User;
import com.pms.service.blog.RoleService;
import com.pms.service.user.UserService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 由系统来看权限只需要查看的权限
 * 关于增加和删除的权限就没有实现
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private int FAILURE = 0;
    private int SUCCESS = 1;
    @Resource
    private RoleService roleService;
    private Role roleModel;
    @Resource
    private UserService userService;
    /**
     * 查询权限
     *
     * @param role 传入的用户id和访问者id
     */
    @RequestMapping("/selectRoleByConbinationKey.do")
    public void selectRoleByConbinationKey(Role role) {
        try {
            role.setRoleId(1);
            roleModel = roleService.selectByConbinationKey(role);
            if (roleModel == null)
                throw new Exception("没有权限");
            Map map = MapUtil.toMap(SUCCESS, "成功", roleModel);
            JsonUtil.toJSON(map);
        } catch (Exception e) {
            Map map = MapUtil.toMap(FAILURE, "您没有访问权限", roleModel);
            JsonUtil.toJSON(map);
        }


    }

    /**
     * 给用户设置查看权限
     *
     * @param role 权限对象
     */
    @RequestMapping("/insertRole.do")
    public void insertRole(Role role) {
        if (role.getMasterId() != null && role.getUserId() != null) {
            try {
                role.setRoleId(1);
                int status = roleService.insertRole(role);
                if (status == 0)
                    throw new Exception();
                Map map = MapUtil.toMap(SUCCESS, "添加成功", null);
                JsonUtil.toJSON(map);
            } catch (Exception e) {
                e.printStackTrace();
                Map map = MapUtil.toMap(FAILURE, "添加失败", null);
                JsonUtil.toJSON(map);
            }
        }

    }

    /**
     * 取消查看权限
     *
     * @param role 权限对象
     */
    @RequestMapping("/deleteRole.do")
    public void deleteRole(Role role) {
        if (role.getMasterId() != null && role.getUserId() != null) {
            try {
                role.setRoleId(1);
                int status = roleService.deleteRoleByConbinationKey(role);
                if (status == 0)
                    throw new Exception();
                Map map = MapUtil.toMap(SUCCESS, "删除成功", null);
                JsonUtil.toJSON(map);
            } catch (Exception e) {
                e.printStackTrace();
                Map map = MapUtil.toMap(FAILURE, "删除失败", null);
                JsonUtil.toJSON(map);
            }
        }
    }

    /**
     * 根据查询条件选出用户
     * @param user 带有用户名的对象
     */
    @RequestMapping("/searchUser")
    public void searchUser(User user){
        List userList = userService.findUserBySearching(user);
            String result = "查询成功";
        if(userList.isEmpty())
            result="无";
        Map map = MapUtil.toMap(SUCCESS,result,userList);
        JsonUtil.toJSON(map);
    }
}
