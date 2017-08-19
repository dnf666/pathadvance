package com.pms.dao.teamdao;

import com.pms.model.team.*;
import org.apache.ibatis.annotations.Param;
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
    public List<Team> getAllTeam();//得到所有团队对象
    //对团队成员的操作即对团队成员表的操作
    public boolean addTeamMember(TeamMember teamMember);
    public boolean delTeamMember(TeamMember teamMember);
    public List<TeamMember> getTeamMembersByTeamName(String teamName);//通过团队名称获得所有团队成员
    public List<TeamMember> getTeamInfoByUserName(String userName);//通过用户名来获取用户加入了哪些团队
    //团队项目
    public boolean addProject(TeamProject teamProject);//创建项目
    public boolean delProject(TeamProject teamProject);//删除项目
    public boolean updateProject(TeamProject teamProject);//更改项目
    public List<TeamProject> getProjectInfoByTeamName(String teamName);//通过团队名称获取所有项目信息
    public TeamProject getProjectInfoByProjectId(int projectId);////通过项目id获取项目信息
    //团队项目成员
    public boolean addProjectMember(ProjectMember projectMember);//添加项目成员
    public boolean delProjectMember(ProjectMember projectMember);
    public List<ProjectMember> getProjectMembersByProject(@Param("teamName")String teamName, @Param("projectName") String projectName);//通过项目名称获取所有项目成员的相关信息
    //团队公告
    public boolean addNotice(TeamNotice teamNotice);
    public boolean delNotice(TeamNotice teamNotice);
    public boolean updateNotice(TeamNotice teamNotice);
    public TeamNotice getNoticeById(int id);//通过id获取准确的公告信息
    public List<TeamNotice> getNoticeByteamName(String teamName);
    //团队资料
    public boolean addFile(TeamFile teamFile);
    public boolean delFile(TeamFile teamFile);
    public List<TeamFile> getFileByFileName(String fileName);
    //团队成员职务变动记录,只有更改了默认的职务才会在该表中添加信息,同时团队成员表中的信息也会更改
    public boolean insertTeamMasterHistory(TeamMasterHistory teamMasterHistory);
}
