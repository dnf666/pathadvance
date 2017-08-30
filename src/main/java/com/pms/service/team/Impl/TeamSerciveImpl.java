package com.pms.service.team.Impl;

import com.pms.dao.teamdao.TeamMapper;
import com.pms.model.file.File;
import com.pms.model.team.*;
import com.pms.service.team.TeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudong on 2017/8/15.
 *
 */
@Service
public class TeamSerciveImpl implements TeamService{
    @Resource
    TeamMapper teamMapper;
    String msg;//错误信息

    public boolean isProMember(List<ProjectMember> list, String userName) {
        if (list.size()!=0){
            for (int i=0;i<list.size();i++){
                if (userName.equals(list.get(i).getUserName())){
                    return true;
                }
            }
        }
        return false;
    }

    public TeamMember getMember(List<TeamMember> list, String userName) {
        if (list.size()==0){
            return null;//传入的list没有任何意义
        }else {
            for (int i=0;i<list.size();i++){
                //通过成员的用户名匹配找到相应的团队成员
                if (userName.equals(list.get(i).getUserName())){
                    return list.get(i);
                }
            }
            return null;//表示没有匹配的团队成员
        }
    }

    public int getTeamPrivilege(TeamMember teamMember, String userName) {
        List<TeamMember> list=teamMapper.getTeamInfoByUserName(userName);
        if (list.size()>0){
            for (int i=0;i<list.size();i++){
                if (list.get(i).getTeamName().equals(teamMember.getTeamName())){
                    return list.get(i).getTeamPrivelige();//返回当前用户在团队中的权限
                }
            }
        }
        return -1;//表示当前团队没有当前用户，直接报错
    }

    public boolean createTeam(Team team) {
            if (!teamMapper.addTeam(team)){
                    msg="团队创建失败";
                    return false;
                }else {
                    if (team.getCreateBy()!=null){
                        //团队创建成功，将当前的创建者存进team_member这张表中，并且权限默认为最高
                        TeamMember teamMember=new TeamMember();
                        teamMember.setTeamName(team.getTeamName());
                        teamMember.setUserName(team.getCreateBy());
                        teamMember.setTeamRole("负责人");
                        teamMember.setJoinTime(team.getCreateTime());
                        teamMember.setJoinBy(team.getCreateBy());
                        teamMember.setTeamPrivelige(2);
                      //测试使用的语句  System.out.println("特权之后");
                        if (teamMapper.addTeamMember(teamMember)){
                            return true;
                        }else {
                            return false;//没有成功的将当前信息存入数据库
                        }
                }else{
                        return false;//当前team对象信息不完整
               }
            }
    }

    public List<Team> getAllTeam() {
        return teamMapper.getAllTeam();
    }

    public List<Team> getMyteam(String userName) {
        List<Team> teamList=new LinkedList<Team>();
        List<TeamMember> teamMemberList=teamMapper.getTeamInfoByUserName(userName);
        if (userName!=null&&teamMemberList!=null&&teamMemberList.size()>0){
            for (int i=0 ; i<teamMemberList.size();i++){
                teamList.add(teamMapper.getTeamInfo(teamMemberList.get(i).getTeamName()));
            }
        }
        return teamList;
    }

    public boolean delTeam(Team team , String delBy) {
        if (getTeamPrivilege(getMember(teamMapper.getTeamMembersByTeamName(team.getTeamName()),delBy),delBy)==2){

            if (teamMapper.delTeam(team)){
                //注销了团队，那么团队当中的成员也会被删
                List<TeamMember> list= teamMapper.getTeamMembersByTeamName(team.getTeamName());//通过团队名称获取当前团队的所有成员
                if (list.size()>0){
                    for (int k=0;k<list.size();k++){
                        //通过循环一个一个删，将del_flag设为1，否则传递的参数是空
                        list.get(k).setDelFlag(1);
                        list.get(k).setDelTime(team.getDelTime());
                        list.get(k).setDelBy(delBy);
                        if (teamMapper.delTeamMember(list.get(k))){
                            ;//不做任何操作,坐等下一次循环
                        }else {
                            //删除任何一个成员失败则返回失败，暂时这样处理。。。
                            return false;
                        }
                    }
                    return true;//所有团队成员都已经成功删除.
                }else {
                    return true;//因为没有可删的队员了，所以删除操作成功
                }
            }else {
                return false;//注销团队失败
            }
        }else {
            return false;//当前的操作用户没有权限进行此操作
        }
    }

