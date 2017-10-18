package com.pms.controller.admin;

import com.pms.service.admin.UserUtilService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import com.pms.util.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类和 user/UserAction 的类不一样，是用来给管理员
 * 提供服务的，所以大部分返回的数据都是不会做数据隐藏的
 */
@Controller
@RequestMapping("admin")
public class UserUtilAction {
    @Resource(name = "httpSession")
    Session session;

    @Autowired
    UserUtilService utilService;

    /**
     * 做两件事情
     *  第一件事情返回用户总条数
     *  第二件事情返回第一页的用户信息
     *      因为管理员进入到管理页面，永远都要加载第一页的数据，
     *      所以为了减少通讯，就直接连同第一页返回回去，
     * @param request
     */
    @RequestMapping("getUsersForAdmin")
    public void getUsersForAdmin(int start,int limit,HttpServletRequest request){
        Map map;
        if (session.isExist("admin")) {
            Map map_context = new HashMap();
            map_context.put("sumCount",utilService.getSumCount());
            map_context.put("list",utilService.selectOnePage(start,limit));
            map = MapUtil.toMap(true,"成功",map_context);
        }else {
            map = MapUtil.toMap(false,"管理员未登陆",null);
        }
        JsonUtil.toJSON(map);
    }
}
