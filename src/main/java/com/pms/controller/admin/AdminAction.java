package com.pms.controller.admin;

import com.pms.model.admin.Admin;
import com.pms.model.exception.WrongPasswordException;
import com.pms.service.admin.AdminService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import com.pms.util.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 这里面放一些管理员的基本操作
 * 例如：登陆、注销等等一些动作
 * 方便后期来查
 */
@RequestMapping("admin")
@Controller
public class AdminAction {

    @Autowired
    AdminService adminService;
    @Resource(name="httpSession")
    Session session;

    /**
     * 管理员的登陆，会对Map进行一些初始化的工作
     * '错误 1'表示只进行了初始化，但是没有执行到 try 或者 catch 语句当中去
     * 当Service层抛出异常的时候，这个异常是含带有信息的所以e.getMessage()
     * 就是取出这些信息的描述
     * @param admin
     *  adminName 输入的管理员用户名
     *  password  输入的密码
     */
    @RequestMapping("login")
    public void login(Admin admin){
        Map map = MapUtil.toMap(false,"错误 1",null);
        try {
            if (adminService.login(admin)) {
                map = MapUtil.toMap(true,null,admin);
                session.put("admin",admin.getAdminName());
            }
        }catch (NullPointerException e){
            map = MapUtil.toMap(false,e.getMessage(),null);
        }catch (WrongPasswordException e){
            map = MapUtil.toMap(false,e.getMessage(),null);
        }finally {
            JsonUtil.toJSON(map);
        }
    }

    /**
     * 登出的操作，这个方法是有问题的，因为在前面使用的都是
     * 抽象了接口当中的方法，但是直接就清除了HttpServletRequest
     * 所以有点问题
     * @param request
     *  这个参数是可以不要的，在后面重构了就删除了吧
     */
    //todo 这是我留下的一个坑，需要你们改用成Session接口的方法
    @RequestMapping("loginOut")
    public void login_out(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
    }
}
