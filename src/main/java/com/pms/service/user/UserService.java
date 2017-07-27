package com.pms.service.user;

import com.pms.dataModel.User.LoginInfo;
import com.pms.dataModel.User.PersonInfo;
import com.pms.model.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rhan on 2017/7/27.
 */
public interface UserService {
    public boolean login(LoginInfo loginInfo, HttpServletRequest request);
    public boolean register(User user);
    public boolean modifyInfo(User user);
    public PersonInfo getUserInfo(String userName);
    public boolean isExist(String userName);
    /**
     * 返回错误信息，例如"密码错误"
     * @return
     */
    public String getMessage();
}
