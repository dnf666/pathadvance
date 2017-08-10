package com.pms.service.blog;

import com.pms.model.blog.BlogWithBLOBs;

import java.util.List;

/**
 * 博客的service
 */
public interface BlogService {
    /**
     * 新添加博客时调用
     * @param blogWithBLOBs
     * @return
     */
    int insert(BlogWithBLOBs blogWithBLOBs);

    /**
     * 有选择性的添加，先放在这里，以防添加功能
     * @param blogWithBLOBs
     * @return
     */
    int insertSelective(BlogWithBLOBs blogWithBLOBs);

    /**
     *删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 更新博客内容时用
     * @param blogWithBLOBs
     * @return
     */
    int updateBlogWithBlobs(BlogWithBLOBs blogWithBLOBs);

    /**
     * 获取全部博客
     * @return
     */
    List<BlogWithBLOBs> selectAll();

    /**
     * 获取特定的博客
     * @param id
     * @return
     */
    BlogWithBLOBs selectByPrimaryKey(int id);
    /**
     * 选择性更新
     */
    int updateBlogWithBlobsBySelective(BlogWithBLOBs blogWithBLOBs);

}
