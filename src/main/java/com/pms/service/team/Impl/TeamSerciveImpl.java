
package com.pms.service.team.Impl;
import com.pms.dao.project.ProjectMapper;
import com.pms.dao.teamdao.TeamMapper;
import com.pms.dao.user.UserMapper;
import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.model.team.*;
import com.pms.service.file.FileService;
import com.pms.service.project.ProjectService;
import com.pms.service.team.TeamService;
import com.pms.util.team.IsNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by liudong on 2017/8/15.
 *
 */
@Service
public class TeamSerciveImpl implements TeamService{
    private final int MASTER_OF_TEAMMEMBER = 2;//团队负责人即最高权限
    private final int MANAGER_OF_TEAMMEMBER = 1;//团队管理员的权限
    private final int NO_SUCH_TEAMMEMBER = -1;//表示当前团队没有这个成员
    private final int DEL_FLAG = 1;//删除的标志
    private final String ROLE_OF_PROJECTCREATER = "负责人";//项目创建者的角色
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProjectService projectService;
    @Resource
    private FileService fileService;
    @Resource
    private TeamMapper teamMapper;
    public TeamMember getTeamMember(List<TeamMember> list, String userName) {
        if (list != null && userName!=null){
            if (list.size() == 0) {
                return null;
            }
            for(TeamMember teamMember:list) {//遍历当前的团队成员，找到用户名与username匹配的团队成员
                if (userName.equals(teamMember.getUserName())){
                    return teamMember;
                }
            }
        }
        return null;//表示没有匹配的团队成员
    }
    public int getTeamPrivilege(TeamMember teamMember) {
        //判断 传入的团队成员是否存在
        if (teamMember != null
                && getTeamMemberByTeamNameAndUserName(teamMember.getTeamName() , teamMember.getUserName()) != null){
            return teamMember.getTeamPrivelige();
        }
        return NO_SUCH_TEAMMEMBER;
    }
    public TeamMember getTeamMemberByTeamNameAndUserName(String teamName, String userName) {
        return teamMapper.getTeamMemberByTeamNameAndUserName(teamName,userName);
    }
    public TeamMember getDelTeamMember(String teamName , String userName) {
        return teamMapper.getDelTeamMember(teamName , userName);
    }
    public boolean createTeam(Team team) {
        //检查团队信息是否完整
        if (team != null && team.getCreateBy() != null && team.getTeamName() != null && team.getCreateTime() !=null){
            if (teamMapper.addTeam(team)){
                TeamMember teamMember = new TeamMember.Builder()
                        .teamName(team.getTeamName())
                        .userName(team.getCreateBy())
                        .teamRole("负责人")
                        .joinBy(team.getCreateBy())
                        .joinTime(team.getCreateTime())
                        .teamPrivelige(2)
                        .build();
                return teamMapper.addTeamMember(teamMember);
            }
        }
        return false;
    }
    public List<Team> getAllTeam() {
        return teamMapper.getAllTeam();
    }
    public List<Team> getMyteam(String userName) {
        List<Team> teamList = new LinkedList<Team>();
        if (userName != null){
            List<TeamMember> teamMemberList = teamMapper.getTeamInfoByUserName(userName);//通过团队成员获得团队名称的信息
            if (teamMemberList != null && teamMemberList.size() > 0){
                for (TeamMember tm:teamMemberList) {
                    String teamName = tm.getTeamName();
                    teamList.add(teamMapper.getTeamInfo(teamName));
                }
            }
        }
        return teamList;
    }
    public List<Team> getManagedTeam(String userName) {
        List<Team> teamList = new LinkedList<Team>();
        if (userName != null){
            List<TeamMember> teamMemberList = teamMapper.getTeamInfoByUserName(userName);//通过团队成员获得团队名称的信息
            if (teamMemberList != null && teamMemberList.size() > 0){
                for (TeamMember tm:teamMemberList) {
                    String teamName = tm.getTeamName();
                    int privilege = getTeamPrivilege(tm);//用户在团队中的权限
                    if (privilege >= MANAGER_OF_TEAMMEMBER){
                        teamList.add(teamMapper.getTeamInfo(teamName));
                    }
                }
            }
        }
        return teamList;
    }
    public List<Team> getJoinTeam(String userName) {
        List<Team> teamList = new LinkedList<Team>();
        if (userName != null){
            List<TeamMember> teamMemberList = teamMapper.getTeamInfoByUserName(userName);//通过团队成员获得团队名称的信息
            if (teamMemberList != null && teamMemberList.size() > 0){
                for (TeamMember tm:teamMemberList) {
                    String teamName = tm.getTeamName();
                    int privilege = getTeamPrivilege(tm);//用户在团队中的权限
                    if (privilege < MANAGER_OF_TEAMMEMBER){
                        teamList.add(teamMapper.getTeamInfo(teamName));
                    }
                }
            }
        }
        return teamList;
    }

