package com.path.util.team;

import java.util.List;

/**
 * CreatedBy: liudong
 * On: 2017/9/9.
 * describle:实现分页展示功能
 */
public class PageList {
    public static List getList(int page,List list) {
        int pageSize = 5;//每一页展示的消息条数
        if (list.size() != 0){
            if (list.size() <= pageSize){//所有团队不足一页的时候,全部展示
                return list;
            }else if (list.size() > pageSize && page <= list.size()/pageSize){//当列表中的数目大于单页的size时，就按照每页的显示
                return list.subList(pageSize*(page-1),(page*pageSize));
            }else if (page==1+list.size()/pageSize){//显示末页
                return list.subList((page-1)*pageSize,list.size());
            }
        }

        return null;
    }
}
