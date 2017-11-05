package com.pms.controller.team;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.model.team.*;
import com.pms.service.team.TeamService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import com.pms.util.team.IsNull;
import com.pms.util.team.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
/**
 * Created by liudong on 2017/8/22.
 */
@Controller
public class TeamAction{
    //团队权限最高为2
    private final int MANANGER = 2;
    @Autowired
    private TeamService teamService;

    /**1
     * 团队创建
     * @param team 团队  team需要设值的参数：teamName , createBy , createTime
     */
    @RequestMapping("/team/addteam.do")
    public void addTeam(Team team){
        Map map;
        //判断团队信息是否齐全
        if (IsNull.addTeamInfoIsOk(team)){
            boolean isOk = teamService.createTeam(team) ;
            if (isOk){
                map = MapUtil.toMap(1,"success",team);
            }
            else {
                map = MapUtil.toMap(-1,"团队创建失败",null);
            }
        }else {
            //团队创建失败，并且不返回任何信息
            map=MapUtil.toMap(0,"团队信息不全",null);
        }
        JsonUtil.toJSON(map);
    }

    /**2
     * 展示所有团队的信息
     */
    @RequestMapping(value = "/team/show.do" )
    public void showTeams(int page){
        //这个方法还有需要改进的，即页码的设置
        Map map;
        List<Team> listOfTeam=teamService.getAllTeam();

        if (page >= 0){
            map = MapUtil.toMap(1,"success", PageList.getList(page,listOfTeam));
        }else {
            map = MapUtil.toMap(0,"传入的页码不对",null);
        }
        JsonUtil.toJSON(map);
    }
    /**3
     * 展示团队成员所在团队的信息
     */
    @RequestMapping(value = "/myteam/show.do")
    public void myteamShow(String userName){
        Map map;
        if (userName != null){
            List<Team> listOfMyteam = teamService.getMyteam(userName);
            map =  MapUtil.toMap(1,"success",listOfMyteam);
        }else {
            map = MapUtil.toMap(0,"用户名为空",null);
        }
        JsonUtil.toJSON(map);
    }

    /**4
     * 注销团队
     */
    @RequestMapping(value = "/team/delteam.do")
    public void delTeam(Team team,String delBy){
        Map map;
        if (delBy != null && IsNull.delTeamInfoIsOk(team) ){
            boolean isOk = teamService.delTeam(team,delBy);
            if (isOk){
                map = MapUtil.toMap(1,"success",null);
            }
            else {
                map = MapUtil.toMap(0,"团队删除操作失败",null);
            }
        } else {
            map = MapUtil.toMap(0,"删除者为空或者传入的团队信息不全",null);
        }
        JsonUtil.toJSON(map);
    }

    /**1
     * 展示团队公告
     */
    @RequestMapping(value = "/notice/show.do")
    public void showNotice(String teamName){
        Map map;
        if (teamName != null){
            List<TeamNotice> listOfTeamNotice = teamService.getTeamNotice(teamName);
            map = MapUtil.toMap(1,"success",listOfTeamNotice);
        } else {
            map = MapUtil.toMap(0,"团队名称为空",null);
        }
        JsonUtil.toJSON(map);
    }

    /**2
     * 展示选定公告的详细信息
     */
    @RequestMapping(value = "notice/showinfomation.do")
    public void getNoticeById(int id){
        Map map;
        TeamNotice teamNotice = teamService.getNoticeById(id);
        boolean isOk = teamNotice != null;
        if (isOk){
            map =  MapUtil.toMap(1,"success",teamNotice);
        }else {
            map = MapUtil.toMap(0, "暂无公告信息", null);
        }
        JsonUtil.toJSON(map);
    }

    /**3
     * 创建一个新公告
     */
    @RequestMapping(value = "/notice/addnotice.do")
    public void addNotice(TeamNotice teamNotice){
        Map map;
        if (IsNull.addteamNoticeInfoIsOk(teamNotice)){
            boolean isOk = teamService.createNotice(teamNotice);
            if (isOk){
                map = MapUtil.toMap(1,"success",null);
            }else {
                map = MapUtil.toMap(0,"创建公告失败",null);
            }
        }else {
            map=MapUtil.toMap(0,"公告信息不全",null);
        }
        JsonUtil.toJSON(map);
    }

