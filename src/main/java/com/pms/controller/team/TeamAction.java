package com.pms.controller.team;
import com.pms.model.team.*;
import com.pms.service.team.TeamService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import com.pms.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;

/**
 * Created by liudong on 2017/8/22.
 */
@Controller
public class TeamAction{
    @Autowired
    private TeamService teamService;

    /**1
     * 团队创建
     * @param team 团队  eam需要设值的参数：teamName , createBy , createTime
     *
     * createBy:刘岽
     * http://localhost:8080/team/addteam.do?teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&createBy=%E5%88%98%E5%B2%BD&createTime=123434
     */
    @RequestMapping("/team/addteam.do")
    public void addTeam(Team team){
        Map map;
        if (team != null && team.getTeamName() != null && team.getCreateBy() != null && team.getCreateTime() != null){
            map = teamService.createTeam(team) ? MapUtil.toMap(1,"success",team) : MapUtil.toMap(-1,"false",null);
        }else {
            map=MapUtil.toMap(0,"false",null);//团队创建失败，并且不反悔任何信息
        }
        JsonUtil.toJSON(map);
    }

    /**2
     * 展示所有团队的信息
     *
     * 测试数据：涉及到team表。
     *
     * http://localhost:8080/team/show.do ? page = 1
     */
    @RequestMapping(value = "/team/show.do")
    public void showTeams(int page){
        Map map;
        List<Team> listOfTeam=teamService.getAllTeam();
        map = (page != 0) ? MapUtil.toMap(1,"success", PageList.getList(page,listOfTeam)):MapUtil.toMap(0,"false",null);
        JsonUtil.toJSON(map);
    }
    /**3
     * 展示团队成员所在团队的信息
     *
     * http://localhost:8080/myteam/show.do?userName=%E5%88%98%E5%B2%BD
     */
    @RequestMapping(value = "/myteam/show.do")
    public void myteamShow(String userName){
        Map map;
        if (userName != null){
            List<Team> listOfMyteam = teamService.getMyteam(userName);
            map =  MapUtil.toMap(1,"success",listOfMyteam);
        }else {
            map = MapUtil.toMap(0,"false",null);
        }
        JsonUtil.toJSON(map);
    }

    /**4
     * 注销团队
     * @param team  团队 team需要设值的参数：teamName , delFlag , delTime , delRemarks
     * @param delBy 删除者
     *
     * 测试数据：涉及到team,team_member表。
     *
     * http://localhost:8080/team/delteam.do?teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&deFlag=1&delRemarks=%E8%AF%95%E9%AA%8C&delBy=%E5%88%98%E5%B2%BD&delTime=123
     */
    @RequestMapping(value = "/team/delteam.do")
    public void delTeam(Team team,String delBy){
        Map map;
        if (delBy != null && team !=null && team.getTeamName() != null && team.getDelTime() != null && team.getDelRemarks() != null){
            map = (teamService.delTeam(team,delBy) ? MapUtil.toMap(1,"success",null) : MapUtil.toMap(0,"false",null));
        } else {
            map = MapUtil.toMap(0,"false",null);
        }
        JsonUtil.toJSON(map);
    }

    /**1
     * 展示团队公告
     * @param teamName 团队名称
     */
    @RequestMapping(value = "/notice/show.do")
    public void showNotice(String teamName){
        Map map;
        if (teamName != null){
            List<TeamNotice> listOfTeamNotice=teamService.getTeamNotice(teamName);
            map=MapUtil.toMap(1,"success",listOfTeamNotice);
        } else {
            map=MapUtil.toMap(0,"false",null);
        }
        JsonUtil.toJSON(map);
    }

    /**2
     * 展示选定公告的详细信息
     * @param id 公告id
     *
     * http://localhost:8080/notice/showinfomation.do?id=1
     */
    @RequestMapping(value = "notice/showinfomation.do")
    public void getNoticeById(int id){
        Map map;
        TeamNotice teamNotice=teamService.getNoticeById(id);
        map = (teamNotice!=null) ? MapUtil.toMap(1,"success",teamNotice) : MapUtil.toMap(0,"false",null);
        JsonUtil.toJSON(map);
    }

