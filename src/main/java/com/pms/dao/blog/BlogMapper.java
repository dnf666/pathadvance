package com.pms.dao.blog;

import com.pms.model.blog.Blog;
import com.pms.model.blog.BlogWithBLOBs;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 用户删除，只是修改del_flag
     * @param id
     * @return
     */
    @Update("update blog set del_flag = 1 where id = #{id}")
    int updateDelFlag(int id);

    /**
     * 设置为私有
     * @param id
     * @return
     */
    int setPrivate(int id);


    List<BlogWithBLOBs> selectOwnAll(String userName);

    List<BlogWithBLOBs> selectOtherAll(String userName);
}