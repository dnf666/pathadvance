package com.pms.controller.Demo;

import com.pms.model.blog.BlogWithBLOBs;
import com.pms.model.user.User;
import com.pms.service.blog.BlogService;
import com.pms.util.JsonUtil;
import com.sun.crypto.provider.BlowfishKeyGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rhan on 2017/7/20.
 */
@Controller
public class DemoConroller {
//注入在spring中声名的类
    @Resource
    private BlogService blogService;

    @RequestMapping("demo.do")
    public void demo(HttpServletResponse response) {
        System.out.println("hello world");
        String s = "123";
        Map m = new HashMap();
        m.put("123",s);
        JsonUtil.toJSON(m,response);
    }


    /**
     * 本方法只是测试，前台传入json对象
     * 失败
     * @param user
     */
//    @RequestMapping(value = "/jsonTest.do",method = RequestMethod.POST)
//    public void jsonTest(@RequestBody User user) {
//        System.out.println("jsonTest:userName" + user.getUserName());
//
//        if (blogService.selectByPrimaryKey(1) == null) {
//            addOneBlog(1);
//        }
//        BlogWithBLOBs blogWithBLOBs = blogService.selectByPrimaryKey(1);
////       JsonUtil.toJSON(blogWithBLOBs,response);
//
//    }
//
//
//    private void addOneBlog(int id) {
//        BlogWithBLOBs blogWithBLOBs = new BlogWithBLOBs();
//        blogWithBLOBs.setId(id);
//        blogWithBLOBs.setCreateBy("userName");
//        blogService.insertSelective(blogWithBLOBs);
//    }
}
