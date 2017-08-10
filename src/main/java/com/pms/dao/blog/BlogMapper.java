package com.pms.dao.blog;

import com.pms.model.blog.Blog;
import com.pms.model.blog.BlogWithBLOBs;
import org.springframework.stereotype.Repository;

/**
 * 博客的dao层
 */
@Repository
public interface BlogMapper extends BaseDao<BlogWithBLOBs,Integer>{

    /**
     * 实现有选择的添加
     * @param record
     * @return
     */
    int insertSelective(BlogWithBLOBs record);

    /**
     * 更新基本信息时调用，没必要再检查博客内容而降低性能
     * @param record
     * @return
     */
    int updateByPrimaryKey(Blog record);

    /**
     * 全部更新时调用
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(BlogWithBLOBs record);
}