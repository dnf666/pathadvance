package dlf;

import com.path.model.CenterNode;
import com.path.model.CenterNodeKey;
import com.path.service.centernode.CenterNodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String s = "1小时11分钟";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh小时mm分钟");
        try {
            Date date = simpleDateFormat.parse(s);
            System.out.println(date.getMinutes());
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
    @Test
    public void updateAdvance(){
        CenterNode centerNode = new CenterNode();
        centerNode.setCId(1);
        centerNode.setCNum("1");
        centerNode.setCBaidulatitude(123.2f);
        centerNode.setCBaidulongitude(2133.2f);
        centerNode.setCLatitude("123");
        centerNode.setCLongitude("123");
        CenterNode centerNode1 = new CenterNode();
        centerNode1.setCId(1);
        centerNode1.setCNum("2");
        centerNode1.setCBaidulatitude(123.2f);
        centerNode1.setCBaidulongitude(2133.2f);
        centerNode1.setCLatitude("123");
        centerNode1.setCLongitude("123");

        List<CenterNode> list = new ArrayList<>();
        list.add(centerNode);
        list.add(centerNode1);
        boolean re =centerNodeService.updateAdvance(list);
        System.out.println(re);
    }
}