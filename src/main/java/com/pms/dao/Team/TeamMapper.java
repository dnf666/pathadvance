package com.pms.dao.team;

import com.pms.model.team.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liudong on 2017/8/10.
 */
@Repository
public interface TeamMapper {
    //对团队的操作，对团队表的操作
    public boolean addTeam(Team team);//创建一个团队
    public boolean delTeam(Team team);//注销团队，并没有在数据库中删除
    public Team getTeamInfo(String teamName);//通过团队的名称得到团队的相关信息
    //对团队成员的操作即对团队成员表的操作
    public boolean addTeamMember(TeamMember teamMember);//需要判断团队是否存在，如果不存在或者已经打上了删除标志就不能够加入；未完成
    public boolean delTeamMember(TeamMember teamMember);
    public List<TeamMember> getTeamMembersByTeamName(String teamName);//通过团队名称获得团队成员
    public TeamMember getTeamInfoByUserName(String userName);//通过用户名来获取用户加入了哪些团队
    //团队项目
    public boolean addProject(TeamProject teamProject);//创建项目
    public boolean delProject(TeamProject teamProject);//删除项目
    public boolean updateProject(TeamProject teamProject);//更改项目
    public List<TeamProject> getProjectInfoByTeamName(String teamName);//通过团队名称获取项目信息
    public List<TeamProject> getProjectInfoByProjectName(String projectName);////通过项目名称获取项目信息
    //团队项目成员
    public boolean addProjectMember(ProjectMember projectMember);//添加项目成员
    public boolean delProjectMember(ProjectMember projectMember);
    public List<ProjectMember> getaddProjectMembersByProject(String projectName);//通过项目名称获取项目成员的相关信息
    //团队公告
    public boolean addNotice(TeamNotice teamNotice);
    public boolean delNotice(TeamNotice teamNotice);
    public boolean updateNotice(TeamNotice teamNotice);
    public List<TeamNotice> getNoticeByteamName(String teamName);
    //团队资料
    public boolean addFile(TeamFile teamFile);
    public boolean delFile(TeamFile teamFile);
    public List<TeamFile> getFileByFileName(String fileName);

}
