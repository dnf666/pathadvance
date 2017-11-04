package com.pms.dao.user;

import com.pms.dataModel.User.LoginInfo;
import com.pms.dataModel.User.PersonInfo;
import com.pms.model.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户管理系统一个操作数据库的基本Dao类
 * Created by rhan on 2017/7/22.
 */
@Repository
public interface UserMapper {
    public LoginInfo selectPasswordByUserName(String userName);
    public boolean insertIntoUser(User user);
    public PersonInfo selectPersonInfoByUserName(String userName);
    public boolean updateUserInfo(User user);
/*<<<<<<< HEAD
    List<User> findUserBySearching(User user);
=======*/
    public List<User> selectOnePage(@Param("start") int start,@Param("limit") int limit);
    public int selectSumCount();

    List findBySearchingUser(User user);
//>>>>>>> master
}
