package com.pms.service.user;

import com.pms.dataModel.User.LoginInfo;
import com.pms.dataModel.User.PersonInfo;
import com.pms.model.user.User;
import com.pms.service.user.Impl.UserServiceImpl;

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
     *  IsExistException异常，是当注册的用户名在数据库当中已经存在的时候
     *  就会返回这个异常
     */
    boolean register(User user) throws UserServiceImpl.IsExistException;

    /**
     * 修改用户的在数据库当中的信息
     * @param user
     * @return
     * @throws NoSuchElementException
     *  当尝试的从数据库当中获取到用户实体的时候，如果当前的数据没有
     *  该用户名对应的用户数据，那么就会返回这个异常
     */
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

    /**
     * 这个是戴林甫写的。。。和我没关系。。。
     * @param user
     * @return
     */
    List findUserBySearching(User user);
}
