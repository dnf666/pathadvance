package com.pms.service.user;

import com.pms.dataModel.User.LoginInfo;
import com.pms.dataModel.User.PersonInfo;
import com.pms.model.user.User;
import com.pms.service.user.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

/**
 * Created by rhan on 2017/7/27.
 */
public interface UserService {

    /**
     * 用于提供用户登陆的方法
     * @param loginInfo
     * @param verificationCode
     * @return
     */
    public boolean login(LoginInfo loginInfo,String verificationCode);

    /**
     * 用于用户注册的方法
     * @param user
     * @return
     * @throws Exception
     */
    public boolean register(User user) throws UserServiceImpl.IsExistException;

    public boolean modifyInfo(User user) throws NoSuchElementException;

    public PersonInfo getUserInfo(String userName);

    /**
     * 查询这个用户名 userName 是否存在
     * @param userName
     * @return
     *  true 存在
     *  false 不存在
     */
    public boolean isExist(String userName);
}