    public boolean inviteMember(TeamMember teamMember) {
        //如果团队中已经存在该成员，则不必再次邀请

        if (getMember(teamMapper.getTeamMembersByTeamName(teamMember.getTeamName()),teamMember.getUserName())==null){
        if (getTeamPrivilege(teamMember,teamMember.getJoinBy())>=1){
            if (teamMapper.addTeamMember(teamMember)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;//当前操作用户没有足够的权限
        }
        }else {
            return false;//当前成员已经在列表中了。
        }
    }

    public List<TeamMember> getTeamMembers(String teamName) {
        return teamMapper.getTeamMembersByTeamName(teamName);
    }

    public boolean delMember(TeamMember teamMember) {
        if (getTeamPrivilege(teamMember,teamMember.getDelBy())>=1){
            if (teamMapper.delTeamMember(teamMember)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;//当前操作用户没有足够的权限
        }
    }

    public boolean createProject(TeamProject teamProject) {
        //创建项目必须要有一个团队,首先判断当前用户是否是在当前的团队当中
        //同时将当前成员插入project_member中
        if (teamProject.getTeamName().equals(getMember(teamMapper.getTeamMembersByTeamName(teamProject.getTeamName()),teamProject.getCreateBy()).getTeamName())){
            if (teamMapper.addProject(teamProject)){
                //项目创建成功，在项目成员中添加项目的创建者
                ProjectMember projectMember=new ProjectMember();
                projectMember.setJoinTime(teamProject.getCreateAt());
                projectMember.setTeamName(teamProject.getTeamName());
                projectMember.setUserName(teamProject.getCreateBy());
                projectMember.setTeamRole(getMember(teamMapper.getTeamMembersByTeamName(teamProject.getTeamName()),teamProject.getCreateBy()).getTeamRole());
                projectMember.setProjectName(teamProject.getProjectName());
                projectMember.setJoinBy(teamProject.getCreateBy());
                return teamMapper.addProjectMember(projectMember);
            }
        }
        return false;
    }

    public boolean delProject(TeamProject teamProject, String delBy) {
        //只有创建者才能够删除自己的项目,并且同时删除项目所有成员
            if(teamMapper.delProject(teamProject)){
                //首先得到当前项目下的所有成员，然后再逐个删除
                List<ProjectMember> list=teamMapper.getProjectMembersByProject(teamProject.getTeamName(),teamProject.getProjectName());
                if (list!=null){
                    for (int k=0;k<list.size();k++){
                        list.get(k).setDelFlag(1);
                        list.get(k).setDelBy(delBy);
                        if (!teamMapper.delProjectMember(list.get(k))){
                            return false;
                        }
                    }
                    return true;
                }
            }
        return false;
    }

    public boolean setTeamPrivilige(TeamMember teamMember) {
           return teamMapper.setPrivilege(teamMember);
    }

    public List<TeamProject> getTeamProjectsByTeamName(String teamName) {
        return teamMapper.getProjectInfoByTeamName(teamName);
    }

    public TeamProject getTeamProjectsById(int id) {
        return teamMapper.getProjectInfoByProjectId(id);
    }

    public List<ProjectMember> getProMemberByTeamNameAndProjectName(String teamName, String projectName) {
        return teamMapper.getProjectMembersByProject(teamName,projectName);
    }

    public boolean updateaProject(TeamProject teamProject, String updateBy) {
        //更新项目内容，必须是项目中的人，这里得到该项目是有点问题的。。。。
        if(isProMember(teamMapper.getProjectMembersByProject(teamProject.getTeamName(),teamProject.getProjectName()),updateBy)){
           return teamMapper.updateProject(teamProject);
        }
        return false;
    }

    public boolean addProjectMember(ProjectMember projectMember, String inviteBy) {
        //首先判断该项目中是否存在此成员，存在则不必再添加,没有写哈

        //添加项目成员，必须是项目中的人
        if(isProMember(teamMapper.getProjectMembersByProject(projectMember.getTeamName(),projectMember.getProjectName()),inviteBy)){
            return teamMapper.addProjectMember(projectMember);
        }
        return false;
    }

    public boolean delProjectMember(ProjectMember projectMember,int projectId) {
        //必须是项目的创建者才能够删除项目成员

        if(isProMember(teamMapper.getProjectMembersByProject(projectMember.getTeamName(),projectMember.getProjectName()),projectMember.getDelBy())&&projectMember.getDelBy().equals(teamMapper.getProjectInfoByProjectId(projectId).getCreateBy())){

            //如果数据库里没有该项目的id，则会抛出一个空指针异常
            return teamMapper.delProjectMember(projectMember);
        }
        return false;
    }

    public boolean createNotice(TeamNotice teamNotice) {
        if (getTeamPrivilege(getMember(teamMapper.getTeamMembersByTeamName(teamNotice.getTeamName()),teamNotice.getCreateBy()),teamNotice.getCreateBy())>=1){
                        return teamMapper.addNotice(teamNotice);
        }
        return false;
    }

    public boolean delNotice(TeamNotice teamNotice,String delBy) {
        if (getTeamPrivilege(getMember(teamMapper.getTeamMembersByTeamName(teamNotice.getTeamName()),delBy),delBy)>=1){
            return teamMapper.delNotice(teamNotice);
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
        if (getTeamPrivilege(getMember(teamMapper.getTeamMembersByTeamName(teamNotice.getTeamName()),updateBy),updateBy)>=1){
            return teamMapper.updateNotice(teamNotice);
        }
        return false;
    }

    public boolean updateTeamRole(TeamMasterHistory teamMasterHistory) {
        //更改相关成员的职务得有相关的权限
        if (getTeamPrivilege(getMember(teamMapper.getTeamMembersByTeamName(teamMasterHistory.getTeamName()),teamMasterHistory.getUserName()),teamMasterHistory.getModifyBy())>=1){
            return teamMapper.insertTeamMasterHistory(teamMasterHistory);
          }
          return false;
    }

    public String getMessage() {
        return (msg == null) ? null:msg;
    }
}
