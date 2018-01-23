package com.path.dao.user;

import com.path.dataModel.User.LoginInfo;
import com.path.dataModel.User.PersonInfo;
import com.path.model.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户管理系统一个操作数据库的基本Dao类
 * Created by rhan on 2017/7/22.
 */
@Repository
public interface UserMapper {

    /**
     * 根据用户名来选取一条用户信息
     * @param userName
     *  传入的用户名
     * @return
     *  返回一个登陆信息
     */
    LoginInfo selectPasswordByUserName(String userName);

    /**
     * 把用户的基本数据插入到数据库当中
     * @param user
     * @return
     */
    boolean insertIntoUser(User user);

    /**
     * 根据用户名称选取一条PersonInfo类型的数据
     * @param userName
     * @return
     */
    PersonInfo selectPersonInfoByUserName(String userName);

    /**
     * 跟新数据库里面的数据
     * @param user
     * @return
     */
    boolean updateUserInfo(User user);

    /**
     * 这个是戴林甫写得。。。
     * @param start
     * @param limit
     * @return
     */
    List<User> selectOnePage(@Param("start") int start,@Param("limit") int limit);

    /**
     * 戴林甫写的。。。
     * @return
     */
    int selectSumCount();

    /**
     * 戴林甫写的。。。。
     * @param user
     * @return
     */
    List findBySearchingUser(User user);
//>>>>>>> master
}
