package com.pms.service.user.Impl;

import com.pms.dao.user.UserMapper;
import com.pms.dataModel.User.LoginInfo;
import com.pms.dataModel.User.PersonInfo;
import com.pms.model.user.User;
import com.pms.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

/**
 * Created by rhan on 2017/7/27.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserMapper userMapper;

    String message;

    public boolean login(LoginInfo loginInfo,String verificationCode) {
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
    public boolean register(User user) throws IsExistException {
        if (!isExist(user.getUserName())) {
            userMapper.insertIntoUser(user);
            return true;
        }
        throw new IsExistException("用户名 " + user.getUserName() + " 已经存在");
    }

    public boolean modifyInfo(User user){
        if (isExist(user.getUserName())){
            userMapper.updateUserInfo(user);
            return true;
        }
        throw new NoSuchElementException("没有用户名为 : " + user.getUserName() + " 的用户");
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
            return true;
        }
        return false;
    }

    /**
     * 自定义的一个异常，在程序当中执行异常处理比做 boolean 判断更好
     */
    class IsExistException extends Exception{
        String message;

        public IsExistException(String s) {
            this.message = s;
        }
    }
}
