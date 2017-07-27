package com.pms.service.user.Impl;

import com.pms.dao.user.UserMapper;
import com.pms.dataModel.User.LoginInfo;
import com.pms.dataModel.User.PersonInfo;
import com.pms.model.user.User;
import com.pms.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by rhan on 2017/7/27.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserMapper userMapper;

    String message;

    public boolean login(LoginInfo loginInfo, HttpServletRequest request) {
        String verificationCode = (String) request.getSession().getAttribute("verificationCode");
        if (verificationCode.equals(loginInfo.getVerificationCode())) {
            LoginInfo loginInfo_db = userMapper.selectPasswordByUserName(loginInfo.getUserName());
            if (loginInfo_db != null) {
                if (loginInfo_db.getPassword().equals(loginInfo.getPassword())) {
                    return true;
                }else {
                    message = "密码错误";
                    return false;
                }
            }else {
                message = "账号不存在";
                return false;
            }
        }else {
            message = "验证码错误";
            return false;
        }
    }

    /**
     * 该方法主要是提供注册功能
     * todo User 当中数据的完整性没有检查
     * @param user
     * @return
     */
    public boolean register(User user) {
        if (!isExist(user.getUserName())) {
            userMapper.insertIntoUser(user);
        }
        return false;
    }

    public boolean modifyInfo(User user) {
        if (isExist(user.getUserName())){
            userMapper.updateUserInfo(user);
        }
        return false;
    }

    public PersonInfo getUserInfo(String userName) {
        return userMapper.selectPersonInfoByUserName(userName);
    }

    public String getMessage() {
        return (message == null) ? null:message;
    }

    public boolean isExist(String userName) {
        PersonInfo personInfo = userMapper.selectPersonInfoByUserName(userName);
        if (personInfo != null) {
            message = "用户名已经存在";
            return true;
        }
        return false;
    }
}
