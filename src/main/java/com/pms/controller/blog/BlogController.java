package com.pms.controller.blog;

import com.pms.model.blog.BlogWithBLOBs;
import com.pms.service.blog.BlogService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/blog")
public class BlogController {
    BlogWithBLOBs blogWithBLOBs = new BlogWithBLOBs();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");

    @Resource1
    private BlogService blogService;

    /**
     * 查看自己的全部博客
     * @param userName
     */
    @RequestMapping("selectOwnAll")
    public void selectOwnAll(String userName, HttpServletResponse response){
        List<BlogWithBLOBs> list = blogService.selectOwnAll(userName);
        JsonUtil.toJSON(list,response);
    }
    /**
     * 查看别人的全部公开博客
     */
    @RequestMapping("selectOtherAll")
    public void selectOtherAll(String userName,HttpServletResponse response){
        List<BlogWithBLOBs> list = blogService.selectOtherAll(userName);
        JsonUtil.toJSON(list,response);
    }

    /**
     * 主页展示全部没被删和公开的博客
     * @param response
     */
    @RequestMapping("selectAll")
    public void selectAll(HttpServletResponse response){
        List<BlogWithBLOBs> list = blogService.selectAll();
        JsonUtil.toJSON(list,response);
    }

    /**
     * 添加博客
     * @param blog
     * @param response
     */
    @RequestMapping("addBlog")
    public void addBlog(BlogWithBLOBs blog,HttpServletResponse response){
        String date = simpleDateFormat.format(new Date());
        System.out.println("时间格式："+date);
        blog.setCreateTime(date);
        int i= blogService.insertSelective(blog);
        Map<String,Object> map =resultJudge(i,null);
        JsonUtil.toJSON(map,response);
    }

    /**
     * 用户端删除博客，
     * @param id
     * @param response
     */
    @RequestMapping("deleteBlog")
    public void deleteBlog(int id,HttpServletResponse response){
        String date = simpleDateFormat.format(new Date());
        blogWithBLOBs.setDelTime(date);
        blogWithBLOBs.setId(id);
        blogWithBLOBs.setDelFlag(true);
        System.out.println("时间格式："+date);
        int i = blogService.updateBlogWithBlobsBySelective(blogWithBLOBs);
        Map<String,Object> map =resultJudge(i,null);
        JsonUtil.toJSON(map,response);
    }

    /**
     * 更新博客
     * @param blog
     * @param response
     */
    @RequestMapping("updateBlog")
    public void updateBlog(BlogWithBLOBs blog,HttpServletResponse response){
        int i= blogService.updateBlogWithBlobsBySelective(blog);
        Map<String,Object> map =resultJudge(i,null);
        JsonUtil.toJSON(map,response);
    }

    /**
     * 返回样式封装
     * @param i
     * @return
     */
    public Map<String,Object> resultJudge(int i,Object object){
        String result = null ;
        if(i>=1){
            result = "操作成功";
        }else {
            result = "操作失败";
        }
        Map<String,Object> map = MapUtil.toMap(1,result,object);
        return map;
    }
}
