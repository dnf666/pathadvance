package dlf.dnf;

import com.pms.model.user.User;
import com.pms.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class RoleControllerTest {
    @Resource
    private UserService userService;
    @Test
    public void searchUser() throws Exception {
       /* User user = new User();
        user.setUserName("1");
        List<User> userList = userService.findUserBySearching(user);
        for(User user1: userList){
            System.out.println(user1.getUserName());*/

        Map<String,String> map = new HashMap<String, String>();
        map.put("1","1");
        map.put("2","2");
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String,String> e= (Map.Entry<String,String>)iterator.next();
            System.out.println(e.getValue());
        }
        }
        



}