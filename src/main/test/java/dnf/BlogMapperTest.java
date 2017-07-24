package dnf;

import com.pms.dao.blog.BlogMapper;
import com.pms.model.blog.BlogWithBLOBs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class BlogMapperTest {
    @Resource
    BlogMapper blogMapper;
    @Test
    public void insert()throws Exception{
        BlogWithBLOBs blogWithBLOBs =new BlogWithBLOBs();
        blogWithBLOBs.setId(1);
        blogWithBLOBs.setContext("context");
        blogWithBLOBs.setCreateTime("time");
        blogWithBLOBs.setDelTime("deletetime");
        blogWithBLOBs.setCreateBy("createby");
        blogWithBLOBs.setDelFlag(true);
        blogWithBLOBs.setIsPrivate(true);
        blogWithBLOBs.setTitle("title");
        Assert.assertEquals(1,blogMapper.insert(blogWithBLOBs));

    }

}