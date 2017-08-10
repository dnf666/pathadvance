package com.pms.service.blog.impl;

import com.pms.dao.blog.BlogMapper;
import com.pms.model.blog.BlogWithBLOBs;
import com.pms.service.blog.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;

    public int insert(BlogWithBLOBs blogWithBLOBs) {
        blogMapper.insert(blogWithBLOBs);
        return 0;
    }

    public int insertSelective(BlogWithBLOBs blogWithBLOBs) {
        return 0;
    }

    public int deleteByPrimaryKey(int id) {
        return 0;
    }

    public int updateBlogWithBlobs(BlogWithBLOBs blogWithBLOBs) {
        return 0;
    }

    public List<BlogWithBLOBs> selectAll() {
        return null;
    }

    public BlogWithBLOBs selectByPrimaryKey(int id) {
        return null;
    }

    public int updateBlogWithBlobsBySelective(BlogWithBLOBs blogWithBLOBs) {
        return 0;
    }
}
