package com.pms.service.team.Impl;

import com.pms.dao.teamdao.TeamMapper;
import com.pms.model.team.*;
import com.pms.service.team.TeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudong on 2017/8/15.
 *
 * @attention 团队项目成员有问题没有姐姐，还有dao层的文件也没有弄清楚
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
                        teamMember.setTeamRole("负责人");
                        teamMember.setJoinTime(team.getCreateTime());
                        teamMember.setJoinBy(team.getCreateBy());
                        teamMember.setTeamPrivelige(2);

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

    public boolean delTeam(Team team ,String delBy) {
        if (getTeamPrivilege(getMember(teamMapper.getTeamMembersByTeamName(team.getTeamName()),delBy),delBy)==2){
            if (teamMapper.delTeam(team)){
                //注销了团队，那么团队当中的成员也会被删
                List<TeamMember> list= teamMapper.getTeamMembersByTeamName(team.getTeamName());//通过团队名称获取当前团队的所有成员
                if (list.size()>0){
                    for (int k=0;k<list.size();k++){
                        //通过循环一个一个删
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
        if (getTeamPrivilege(teamMember,teamMember.getJoinBy())>=1){
            if (teamMapper.addTeamMember(teamMember)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;//当前操作用户没有足够的权限
        }
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
            return teamMapper.addProject(teamProject);
        }
        return false;
    }

    public boolean delProject(TeamProject teamProject, String delBy) {
        if (delBy.equals(teamProject.getCreateBy())){//只有创建者才能够删除自己的项目
            return teamMapper.delProject(teamProject);
        }
        return false;
    }

    public boolean updateaProject(TeamProject teamProject, String updateBy) {
        //更新项目内容，必须是项目中的人，这里得到该项目是有点问题的。。。。
        if(isProMember(teamMapper.getaddProjectMembersByProject(teamProject.getProjectName()),updateBy)){
            teamMapper.updateProject(teamProject);
        }
        return false;
    }

    public boolean addProjectMember(ProjectMember projectMember, String inviteBy) {
        return false;
    }

    public boolean delProjectMember(ProjectMember projectMember, String delBy) {
        return false;
    }

    public boolean createNotice(TeamNotice teamNotice) {
        if (getTeamPrivilege(getMember(teamMapper.getTeamMembersByTeamName(teamNotice.getTeamName()),teamNotice.getCreateBy()),teamNotice.getCreateBy())>=1){
                        return teamMapper.addNotice(teamNotice);
        }
        return false;
    }

    public boolean delNotice(TeamNotice teamNotice) {
        if (getTeamPrivilege(getMember(teamMapper.getTeamMembersByTeamName(teamNotice.getTeamName()),teamNotice.getCreateBy()),teamNotice.getCreateBy())>=1){
            return teamMapper.delNotice(teamNotice);
        }
        return false;
    }

    public boolean updateNotice(TeamNotice teamNotice) {
        return false;
    }
}
