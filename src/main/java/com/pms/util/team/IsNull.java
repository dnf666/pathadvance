package com.pms.util.team;

import com.pms.model.team.Team;
import com.pms.model.team.TeamMember;
import com.pms.model.team.TeamNotice;

/**
 * CreatedBy: liudong
 * On: 2017/9/16.
 * describle:  用于判断传入的对象是否为空，或者其中一些必要的数据是否存在
 */
public class IsNull
{
    /**
     * 创建团队时团队的名称、创建者、创建时间
     * @param team 团队
     * @return 不为空返回true，否则返回false
     */
    public static boolean addTeamInfoIsOk(Team team){
        return team != null && team.getTeamName() != null && team.getCreateBy() != null && team.getCreateTime() != null;
    }
    /**
     * 删除团队时团队的名称、创建者、创建时间
     * @param team 团队
     * @return 不为空返回true，否则返回false
     */
    public static boolean delTeamInfoIsOk(Team team){
        return team !=null && team.getTeamName() != null && team.getDelTime() != null ;
    }

    public static boolean addteamNoticeInfoIsOk(TeamNotice teamNotice){
        return teamNotice.getCreateBy() != null && teamNotice.getTeamName() != null && teamNotice.getContext() != null && teamNotice.getCreateTime() != null;
    }

    public static boolean delTeamNoticeInfoIsOk(TeamNotice teamNotice){
        return teamNotice.getDelTime() != null && teamNotice.getId() != 0;
    }
    public static boolean updateaTeamNoticeInfoIsOK(TeamNotice teamNotice){
        return teamNotice.getTeamName() != null && teamNotice.getContext() != null && teamNotice.getCreateTime() != null && teamNotice.getId() != 0;
    }
}
