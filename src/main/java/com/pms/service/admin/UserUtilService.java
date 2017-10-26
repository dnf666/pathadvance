package com.pms.service.admin;

import com.pms.dao.user.UserMapper;
import com.pms.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 这个类是用来给管理员提供服务的，所以不需要做数据隐藏处理
 * 大部分功能都是用来统计数据库信息的
 */
@Service
public class UserUtilService {

    @Autowired
    UserMapper userMapper;

    public List<User> selectAll(){
        return selectOnePage(0,getSumCount());
    }

    public List<User> selectOnePage(int start,int limit){
        return userMapper.selectOnePage(start,limit);
    }

    public int getSumCount(){
        return userMapper.selectSumCount();
    }

}
