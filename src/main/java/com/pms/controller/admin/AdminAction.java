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

@RequestMapping("admin")
@Controller
public class AdminAction {

    @Autowired
    AdminService adminService;
    @Resource(name="httpSession")
    Session session;

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

    @RequestMapping("loginOut")
    public void login_out(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
    }
}
