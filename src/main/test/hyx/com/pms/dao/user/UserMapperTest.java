package hyx.com.pms.dao.user;

import com.pms.dao.user.UserMapper;
import com.pms.dataModel.User.LoginInfo;
import com.pms.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by rhan on 2017/7/23.
 */
@ContextConfiguration(locations = "classpath:springconfig.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {
    @Resource
    UserMapper userMapper;
    @Test
    public void selectPasswordByUserName() throws Exception {
        LoginInfo user = null;
        user = userMapper.selectPasswordByUserName("1");
        if (user == null) {
            System.out.println("不存在此用户");
        }else {
            System.out.println(user.getPassword());
        }
    }

    @Test
    public void insertIntoUser() throws Exception {
        User user = new User();
        user.setUserName("1");
        userMapper.insertIntoUser(user);
    }

    @Test
    public void selectPersonInfoByUserName() throws Exception {
    }

    @Test
    public void updateUserInfo() throws Exception {
    }

    @Test
    public void testBase() throws Exception{
    }
}