    public boolean delTeam(Team team , String delBy) {
        if (team != null && team.getTeamName() !=null && team.getCreateBy() !=null && delBy != null){
            List<TeamMember> listOfTeamMember = getTeamMembers(team.getTeamName());
            if (listOfTeamMember == null){
                return false;
            }
            TeamMember teamMember = getTeamMember(listOfTeamMember,delBy);//表示删除者不是当前团队成员
            if (teamMember == null){
                return false;
            }
            int privilege = getTeamPrivilege(teamMember);//删除团队成员的团队操作权限
            if (privilege == MASTER_OF_TEAMMEMBER && teamMapper.delTeam(team)){
                //如果团队删除成功，那么团队中的成员亦会被删除
                for (TeamMember tm:listOfTeamMember) {
                    tm.setDelFlag(DEL_FLAG);
                    tm.setDelBy(delBy);
                    tm.setDelTime(team.getDelTime());
                    if ( ! teamMapper.delTeamMember(tm)){
                        return false;//删除团队成员失败
                    }
                }
                return true;
            }
        }
        return false;
    }
    public boolean inviteMember(TeamMember teamMember) {
        String teamName = teamMember.getTeamName();
        String userName = teamMember.getUserName();
        if (teamMember != null && teamName != null && userName != null){
            List<TeamMember> listOfTeamMember = getTeamMembers(teamMember.getTeamName());//得到当前团队的团队成员，如果为空则表示当前团队不存在
            //如果团队中已经存在该成员，则不必再次向数据库添加一条信息
            if (teamMapper.getTeamMemberByTeamNameAndUserName(teamName , userName) != null){
                return true;
            }
            //如果团队中以前添加过当前团队成员，被移除，现在又重新添加
            if (teamMapper.getDelTeamMember(teamName , userName) != null){
                return teamMapper.reAddTeamMember(teamMember);
            }
            //团队中从未有添加过该成员
            return
                    listOfTeamMember != null || teamMapper.addTeamMember(teamMember);
        }
        return false;//因为传入的成员信息不全
    }
    public List<TeamMember> getTeamMembers(String teamName) {
        if (teamName != null)
            return teamMapper.getTeamMembersByTeamName(teamName);
        return null;
    }
    public boolean delTeamMember(TeamMember teamMember) {
        if (IsNull.delTeammemberInfoIsOK(teamMember)){
            String delBy = teamMember.getDelBy();
            List<TeamMember> listOfTeamMember = getTeamMembers(teamMember.getTeamName());
            TeamMember tm = getTeamMember(listOfTeamMember,delBy);
            if (tm != null){
                if (getTeamPrivilege(tm) >= MANAGER_OF_TEAMMEMBER){
                    return teamMapper.delTeamMember(teamMember);
                }
            }
        }
        return false;
    }

    public boolean createProject(Project teamProject) {
        //创建项目必须要有一个团队,首先判断当前用户是否是在当前的团队当中
        //同时将当前成员插入project_member中
        if (IsNull.addTeamProjectInfoIsOK(teamProject)){
            String teamName = teamProject.getTeamName();
            String userName = teamProject.getCreateBy();
             /*  if (projectService.addProject(teamProject)){
                   if (getTeamMemberByTeamNameAndUserName(teamName, userName) != null){
                       //////////////////////////差一个项目id
                       ProjectMember projectMember = new ProjectMember.Builder().projectRole(ROLE_OF_PROJECTCREATER)
                               .teamName(teamName)
                               .userName(userName)
                               .joinTime(teamProject.getCreateAt())
                               .joinBy(userName)
                               .build();
                       return projectService.addProMember(projectMember);
               }

            }*/
        }
        return false;
    }

    public boolean delProject(Project teamProject, String delBy) {
        if (teamProject != null && teamProject.getCreateBy() != null && teamProject.getCreateBy().equals(delBy)){
            ////////这里应当写删除项目和删除所有的项目成员信息
        }
        return false;
    }


    public boolean setTeamPrivilige(TeamMember teamMember) {
        if (teamMember != null && teamMember.getTeamName() != null && teamMember.getUserName() != null )
            return teamMapper.setPrivilege(teamMember);
        return false;
    }

    public boolean createNotice(TeamNotice teamNotice) {
        if (IsNull.addteamNoticeInfoIsOk(teamNotice)){
            List<TeamMember> listOfTeamMember = getTeamMembers(teamNotice.getTeamName());
            if (listOfTeamMember == null) return false;
            TeamMember tm = getTeamMember(listOfTeamMember,teamNotice.getCreateBy());
            if (tm == null) return false;
            int privilege=getTeamPrivilege(tm);
            if (privilege >= MANAGER_OF_TEAMMEMBER){
                return teamMapper.addNotice(teamNotice);
            }
        }
        return false;
    }
    public boolean delNotice(TeamNotice teamNotice,String delBy) {
        int privilige ;
        if (IsNull.delTeamNoticeInfoIsOk(teamNotice) ){
            privilige = getTeamPrivilege(teamMapper.getTeamMemberByTeamNameAndUserName(teamNotice.getTeamName() , delBy));
            if (privilige >= 1)
                return  teamMapper.delNotice(teamNotice);
        }
        return false;
    }
    public List<TeamNotice> getTeamNotice(String teamName) {
        return teamMapper.getNoticeByteamName(teamName);
    }
    public TeamNotice getNoticeById(int id) {
        return teamMapper.getNoticeById(id);
    }
    public boolean updateNotice(TeamNotice teamNotice, String updateBy) {
        return teamNotice != null && updateBy != null && teamNotice.getCreateBy().equals(updateBy) && teamMapper.updateNotice(teamNotice);
    }
    public boolean updateTeamRole(TeamMasterHistory teamMasterHistory) {
        if (teamMasterHistory != null) {
            int privilege;
            TeamMember tm;
            List<TeamMember> listOfTeamMember = getTeamMembers(teamMasterHistory.getTeamName());
            if (listOfTeamMember == null) return false;
            tm = getTeamMember(listOfTeamMember, teamMasterHistory.getModifyBy());
            if (tm == null) return false;
            privilege = getTeamPrivilege(tm);
            if (privilege >= 1) {
                return teamMapper.insertTeamMasterHistory(teamMasterHistory);
            }
        }
        return false;
    }
    public int getCounts(List list) {
        return list.size();
    }

}


