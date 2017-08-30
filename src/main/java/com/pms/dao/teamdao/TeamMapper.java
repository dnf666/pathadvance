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
    /**
     * 创建团队
     * @param team
     * @return
     */
    public boolean addTeam(Team team);

    /**
     * 注销团队
     * @param team
     * @return
     */
    public boolean delTeam(Team team);

    /**
     * 通过团队的名称得到团队的相关信息
     * @param teamName
     * @return
     */
    public Team getTeamInfo(String teamName);

    /**
     * 得到所有团队对象
     * @return
     */
    public List<Team> getAllTeam();

    /**
     * 添加团队成员
     * @param teamMember
     * @return
     */

    public boolean addTeamMember(TeamMember teamMember);

    /**
     * 删除团队成员
     * @param teamMember
     * @return
     */
    public boolean delTeamMember(TeamMember teamMember);

    /**
     * //通过团队名称获得该团队成员
     * @param teamName
     * @return
     */
    public List<TeamMember> getTeamMembersByTeamName(String teamName);

    /**
     * 通过用户名来获取用户加入了哪些团队
     * @param userName
     * @return
     */
    public List<TeamMember> getTeamInfoByUserName(String userName);

    /**
     * 创建项目
     * @param teamProject
     * @return
     */
    public boolean addProject(TeamProject teamProject);

    /**
     * 删除项目
     * @param teamProject
     * @return
     */
    public boolean delProject(TeamProject teamProject);

    /**
     * 更改项目
     * @param teamProject
     * @return
     */
     public boolean updateProject(TeamProject teamProject);

    /**
     * 通过团队名称获取所有项目信息
     * @param teamName
     * @return
     */
    public List<TeamProject> getProjectInfoByTeamName(String teamName);

    /**
     * 通过项目id获取项目信息
     * @param projectId
     * @return
     */
    public TeamProject getProjectInfoByProjectId(int projectId);

    /**
     * 添加项目成员
     * @param projectMember
     * @return
     */
    public boolean addProjectMember(ProjectMember projectMember);

    /**
     * 删除项目成员
     * @param projectMember
     * @return
     */
    public boolean delProjectMember(ProjectMember projectMember);

    /**
     * 得到该项目的成员
     * @param teamName
     * @param projectName
     * @return
     */
    public List<ProjectMember> getProjectMembersByProject(@Param("teamName")String teamName, @Param("projectName") String projectName);//通过项目名称获取所有项目成员的相关信息

    /**
     * 创建公告
     * @param teamNotice
     * @return
     */
    public boolean addNotice(TeamNotice teamNotice);

    /**
     * 删除公告
     * @param teamNotice
     * @return
     */
    public boolean delNotice(TeamNotice teamNotice);

    /**
     * 更新公告
     * @param teamNotice
     * @return
     */
    public boolean updateNotice(TeamNotice teamNotice);

    /**
     * 通过id获取准确的公告信息
     * @param id
     * @return
     */
    public TeamNotice getNoticeById(int id);

    /**
     * 获得该团队的所有公告
     * @param teamName
     * @return
     */
    public List<TeamNotice> getNoticeByteamName(String teamName);

    /**
     * 设置权限
     * @param teamMember
     * @return
     */
    public boolean setPrivilege(TeamMember teamMember);
    /**
     * 团队成员职务变动记录,
     * 只有更改了默认的职务才会在该表中添加信息,
     * 同时团队成员表中的信息也会更改。
     * @param teamMasterHistory
     * @return
     */
    public boolean insertTeamMasterHistory(TeamMasterHistory teamMasterHistory);
}
