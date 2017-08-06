package com.pms.controller.user;

import com.pms.dataModel.User.LoginInfo;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserAction {

    @RequestMapping("login")
    public void login(LoginInfo loginInfo, HttpServletRequest request){

    }
}