    /**3
     * 创建一个新公告
     * @param teamNotice 团队公告  teamNotice需要设值的参数： title , createBy , createTime , context , teamName ,
     * localhost:8080/notice/addnotice.do?title=公告&createBy=刘岽&createTime=123456&context=内容内容&teamName=团队名称
     */
    @RequestMapping(value = "/notice/addnotice.do")
    public void addNotice(TeamNotice teamNotice){
        Map map;
        if (teamNotice.getCreateBy() != null && teamNotice.getTeamName() != null && teamNotice.getContext() != null && teamNotice.getCreateTime() != null){
            map = (teamService.createNotice(teamNotice)) ? MapUtil.toMap(1,"success",null) : MapUtil.toMap(0,"false",null);
        }else {
            map=MapUtil.toMap(0,"false",null);
        }
        JsonUtil.toJSON(map);
    }

    /**4
     * 删除团队公告,
     * @param teamNotice  teamNotice需要设值的参数：id (团队公告的id) ， delFlag ,delTime
     *只需要传入公告的id，teamName,和delBy就可以成功删除公告
     *http://localhost:8080/notice/delnotice.do?delFlag=1&delTime=1222&teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&delBy=%E5%88%98%E5%B2%BD&id=3
     */
    @RequestMapping(value = "/notice/delnotice.do")
    public void delNotice(TeamNotice teamNotice,String delBy){
        Map map;
        if (teamNotice.getTeamName() != null){
                map = (teamService.delNotice(teamNotice,delBy)) ? MapUtil.toMap(1,"success",null) : MapUtil.toMap(0,"false",null);
        }else {
            map=MapUtil.toMap(0,"false",null);
        }
        JsonUtil.toJSON(map);
    }

    /**5
     * 更新团队公告
     * @param teamNotice 团队公告  teamNotice需要设值的参数：id (团队公告的id) ， title , createBy , createTime , context
     * @param updateBy 更新者
     *
     * http://localhost:8080/notice/reeditnotice.do?id=1&title=%E5%85%AC%E5%91%8A&createBy=%E5%88%98%E5%B2%BD&createTime=123456&context=%E5%86%85ddddddddd%E5%AE%B9&teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&updateBy=%E5%88%98%E5%B2%BD
     */
    @RequestMapping(value = "/notice/reeditnotice.do")
    public void updateNotice(TeamNotice teamNotice,String updateBy){
        Map map;
        if (teamNotice.getTeamName() != null && teamNotice.getContext() != null && teamNotice.getCreateTime() != null){
            map = teamService.updateNotice(teamNotice,updateBy) ? MapUtil.toMap(1,"success",teamNotice) : MapUtil.toMap(0,"false",null);
        }else {
            map = MapUtil.toMap(0,"false",null);
        }
        JsonUtil.toJSON(map);
    }

    /**1
     * 展示团队成员
     * @param teamName 团队名称
     *
     * http://localhost:8080/teammember/show.do?teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0
     */
    @RequestMapping(value = "/teammember/show.do")
    public void showTeamMember(String teamName){
        Map map;
        if (teamName != null){
            List<TeamMember> list=teamService.getTeamMembers(teamName);
            map = ( list != null ) ? MapUtil.toMap(1,"success",list): MapUtil.toMap(0,"false",null);
        }else{
            map =  MapUtil.toMap(0,"false",null);
        }

        JsonUtil.toJSON(map);
    }

    /**2
     * 删除团队成员
     * @param teamMember  teamMember需要设值的参数：delFlag , delTime , delRemarks ,teamName ,userName
     *
     *
     *http://localhost:8080/teammember/delmembers.do?teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&userName=%E6%9D%8Exiao%E9%9C%B8&delBy=%E5%88%98%E5%B2%BD&delFlag=1
     */
    @RequestMapping(value = "/teammember/delmembers")
    public void delTeamMember(TeamMember teamMember){
        Map map = (teamService.delTeamMember(teamMember)) ? MapUtil.toMap(1,"success",null)  :  MapUtil.toMap(0,"false",null);
        JsonUtil.toJSON(map);
    }

