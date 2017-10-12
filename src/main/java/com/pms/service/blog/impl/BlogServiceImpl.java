package com.pms.service.blog.impl;

import com.pms.dao.blog.BlogMapper;
import com.pms.model.blog.Blog;
import com.pms.model.blog.BlogWithBLOBs;
import com.pms.service.blog.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
//使用@Component，@Repository，@Service，@Controller注解或者使用@Component的自定义注解
//的类会自动为bean
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;

    public int insert(BlogWithBLOBs blogWithBLOBs) {
        try {
            blogMapper.insert(blogWithBLOBs);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("添加失败");
            return 0;
        }
        return 1;
    }

    public int insertSelective(BlogWithBLOBs blogWithBLOBs) {
        try {
            blogMapper.insertSelective(blogWithBLOBs);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("添加失败");
            return 0;
        }
        return 1;
    }

    public int deleteByPrimaryKey(int id)
    {
        try {
            blogMapper.deleteByPrimaryKey(id);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("删除失败");
            return 0;
        }
        return 1;
    }

    public int updateBlogWithBlobs(BlogWithBLOBs blogWithBLOBs) {

        try {
            blogMapper.updateByPrimaryKeyWithBLOBs(blogWithBLOBs);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("更新失败");
            return 0;
        }
        return 1;
    }

    public List<BlogWithBLOBs> selectAll() {
        try {
           List<BlogWithBLOBs> blogWithBLOBsList = blogMapper.selectAll();
           return blogWithBLOBsList;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("查找失败");
            return null;
        }

    }

    public BlogWithBLOBs selectByPrimaryKey(int id) {
        try {
          BlogWithBLOBs blogWithBLOBs = blogMapper.selectByPrimaryKey(id);
            return blogWithBLOBs;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("查找失败");
            return null;
        }
    }
    public int updateBlogWithBlobsBySelective(BlogWithBLOBs blogWithBLOBs) {
        try {
             blogMapper.updateByPrimaryKeySelective(blogWithBLOBs);

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("更新失败");
            return 0;
        }
        return 1;
    }

    public int updateDelFlag(int id) {
        return blogMapper.updateDelFlag(id);
    }

    public int setPrivate(int id) {
        return blogMapper.setPrivate(id);
    }

    public List<BlogWithBLOBs> selectOwnAll(String userName) {
        return blogMapper.selectOwnAll(userName);
    }

    public List<BlogWithBLOBs> selectOtherAll(String userName) {
        return blogMapper.selectOtherAll(userName);
    }

    public int updateByPrimaryKey(Blog blog){
        try {
            blogMapper.updateByPrimaryKey(blog);

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("更新失败");
            return 0;
        }
        return 1;
    }
}
