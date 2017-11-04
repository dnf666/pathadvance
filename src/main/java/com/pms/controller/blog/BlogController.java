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
import java.util.List;
import java.util.Map;

/**
 * 博客系统的controller
 * 博客系统的跳转都在这
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    private BlogWithBLOBs blogWithBLOBs = new BlogWithBLOBs();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");

    @Resource
    private BlogService blogService;

    /**
     * 查看自己的全部博客或者有权限查看
     *
     * @param userName 自己的用户名
     */
    @RequestMapping("selectOwnAll")
    public void selectOwnAll(String userName, HttpServletResponse response) {
        List<BlogWithBLOBs> list = blogService.selectOwnAll(userName);
        JsonUtil.toJSON(list, response);
    }

    /**
     * 查看别人的博客
     *
     * @param userName 别人的用户名
     * @param response 前台发过来的response
     */
    @RequestMapping("selectOtherAll")
    public void selectOtherAll(String userName, HttpServletResponse response) {
        List<BlogWithBLOBs> list = blogService.selectOtherAll(userName);
        JsonUtil.toJSON(list, response);
    }

    /**
     * 主页展示全部没被删和公开的博客
     *
     * @param response 前台发过来的response
     */
    @RequestMapping("selectAll")
    public void selectAll(HttpServletResponse response) {
        List<BlogWithBLOBs> list = blogService.selectAll();
        JsonUtil.toJSON(list, response);
    }

    /**
     * 查询博客详情
     *
     * @param id       博客id
     * @param response 回应参数
     */
    @RequestMapping("selectByUserName")
    public void selectByUserName(int id, HttpServletResponse response) {
        BlogWithBLOBs blogWithBLOBs = blogService.selectByPrimaryKey(id);
        JsonUtil.toJSON(blogWithBLOBs, response);
    }

    /**
     * 博客设置私有
     *
     * @param id 博客id
     */
    @RequestMapping("setPrivate")
    public void setPrivate(int id) {
        int i = blogService.setPrivate(id);
        JsonUtil.toJSON(i);
    }

    /**
     * 添加博客
     *
     * @param blog     添加的博客
     * @param response 前台发过来的response
     */
    @RequestMapping("addBlog")
    public void addBlog(BlogWithBLOBs blog, HttpServletResponse response) {
        String date = simpleDateFormat.format(new Date());
        System.out.println("时间格式：" + date);
        blog.setCreateTime(date);
        int i = blogService.insertSelective(blog);
        Map<String, Object> map = resultJudge(i, null);
        JsonUtil.toJSON(map, response);
    }

    /**
     * 用户端删除博客，
     *
     * @param id       删除博客的id
     * @param response 前台发过来的response
     */
    @RequestMapping("deleteBlog")
    public void deleteBlog(int id, HttpServletResponse response) {
        String date = simpleDateFormat.format(new Date());
        blogWithBLOBs.setDelTime(date);
        blogWithBLOBs.setId(id);
        blogWithBLOBs.setDelFlag(true);
        System.out.println("时间格式：" + date);
        int i = blogService.updateBlogWithBlobsBySelective(blogWithBLOBs);
        Map<String, Object> map = resultJudge(i, null);
        JsonUtil.toJSON(map, response);
    }

    /**
     * 更新博客
     *
     * @param blog     更新的博客
     * @param response 前台发过来的response
     */
    @RequestMapping("updateBlog")
    public void updateBlog(BlogWithBLOBs blog, HttpServletResponse response) {
        int i = blogService.updateBlogWithBlobsBySelective(blog);
        Map<String, Object> map = resultJudge(i, null);
        JsonUtil.toJSON(map, response);
    }

    /**
     * 返回样式封装
     *
     * @param i 操作结果
     * @return 返回map
     */
    private Map<String, Object> resultJudge(int i, Object object) {
        String result = null;
        if (i >= 1) {
            result = "操作成功";
        } else {
            result = "操作失败";
        }
        Map<String, Object> map = MapUtil.toMap(1, result, object);
        return map;
    }
}
