package com.pms.util.team;

import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
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
        return team !=null && team.getTeamName() != null
                && team.getDelTime() != null ;
    }

    public static boolean addteamNoticeInfoIsOk(TeamNotice teamNotice){
        return teamNotice != null
                && teamNotice.getCreateBy() != null
                && teamNotice.getTeamName() != null
                && teamNotice.getContext() != null
                && teamNotice.getCreateTime() != null;
    }
    public static boolean delTeammemberInfoIsOK(TeamMember teamMember){
        return teamMember != null && teamMember.getTeamName() != null
                && teamMember.getUserName() != null
                && teamMember.getDelTime() != null
                && teamMember.getDelBy() != null;
    }
    public static boolean delTeamNoticeInfoIsOk(TeamNotice teamNotice){
        return teamNotice.getDelTime() != null && teamNotice.getId() != 0;
    }
    public static boolean updateaTeamNoticeInfoIsOK(TeamNotice teamNotice){
        return teamNotice.getTeamName() != null
                && teamNotice.getContext() != null
                && teamNotice.getCreateTime() != null
                && teamNotice.getId() != 0;
    }
    public static boolean addTeamProjectInfoIsOK(Project teamProject){
        return teamProject != null
                && teamProject.getProjectName() != null
                && teamProject.getCreateBy() != null
                && teamProject.getCreateAt() != null
                && teamProject.getTeamName() != null;
    }
    public static boolean addTeamFileInfoIsOK(FileImpl file){
        return file.getCreateBy() != null
                & file.getFileName() != null
                & file.getUrl() != null;
    }
}
