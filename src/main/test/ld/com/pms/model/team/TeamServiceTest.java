package ld.com.pms.model.team;

import com.pms.dao.team.TeamMapper;
import com.pms.model.team.TeamNotice;
import com.pms.service.team.Impl.TeamSerciveImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by liudong on 2017/8/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class TeamServiceTest {
    @Resource
    TeamMapper teamMapper;

}
