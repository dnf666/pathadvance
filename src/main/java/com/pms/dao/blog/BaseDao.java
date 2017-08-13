package com.pms.dao.blog;


import java.util.List;

/**
 * 所有dao层接口的父类，声明了基本的增删改查方法
 * @param <T>
 * @param <T1>
 */

public interface BaseDao<T,T1> {
    /**
     * 添加
     * @param obj
     * @return
     */
        int insert(T obj);

    /**
     * 删除
     * @param id
     * @return 1:成功 0:失败
     */
    int deleteByPrimaryKey(T1 id);

    /**
     * 更新
     * @param obj
     * @return 1:成功 0:失败
     */
        int updateByPrimaryKeySelective(T obj);

    /**
     * 查询
     * @param id
     * @return
     */
    T selectByPrimaryKey(T1 id);

    /**
     * 查询全部
     * @return
     */
        List<T> selectAll();

}
