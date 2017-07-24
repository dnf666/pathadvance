package com.pms.dao.blog;

import com.pms.model.blog.Blog;
import com.pms.model.blog.BlogWithBLOBs;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogWithBLOBs record);

    int insertSelective(BlogWithBLOBs record);

    BlogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BlogWithBLOBs record);

    int updateByPrimaryKey(Blog record);
}