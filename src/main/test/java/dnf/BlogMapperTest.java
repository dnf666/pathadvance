package java.dnf;

import com.pms.dao.blog.BlogMapper;
import com.pms.model.blog.BlogWithBLOBs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
<<<<<<< HEAD

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
=======
>>>>>>> 72e5c5f6f452e2a434c2d3c548e18530c465a56c
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
 blogMapper.insert(blogWithBLOBs);

    }
    @Test
    public void delete() throws Exception{
        Assert.assertEquals(1,blogMapper.deleteByPrimaryKey(1));
    }
    @Test
    public void insertselective()throws Exception{
        BlogWithBLOBs blogWithBLOBs =new BlogWithBLOBs();
        blogWithBLOBs.setContext("context");
        blogWithBLOBs.setCreateTime("time");
        blogWithBLOBs.setDelTime("deletetime");
        blogWithBLOBs.setCreateBy("createby");
        blogWithBLOBs.setDelFlag(true);
        blogWithBLOBs.setIsPrivate(true);
        blogWithBLOBs.setTitle("title");
        Assert.assertEquals(1,blogMapper.insertSelective(blogWithBLOBs));

    }
        @Test
    public void update()throws Exception{
            BlogWithBLOBs blogWithBLOBs =new BlogWithBLOBs();
            blogWithBLOBs.setId(2);
            blogWithBLOBs.setContext("context1");
            blogWithBLOBs.setCreateTime(new Date().toString());
            blogWithBLOBs.setDelTime("deletetime1");
            blogWithBLOBs.setCreateBy("createby1");
            blogWithBLOBs.setDelFlag(true);
            blogWithBLOBs.setIsPrivate(true);
            blogWithBLOBs.setTitle("title");
            Assert.assertEquals(1,blogMapper.updateByPrimaryKeyWithBLOBs(blogWithBLOBs));
        }
        @Test
        public void find()throws Exception {
            System.out.println(blogMapper.selectByPrimaryKey(2));
        }
        @Test
    public void updateDelFlag(){
                blogMapper.updateDelFlag(2);
        }
    @Test
    public void updateDelFlag1(){
        blogMapper.setPrivate(2);
    }
    @Test
    public void selectOwnAll(){
       List<BlogWithBLOBs> list = blogMapper.selectAll();
       for (BlogWithBLOBs blogWithBLOBs:list)
       {
           System.out.println(blogWithBLOBs.toString());
       }
    }
}