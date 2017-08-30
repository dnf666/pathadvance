package com.pms.controller.user;

import com.pms.dataModel.User.LoginInfo;
import com.pms.dataService.user.UserModelService;
import com.pms.model.user.User;
import com.pms.service.user.Impl.UserServiceImpl;
import com.pms.service.user.UserService;
import com.pms.service.user.VeriCode;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class UserAction {

    @Autowired
    UserService userService;
    @Autowired
    UserModelService userModelService;
    @Autowired
    VeriCode veriCode;

    @RequestMapping("login")
    public void login(LoginInfo loginInfo, HttpServletRequest request){
        Map map;
        String code = (String) request.getSession().getAttribute("verificationCode");
        if (userService.login(loginInfo,code)) {
            map = MapUtil.toMap(1,"success",null);
            request.getSession().setAttribute("userName",loginInfo.getUserName());
        }else {
            map = MapUtil.toMap(0,userService.getMessage(),null);
        }
        JsonUtil.toJSON(map);
    }

    @RequestMapping("register")
    public void register(User user, HttpServletRequest request){
        Map map;
        try {
            userService.register(user);
            request.getSession().setAttribute("userName",user.getUserName());
            map = MapUtil.toMap(1,"注册成功",userModelService.toPersonInfo(user));
        } catch (UserServiceImpl.IsExistException e) {
            map = MapUtil.toMap(0,"用户名已存在",user);
            e.printStackTrace();
        }
        JsonUtil.toJSON(map);
    }

    @RequestMapping("getVerificationCode")
    public void pushCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        char[] chars = veriCode.getChars();
        BufferedImage image = veriCode.getImage(chars);
        request.getSession().setAttribute("verificationCode",chars.toString());
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    @RequestMapping("modifyUserInfo")
    public void modifyUserInfo(User user){
        Map map;
        user = userModelService.prepareForModify(user);
        try {
            userService.modifyInfo(user);
            map = MapUtil.toMap(1,"修改成功",userModelService.toPersonInfo(user));
        } catch (NoSuchElementException e) {
            map = MapUtil.toMap(0,"修改失败",userModelService.toPersonInfo(user));
            e.printStackTrace();
        }
        JsonUtil.toJSON(map);
    }

}
