package com.path.dao;

import com.path.model.Vahicle;
import com.path.model.VahicleKey;

import java.util.List;

public interface VahicleMapper {
    int deleteByPrimaryKey(VahicleKey key);

    int insert(Vahicle record);

    int insertSelective(Vahicle record);

    Vahicle selectByPrimaryKey(VahicleKey key);

    int updateByPrimaryKeySelective(Vahicle record);

    int updateByPrimaryKey(Vahicle record);

    boolean insertAdvance(List<Vahicle> list);
}