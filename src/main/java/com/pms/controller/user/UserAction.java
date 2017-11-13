package com.pms.controller.user;

import com.pms.dataModel.User.LoginInfo;
import com.pms.dataModel.User.PersonInfo;
import com.pms.dataService.user.UserModelService;
import com.pms.model.user.User;
import com.pms.service.user.Impl.UserServiceImpl;
import com.pms.service.user.UserService;
import com.pms.service.user.VeriCode;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import com.pms.util.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * 现在的工程不大，其实也就一个月一个人就能做完所有的事情
 * 所以现在把这个类放在这其实也没有问题，但是就是说如果以后
 * 有一些需要对这个模块进行重构的话，那么就可能要拆分类了
 * 把一些基础的东西拆出来，一些很特别的对用户提供的东西也
 * 拆出来形成一个新的类
 * 另外，这个类当中，虽然用了Session的抽象接口，但是还有
 * 一部分代码仍然直接调用了HttpServletRequest类当中的方法
 * 所以，也是需要重构的，这个在我写的时候是没有注意到的
 * 在使用这个Action的时候一定要注意，不要把敏感的信息返回到前台
 * 例如密码，因为前台在调用接口的时候，如果你把敏感的信息返还回去了
 * 那么很容易就被人家用机器来查找你的用户的接接口
 */
//todo 重构所有用了HttpServletRequest类的方法，改成Session接口的方法
@Controller
public class UserAction {

    @Autowired
    UserService userService;
    @Autowired
    UserModelService userModelService;
    @Autowired
    VeriCode veriCode;
    @Resource(name = "httpSession")
    Session session;

    /**
     * 登陆，只返回状态码status就行了，如果登陆成功返回1，如果失败就返回0
     * 如果有错误信息，这里用的Service里面的message来代替的，但是到后面希望
     * 能给他换成用Exception的方式来换，至于抛出什么样的异常，主要是看具体的情况
     * @param loginInfo
     * @param request
     */
    //todo 把String字符串的内容替换成Exception来传递信息
    @RequestMapping("login")
    public void login(LoginInfo loginInfo, HttpServletRequest request){
        Map map;
        java.lang.String code = session.get("verificationCode");
        if (userService.login(loginInfo,code)) {
            map = MapUtil.toMap(1,"success",null);
            request.getSession().setAttribute("userName",loginInfo.getUserName());
        }else {
            map = MapUtil.toMap(0,userService.getMessage(),null);
        }
        JsonUtil.toJSON(map);
    }

    /**
     * 这个接口是用来注册的，一些必要的信息在User的类里面可以查到
     * 前台页面主要有如下的信息
     * 用户名、密码、邮箱、专业，其余的信息会被自动的填充默认值
     * @param user
     * @param request
     */
    //TODO 重构HttpServletRequest的Session方法
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


    /**
     * 这个是在Session当中存储验证码，并且用IO把生成的图片
     * 写到前台的页面当中去
     * @param request
     * @param response
     * @throws IOException
     */
    //TODO 重构HttpServletRequest的Session方法
    @RequestMapping("getVerificationCode")
    public void pushCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        char[] chars = veriCode.getChars();
        BufferedImage image = veriCode.getImage(chars);
        request.getSession().setAttribute("verificationCode", java.lang.String.valueOf(chars));
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    /**
     * 修改用户的信息的方法，关于返回的对象问题
     * 这里的对象信息是经过筛选并且重新生成了一个
     * 代替对象，这个代替对象里面没有敏感信息，也
     * 不会暴露关键的字段
     * @param user
     */
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

    /**
     * 这个主要是从Session里面拿到当前当前登陆状态的用户信息
     * 如果没有的话，可能会产生空指针，也就是当前的Session当中
     * 没有保存用户的登陆信息，也就是没有用户登陆
     */
    @RequestMapping("getUser")
    public void getUser(){
        String userName = session.get("userName");
        Map map;
        if (userName != null){
            map = MapUtil.toMap(1,"",userName);
        }else {
            map = MapUtil.toMap(0,"用户未登陆",null);
        }
        JsonUtil.toJSON(map);
    }

    /**
     * 同getUser方法
     */
    @RequestMapping("getUserInfo")
    public void getUserInfo(){
        java.lang.String userName = session.get("userName");
        Map map;
        if (userName != null){
            PersonInfo user = userService.getUserInfo(userName);
            map = MapUtil.toMap(1,"",user);
        }else {
            map = MapUtil.toMap(0,"用户未登陆",null);
        }
        JsonUtil.toJSON(map);
    }
}
