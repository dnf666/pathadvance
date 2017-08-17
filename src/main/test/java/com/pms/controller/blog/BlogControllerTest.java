package com.pms.controller.blog;

import com.pms.model.blog.BlogWithBLOBs;
import com.pms.service.blog.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class BlogControllerTest {
    BlogWithBLOBs blog = new BlogWithBLOBs();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");
    @Resource
    private BlogService blogService;
    @Test
    public void selectOwnAll() throws Exception {
       List<BlogWithBLOBs> list = blogService.selectOwnAll("2");
       for(BlogWithBLOBs blog:list)
       {
           System.out.println(blog.toString());
       }
    }

    @Test
    public void selectOtherAll() throws Exception {
        List<BlogWithBLOBs> list = blogService.selectOtherAll("2");
        for(BlogWithBLOBs blog:list)
        {
            System.out.println(blog.toString());
        }
    }

    @Test
    public void selectAll() throws Exception {
        List<BlogWithBLOBs> list = blogService.selectAll();
        for(BlogWithBLOBs blog:list)
        {
            System.out.println(blog.toString());
        }
    }

    @Test
        public void addBlog() throws Exception {
        String date = simpleDateFormat.format(new Date());
        System.out.println("时间格式："+date);
        blog.setCreateTime(date);
        blog.setCreateBy("戴林甫");
        blog.setId(5);
        blog.setTitle("title");
        blog.setContext("context");
        int i= blogService.insertSelective(blog);
    }

    @Test
    public void deleteBlog() throws Exception {
        String date = simpleDateFormat.format(new Date());
        blog.setDelTime(date);
        blog.setId(5);
        blog.setDelFlag(true);
        System.out.println("时间格式："+date);
        int i = blogService.updateBlogWithBlobsBySelective(blog);
    }

    @Test
    public void updateBlog() throws Exception {
        blog.setContext("111111111111");
        blog.setId(5);
        int i= blogService.updateBlogWithBlobsBySelective(blog);
    }

    @Test
    public void resultJudge() throws Exception {
    }

}