import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pms.dao.Demo.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by rhan on 2017/7/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class BaseTest {
    @Test
    public void testRunWith()throws Exception{
        System.out.println("hello spring Junit");
    }

    @Resource
    ComboPooledDataSource comboPooledDataSource;
    @Test
    public void testDatapool()throws Exception{
        comboPooledDataSource.getDescription();
    }

    @Resource
    SqlSessionFactoryBean sqlSessionFactoryBean;
    @Test
    public void testSqlSessionFactory() throws Exception{
        sqlSessionFactoryBean.isSingleton();
    }

    @Resource
    DemoMapper demoMapper;
    @Test
    public void testMapperManager() throws Exception{

    }
}