    /**3
     * 添加团队成员
     * @param teamMember teamMember需要设值的参数：userName , teamName , teamRole , joinTime , joinBy , teamPrivelege
     * http://localhost:8080/teammember/addmembers.do?teamRole=%E8%8F%9C%E9%B8%9F&teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&userName=%E6%9D%8E%E5%85%83%E9%9C%B8&joinTime=2323&joinBy=%E5%88%98%E5%B2%BD
     */
    @RequestMapping(value = "/teammember/addmembers.do")
    public void addTeamMember(TeamMember teamMember){
        Map map;
        if (teamMember != null && teamMember.getTeamName() != null
                && teamMember.getTeamPrivelige() >= 1 && teamMember.getUserName() !=null
                && teamMember.getJoinBy() != null && teamMember.getTeamRole() != null
                && teamMember.getJoinTime() != null){
                map =  (teamService.inviteMember(teamMember)) ? MapUtil.toMap(1,"success",teamMember)  :  MapUtil.toMap(0,"false",null);
        }else {
            map = MapUtil.toMap(0,"false",null);
        }
        JsonUtil.toJSON(map);
    }

//    /**4
//     * 设置团队成员的权限，未完成
//     *
//     * @param setBy
//     * @param setted
//     */
//    @RequestMapping("teammember/updateprivilege")
//    public void updatePrivilege(TeamMember setBy,TeamMember setted){
//        Map map=null;
//        if (teamService.getTeamPrivilege(setBy,setBy.getUserName())==2){
//            if (teamService.setTeamPrivilige(setted)){
//                map=MapUtil.toMap(1,"success",teamService.getMember(teamService.getTeamMembers(setted.getTeamName()),setted.getUserName()));
//            }
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//
//    /**5
//     * 移交超级管理员的权限，未完成
//     *
//     * @param setBy
//     * @param setted
//     */
//    @RequestMapping("/teammember/changeprivilege")
//    public void changePrivilege(TeamMember setBy,TeamMember setted){
//        Map map=null;
//        if (teamService.getTeamPrivilege(setBy,setBy.getUserName())==2){
//            setBy.setTeamPrivelige(2);
//            if (teamService.setTeamPrivilige(setted)&&teamService.setTeamPrivilige(setBy)){
//                List<TeamMember> list=null;
//                list.add(teamService.getMember(teamService.getTeamMembers(setBy.getTeamName()),setBy.getUserName()));
//                list.add(teamService.getMember(teamService.getTeamMembers(setted.getTeamName()),setted.getUserName()));
//                map=MapUtil.toMap(1,"success",list);
//            }
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//
//    /**1
//     * 将团队的所有项目都展示出来
//     * @param teamName
//     *http://localhost:8080/project/show.do?teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0
//     */
//    @RequestMapping(value = "/project/show.do")
//    public void showProjects(int page,String teamName){
//        Map map=null;
//        List<TeamProject> listOfProjects=teamService.getTeamProjectsByTeamName(teamName);
//        if (listOfProjects!=null){
//            map=MapUtil.toMap(1,"success",PageList.getList(page,listOfProjects));
//        }else {
//
//        }
//        if (teamName==null){
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//
//    /**2
//     * 展示指定项目的信息
//     * @param id
//     *
//     * http://localhost:8080/project/showprojectinfomation.do?id=2
//     */
//    @RequestMapping("/project/showprojectinfomation.do")
//    public void showProjectInfomation(int id){
//        Map map=null;
//        TeamProject teamProject=teamService.getTeamProjectsById(id);
//        if (teamProject!=null){
//            map=MapUtil.toMap(1,"success",teamProject);
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//    /**3
//     * 展示指定项目的成员信息
//     * @param teamName
//     * @param projectName
//     * http://localhost:8080/project/showmembers.do?teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&projectName=%E5%A4%A7%E5%A4%A7%E5%A4%A7%E9%A1%B9%E7%9B%AE
//     */
//    @RequestMapping("/project/showmembers.do")
//    public void showProMember(String teamName,String projectName){
//        Map map=null;
//        List<ProjectMember> list=teamService.getProMemberByTeamNameAndProjectName(teamName,projectName);
//        if (list!=null){
//            map=MapUtil.toMap(1,"success",list);
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//
//    /**4
//     * 添加团队项目
//     * @param teamProject
//     *
//     * http://localhost:8080/project/addproject.do?projectName=xiaoxiao%E5%A4%A7%E9%A1%B9%E7%9B%AE&teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&projectInfo=%E9%A1%B6%E9%A1%B6%E9%A1%B6%E9%A1%B6%E9%A1%B6%E9%A1%B6%E9%A1%B6%E9%A1%B6&createBy=%E5%88%98%E5%B2%BD&createAt=2324
//     */
//    @RequestMapping("/project/addproject.do")
//    public void addProject(TeamProject teamProject){
//        Map map=null;
//        if (teamService.createProject(teamProject)){
//            map=MapUtil.toMap(1,"success",null);
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//
//    /**5
//     * 删除团队项目
//     * @param teamProject
//     *http://localhost:8080/project/delproject.do?id=2&delBy=%E5%88%98%E5%B2%BD&teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&delFlag=1&createBy=%E5%88%98%E5%B2%BD&projectName=xiaoxiao%E5%A4%A7%E9%A1%B9%E7%9B%AE
//     */
//    @RequestMapping(value = "/project/delproject")
//    public void delTeamProject(TeamProject teamProject,String delBy){
//        Map map=null;
//        if (teamService.delProject(teamProject,delBy)){
//            map=MapUtil.toMap(1,"success",null);
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//
//    /**6
//     * 删除项目成员
//     * @param projectMember
//     *
//     *http://localhost:8080/project/delmember.do?delRemarks=3gfjkg&delFlag=1&userName=XIAOXIAO&teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&projectName=%E5%A4%A7%E5%A4%A7%E5%A4%A7%E9%A1%B9%E7%9B%AE&teamRole=%E8%8F%9C%E9%B8%9F&joinBy=%E6%9D%8E%E5%85%83%E9%9C%B8&joinTime=2333&delBy=%E5%88%98%E5%B2%BD&id=1&delTime=99999
//     */
//    @RequestMapping(value = "/project/delmember.do")
//    public void delProjectMember(ProjectMember projectMember,int id){
//        Map map=null;
//        if (teamService.delProjectMember(projectMember,id)){
//            map=MapUtil.toMap(1,"success",null);
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//
//    /**
//     * 增添项目成员
//     * @param projectMember
//     *
//     * http://localhost:8080/project/addmember.do?userName=XIAOXIAO&teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&projectName=%E5%A4%A7%E5%A4%A7%E5%A4%A7%E9%A1%B9%E7%9B%AE&teamRole=%E8%8F%9C%E9%B8%9F&joinBy=%E6%9D%8E%E5%85%83%E9%9C%B8&joinTime=2333&inviteBy=%E6%9D%8E%E5%85%83%E9%9C%B8
//     */
//    @RequestMapping(value = "/project/addmember.do")
//    public void addProjectMember(ProjectMember projectMember,String inviteBy){
//        Map map=null;
//        if (teamService.addProjectMember(projectMember,inviteBy)){
//            map=MapUtil.toMap(1,"success",null);
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//
//    /**
//     * 团队成员角色变化记录
//     * @param teamMasterHistory
//     *
//     * http://localhost:8080/team/historymanager.do?teamName=%E5%9B%A2%E9%98%9F%E5%90%8D%E7%A7%B0&userName=XIAOXIAO&fromRole=%E8%8F%9C%E9%B8%9F&toRole=%E5%A4%A7%E5%B8%88&modifyBy=%E6%9D%8E%E5%85%83%E9%9C%B8&ModifyAt=444
//     */
//    @RequestMapping("/team/historymanager.do")
//    public void historyMaster(TeamMasterHistory teamMasterHistory){
//        Map map=null;
//        if (teamService.updateTeamRole(teamMasterHistory)){
//            map=MapUtil.toMap(1,"success",null);
//        }else {
//            map=MapUtil.toMap(0,"false",null);
//        }
//        JsonUtil.toJSON(map);
//    }
//    @RequestMapping( "/team/team/teamaccounts.do")
//    public void getTeamSize(){
//        Map map=null;
//        map=MapUtil.toMap(1,"success",teamService.getCounts(teamService.getAllTeam()));
//        JsonUtil.toJSON(map);
//    }
//    @RequestMapping("/team/project/projectcounts.do")
//    public void getProjectSize(String teamName){
//        Map map=null;
//        map=MapUtil.toMap(1,"success",teamService.getCounts(teamService.getTeamProjectsByTeamName(teamName)));
//        JsonUtil.toJSON(map);
//    }
}
