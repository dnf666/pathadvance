package dlf;

import com.path.dao.DistanceMapper;
import com.path.model.Distance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author demo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class DistanceServiceImplTest {

        @Resource
        private DistanceMapper distanceMapper;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void updateAdvance() {
    }

    @Test
    public void selectAllCenterNodeAddress() {
    }

    @Test
    public void insertAdvance() {
    }

    @Test
    public void produceAllWay() {
        List<Distance> list = distanceMapper.produceAllWay(1);
        System.out.println(list);
    }
}