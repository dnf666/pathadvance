package com.pms.dataService.user;

import com.pms.dataModel.User.PersonInfo;
import com.pms.model.user.User;
import org.springframework.stereotype.Service;

/**
 * 这个类是一个工具类，是为了方便当我们取出一个User对象，或者
 * 使用完一个User对象的时候，我们要返回给前端的同学数据，但是
 * User当中有些数据是很敏感的信息不能对外公布，所以就由这个类
 * 来管理
 */
@Service
public class UserModelService {
    /**
     * 用User对象的信息来构建一个PersonInfo对象，然后返回
     * @param user
     * @return
     */
    public PersonInfo toPersonInfo(User user){
        return new PersonInfo(user);
    }

    /**
     * 当要构建一个User对象来更新数据库的时候，对User对象关键字段
     * 设置一个默认的值
     * @param user
     * @return
     */
    public User prepareForModify(User user){
        user.setUserName(null);
        user.setDelFlag(false);
        user.setDelRemark(null);
        return user;
    }
}
