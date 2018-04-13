package dlf;

import com.path.dao.QuestionBasicMapper;
import com.path.model.QuestionBasic;
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
public class QuestionBasicServiceImplTest {
    @Resource
    private QuestionBasicMapper questionBasicMapper;
    private static QuestionBasic questionBasic = new QuestionBasic();
    @Test
    public void deleteByPrimaryKey() {
        char c = 'A';
        int sum = (int)c-65;
        System.out.println(sum);


    }

    @Test
    public void insert() {
        questionBasic.setQId(1);
        questionBasic.setQDescript("lalal十大");
        questionBasic.setQName("123问题");
        questionBasic.setQRem1(null);
        questionBasic.setQRem2(null);
        System.out.println(questionBasic.getQName());
        int i = questionBasicMapper.insert(questionBasic);
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