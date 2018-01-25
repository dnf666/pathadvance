package dlf;

import com.path.model.CenterNode;
import com.path.model.CenterNodeKey;
import com.path.service.CenterNodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author demo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class CenterNodeServiceImplTest {
@Resource
private CenterNodeService centerNodeService;
private CenterNodeKey centerNodeKey = new CenterNodeKey();
private CenterNode centerNode = new CenterNode("lala","lala",3,"123","1231a",213f,231f,123.32,null);
    @Test
    public void deleteByPrimaryKey() {

    }

    @Test
    public void insert() {
        int i = centerNodeService.insert(centerNode);
        Assert.assertEquals(1,i);
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
}