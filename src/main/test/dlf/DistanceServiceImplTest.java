package dlf;

import com.alibaba.fastjson.JSONObject;
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
        Distance distance = new Distance(1,"\"c1\"","\"s1009\"","\"123\"","123",1,1);
        System.out.println(distance.getStartId().replaceAll("\"",""));
        System.out.println(distanceMapper.updateByPrimaryKeySelective(distance));
        System.out.println(distance);

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
        List<String> list = distanceMapper.produceAllWay(1);
        System.out.println(list);
    }
}