    /**4
     * 删除团队公告,
     */
    @RequestMapping(value = "/notice/delnotice.do")
    public void delNotice(TeamNotice teamNotice,String delBy){
        //有点小问题
        Map map;
        if (IsNull.delTeamNoticeInfoIsOk(teamNotice) && delBy != null){
            boolean isOk = teamService.delNotice(teamNotice,delBy);
            if (isOk){
                map = MapUtil.toMap(1,"success",null);
            }else {
                map = MapUtil.toMap(0,"删除公告失败",null);
            }
        }else {
            map=MapUtil.toMap(0,"删除公告的信息或者删除者的信息不全",null);
        }
        JsonUtil.toJSON(map);
    }

    /**5
     * 更新团队公告
     */
    @RequestMapping(value = "/notice/reeditnotice.do")
    public void updateNotice(TeamNotice teamNotice,String updateBy){
        Map map;
        if (IsNull.updateaTeamNoticeInfoIsOK(teamNotice) && updateBy != null){
            boolean isOk = teamService.updateNotice(teamNotice,updateBy);
            if (isOk){
                map = MapUtil.toMap(1,"success",teamNotice);
            }else {
                map =  MapUtil.toMap(0,"更新公告失败",null);
            }
        }else {
            map = MapUtil.toMap(0,"公告信息不全或者没有传入更新者",null);
        }
        JsonUtil.toJSON(map);
    }

    /**1
     * 展示团队成员
     */
    @RequestMapping(value = "/teammember/show.do")
    public void showTeamMember(String teamName){
        Map map;
        if (teamName != null){
            List<TeamMember> list = teamService.getTeamMembers(teamName);
            if (list != null){
                map =  MapUtil.toMap(1,"success",list);
            }else {
                map = MapUtil.toMap(0,"没有该团队的成员",null);
            }
        }else{
            map =  MapUtil.toMap(0,"团队名为空",null);
        }

        JsonUtil.toJSON(map);
    }

    /**2
     * 删除团队成员
     */
    @RequestMapping(value = "/teammember/delmembers")
    public void delTeamMember(TeamMember teamMember){
        Map map =
                (teamService.delTeamMember(teamMember))
                        ?
                        MapUtil.toMap(1,"success",null)
                        :
                        MapUtil.toMap(0,"false",null);
        JsonUtil.toJSON(map);
    }

    /**3
     * 添加团队成员
     */
    @RequestMapping(value = "/teammember/addmembers.do")
    public void addTeamMember(TeamMember teamMember){
        Map map;
        if (IsNull.addTeamMemberInfoIsOK(teamMember)){
                map =  (teamService.inviteMember(teamMember))
                        ?
                        MapUtil.toMap(1,"success",teamMember)
                        :
                        MapUtil.toMap(0,"团队成员添加失败",null);
        }else {
            map = MapUtil.toMap(0,"团队成员信息不全",null);
        }
        JsonUtil.toJSON(map);
    }

    /**4
     * 设置团队成员的权限，未完成
     */
    @RequestMapping("teammember/updateprivilege")
    public void updatePrivilege(TeamMember setBy,TeamMember setted){
        Map map = null;
        if (teamService.getTeamPrivilege(setBy) == MANANGER){
            if (teamService.setTeamPrivilige(setted)){
                map = MapUtil.toMap(1,"success",null);
            }
        }else {
            map = MapUtil.toMap(0,"操作者权限不够",null);
        }
        JsonUtil.toJSON(map);
    }

    /**5
     * 移交负责人的权限，未完成
     *
     * @param setBy 权限移交者
     * @param setted 接受权限者
     */
    @RequestMapping("/teammember/changeprivilege")
    public void changePrivilege(TeamMember setBy,TeamMember setted){
        Map map;
        if (teamService.getTeamPrivilege(setBy) == MANANGER){
            setBy.setTeamPrivelige(setted.getTeamPrivelige());
            setted.setTeamPrivelige(MANANGER);
            if (teamService.setTeamPrivilige(setBy) && teamService.setTeamPrivilige(setted)){
                map = MapUtil.toMap(1,"success",null);
            }else {
                map = MapUtil.toMap(0,"操作失败",null);
            }
        }else {
            map = MapUtil.toMap(0,"操作者权限不够",null);
        }
        JsonUtil.toJSON(map);
    }

    /**1
     * 将团队的所有项目都展示出来
     */
    @RequestMapping(value = "/project/show.do")
    public void showProjects(int page,String teamName){
        Map map;
        List<Project> listOfProjects = teamService.getTeamProjectsByTeamName(teamName);
        if (listOfProjects != null){
            map = MapUtil.toMap(1,"success",PageList.getList(page,listOfProjects));
        }else {
            map = MapUtil.toMap(0,"项目为空或者获取失败",null);
        }
        if (teamName == null){
            map = MapUtil.toMap(0,"团队名称不能为空",null);
        }
        JsonUtil.toJSON(map);
    }

