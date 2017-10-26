package com.pms.service.user;

import com.pms.dataModel.User.LoginInfo;
import com.pms.dataModel.User.PersonInfo;
import com.pms.model.blog.Role;
import com.pms.model.user.User;
import com.pms.service.user.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    boolean login(LoginInfo loginInfo,String verificationCode);

    /**
     * 用于用户注册的方法
     * @param user
     * @return
     * @throws Exception
     */
    boolean register(User user) throws UserServiceImpl.IsExistException;

    boolean modifyInfo(User user) throws NoSuchElementException;

    PersonInfo getUserInfo(String userName);

    /**
     * 查询这个用户名 userName 是否存在
     * @param userName
     * @return
     *  true 存在
     *  false 不存在
     */
    boolean isExist(String userName);

    /**
     * 返回错误信息，例如"密码错误"
     * @return
     */
    String getMessage();

    List findUserBySearching(User user);
}