    /**2
     * 展示指定项目的信息
     */
    @RequestMapping("/project/showprojectinfomation.do")
    public void showProjectInfomation(int projectId){
        Map map;
        Project teamProject = teamService.getProjectInfo(projectId);
        if (teamProject != null){
            map = MapUtil.toMap(1,"success",teamProject);
        }else {
            map = MapUtil.toMap(0,"获取项目失败或者没有项目存在",null);
        }
        JsonUtil.toJSON(map);
    }
   /**3
    * 展示指定项目的成员信息
    */
    @RequestMapping("/project/showmembers.do")
    public void showProMember(int projectId){
        Map map;
        List<ProjectMember> list = teamService.getProMemberByProID(projectId);
        if (list != null){
            map = MapUtil.toMap(1,"success",list);
        }else {
            map = MapUtil.toMap(0,"没有找到项目成员或者操作失败",null);
        }
        JsonUtil.toJSON(map);
    }

    /**4
     * 添加团队项目
     */
    @RequestMapping("/project/addproject.do")
    public void addProject(Project teamProject){
        Map map;
        if (IsNull.addTeamProjectInfoIsOK(teamProject)){
            if (teamService.createProject(teamProject)){
                map = MapUtil.toMap(1,"success",null);
            }else {
                map = MapUtil.toMap(0,"创建项目失败",null);
            }
        }else {
            map = MapUtil.toMap(0,"项目信息不全",null);
        }
        JsonUtil.toJSON(map);
    }

    /**5
     * 删除团队项目
     */
    @RequestMapping(value = "/project/delproject")
    public void delTeamProject(Project teamProject,String delBy) throws Exception {
        Map map;
        if (IsNull.delTeamProjectInfoIsOK(teamProject) && delBy != null){
            if (teamService.delProject(teamProject,delBy)){
                map=MapUtil.toMap(1,"success",null);
            }else {
                map=MapUtil.toMap(0,"操作失败",null);
            }
        }else {
            map=MapUtil.toMap(0,"项目信息不全或者删除者信息为空",null);
        }
        JsonUtil.toJSON(map);
    }

    /**6
     * 删除项目成员
     */
    @RequestMapping(value = "/project/delmember.do")
    public void delProjectMember(ProjectMember projectMember){
        Map map;
        if (IsNull.delProjectMemberInfoIsOK(projectMember)){
            if (teamService.delProjectMember(projectMember)){
                map = MapUtil.toMap(1,"success",null);
            }else {
                map = MapUtil.toMap(0,"操作失败",null);
            }
        }else {
            map = MapUtil.toMap(0,"项目成员信息不全",null);
        }
        JsonUtil.toJSON(map);
    }

    /**
     * 增添项目成员
     */
    @RequestMapping(value = "/project/addmember.do")
    public void addProjectMember(ProjectMember projectMember){
        Map map;
        if (IsNull.addProjectMemberInfoIsOK(projectMember)){
            if (teamService.addProjectMember(projectMember)){
                map = MapUtil.toMap(1,"success",null);
            }else {
                map = MapUtil.toMap(0,"操作失败",null);
            }
        }else {
            map = MapUtil.toMap(0,"项目成员信息不全",null);
        }

        JsonUtil.toJSON(map);
    }

    /**
     * 团队成员角色变化记录
     */
    @RequestMapping("/team/historymanager.do")
    public void historyMaster(TeamMasterHistory teamMasterHistory){
        Map map;
        if (IsNull.updateTeamMasterHistor(teamMasterHistory)){
            if (teamService.updateTeamRole(teamMasterHistory)){
                map = MapUtil.toMap(1,"success",null);
            }else {
                map = MapUtil.toMap(0,"操作失败",null);
            }
        }else {
            map = MapUtil.toMap(0,"信息不全",null);
        }
        JsonUtil.toJSON(map);
    }
    @RequestMapping( "/team/team/teamaccounts.do")
    public void getTeamSize(){
        Map map;
        map = MapUtil.toMap(1,"success",teamService.getAllTeam().size());
        JsonUtil.toJSON(map);
    }
    @RequestMapping("/team/project/projectcounts.do")
    public void getProjectSize(int teamId){
        Map map;
        map = MapUtil.toMap(1,"success",teamService.getProMemberByProID(teamId).size());
        JsonUtil.toJSON(map);
    }
}
