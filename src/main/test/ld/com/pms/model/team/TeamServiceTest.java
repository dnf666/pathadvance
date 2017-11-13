package ld.com.pms.model.team;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pms.dao.teamdao.TeamMapper;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.model.team.*;
import com.pms.service.project.ProjectService;
import com.pms.service.team.TeamService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudong on 2017/8/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class TeamServiceTest {
    @Resource
    private ProjectService projectService;
    @Resource
    private TeamMapper teamMapper;
    @Resource
    TeamService teamService;
    @Test
    public void helloTest(){
        System.out.println("hello serviceTest");
    }
    @Test
    public void createTeamTest(){
        Team team=new Team();
        team.setTeamName("队1");
        team.setCreateBy("东东");
        team.setCreateTime("2017810");
        Assert.assertEquals(true,teamService.createTeam(team));
    }
    @Test
    public void delTeamTest(){
        Team team = new Team();
        team.setTeamName("队1");
        team.setCreateBy("东东");
        team.setCreateTime("2017810");
        team.setDelFlag(true);
        team.setDelTime("13254124");
        team.setDelRemarks("不知道");
        Assert.assertEquals(true,teamService.delTeam(team,team.getCreateBy()));
    }
    @Test
    public void inviteMemberTest(){
        TeamMember teamMember = new TeamMember();
        teamMember.setTeamName("队1");
        teamMember.setTeamRole("小菜鸟");
        teamMember.setJoinBy("东");
        teamMember.setUserName("佳佳");
        teamMember.setJoinTime("455445555");
        //团队权限默认为0，在这里也默认为0
        teamMember.setTeamPrivelige(0);
       Assert.assertEquals(true,teamService.inviteMember(teamMember));
    }
    @Test
    public void delMemberTest(){
        TeamMember teamMember=new TeamMember();
        teamMember.setTeamName("队1");
        teamMember.setTeamRole("小菜鸟");
        teamMember.setJoinBy("东东");
        teamMember.setUserName("佳佳");
        teamMember.setJoinTime("455445555");
        teamMember.setDelFlag(true);
        teamMember.setDelRemarks("不知道");
        teamMember.setDelTime("212121");
        teamMember.setDelBy("东东");
        Assert.assertEquals(true, teamService.delTeamMember(teamMember));
    }
    @Test
    public void createNoticeTest(){
        TeamNotice teamNotice = new TeamNotice();
        teamNotice.setTitle("标题");
        teamNotice.setTeamName("队1");
        teamNotice.setContext("公告内容");
        teamNotice.setCreateTime("155555555");
        teamNotice.setCreateBy("东东");
        Assert.assertEquals(true, teamService.createNotice(teamNotice));
    }
    @Test
    public void delNoticeTest(){
        TeamNotice teamNotice=new TeamNotice();
        teamNotice.setTitle("标题");
        teamNotice.setTeamName("队1");
        teamNotice.setContext("公告内容");
        teamNotice.setCreateTime("155555555");
        teamNotice.setCreateBy("东东");
        teamNotice.setDelFlag(true);
        teamNotice.setId(1);
        teamNotice.setDelTime("545454");
        Assert.assertEquals(true, teamService.delNotice(teamNotice,"东东"));
    }
    @Test
    public void updateNoticeTest(){
        TeamNotice teamNotice=new TeamNotice();
        teamNotice.setTitle("标题");
        teamNotice.setTeamName("队1");
        teamNotice.setContext("公告内容,开心点啊");
        teamNotice.setCreateTime("155555555");
        teamNotice.setCreateBy("东东");
        teamNotice.setId(1);
        Assert.assertEquals(true,teamService.updateNotice(teamNotice,"东东"));
    }
    @Test
    public void getTeamNoticeTest(){
        Assert.assertEquals(1, teamService.getTeamNotice("队1").size());
    }
    @Test
    public void getNoticeByIdTest(){
        Assert.assertEquals("标题",teamService.getNoticeById(1).getTitle());
    }

    @Test
    public void updateTeamRoleTest(){
        TeamMasterHistory teamMasterHistory=new TeamMasterHistory();
        teamMasterHistory.setTeamName("队1");
        teamMasterHistory.setFromRole("小菜鸟");
        teamMasterHistory.setModifyAt("15454");
        teamMasterHistory.setToRole("大菜鸟");
        teamMasterHistory.setModifyBy("东东");
        teamMasterHistory.setUserName("佳佳");
        Assert.assertEquals(true, teamService.updateTeamRole(teamMasterHistory));
    }
    @Test
    public void getMyteamTest(){

        Assert.assertEquals(1, teamService.getMyteam("东东").size());
    }
    //没有测试成功哈
    @Test
    public void isProMemberTest(){
        Project project = new Project.Builder()
                .projectName("项目1")
                .teamName("队2")
                .createAt("12545")
                .createBy("东东")
                .projectInfo("项目信息")
                .build();
        Assert.assertEquals(false,  projectService.addProject(project,project.getCreateBy()));

        List<ProjectMember> list = teamService.getProMemberByProID(1);
        System.out.println(list.size());
        String userName = "东东";
        Assert.assertEquals(true, teamService.isProMember(list, userName));
    }
    @Test
    public void getTeamMembersTest(){
        String teamName = "队1";
        Assert.assertEquals(1, teamService.getTeamMembers(teamName).size());
    }

    @Test
    public void getTeamMemberTest(){
        String teamName = "队1";
        List<TeamMember> teamMemberList = teamService.getTeamMembers(teamName);
        String userName = "东东";
        Assert.assertEquals(userName, teamService.getTeamMember(teamMemberList, userName).getUserName());
    }
    @Test
    public void getTeamMemberByTeamNameAndUserNameTest(){
        String teamName = "队1";
        String userName = "东东";
        Assert.assertEquals(userName, teamService.getTeamMemberByTeamNameAndUserName(teamName, userName).getUserName());
    }
    @Test
    public void getDelTeamMemberTest(){
        String teamName = "队1";
        String userName = "东东";
        Assert.assertEquals(userName, teamService.getDelTeamMember(teamName, userName).getUserName());
    }
    @Test
    public void getAllTeaTest(){
        Assert.assertEquals(1, teamService.getAllTeam().size());
    }
    @Test
    public void getMyTeamTest(){
        Assert.assertEquals(1, teamService.getMyteam("东东").size());
    }
    @Test
    public void getManagedTeamTest(){
        Assert.assertEquals(1, teamService.getManagedTeam("东东").size());
    }
    @Test
    public void getJoinTeamTest(){
        Assert.assertEquals(1, teamService.getJoinTeam("佳佳").size());
    }
    @Test
    public void  createProjectTest(){
        Project project = new Project.Builder()
                .teamName("队1")
                .createBy("东东")
                .createAt("1445456")
                .projectName("大主宰")
                .projectInfo("一本小说")
                .build();
        Assert.assertEquals(true, teamService.createProject(project));
    }
    @Test
    public void addProjectMemberTest(){
        ProjectMember projectMember = new ProjectMember();
        projectMember.setTeamName("队1");
        projectMember.setProjectRole("小菜鸟");
        projectMember.setUserName("小强");
        projectMember.setJoinTime("54545745");
        projectMember.setProjectId(4);
        projectMember.setJoinBy("东东");
        Assert.assertEquals(true,teamService.addProjectMember(projectMember));
    }
    @Test
    public void delProjectTest() throws Exception {
        Project project = new Project.Builder()
                .teamName("队1")
                .createBy("东东")
                .createAt("1445456")
                .projectName("大主宰")
                .projectInfo("一本小说")
                .id(4)
                .build();
        Assert.assertEquals(true, teamService.delProject( project, "东东"));
    }
    @Test
    public void delProjectMemberTest(){
        ProjectMember projectMember = new ProjectMember.Builder()
                .teamName("队1")
                .projectRole("小菜鸟")
                .userName("东东")
                .joinTime("54545745")
                .projectId(1)
                .joinBy("东东")
                .delFlag(true)
                .build();
        Assert.assertEquals(true, teamService.delProjectMember(projectMember));
    }
    @Test
    public void setTeamPriviligeTest(){
        TeamMember teamMember=new TeamMember();
        teamMember.setTeamName("队1");
        teamMember.setTeamRole("小菜鸟");
        teamMember.setJoinBy("东东");
        teamMember.setUserName("佳佳");
        teamMember.setJoinTime("455445555");
        teamMember.setTeamPrivelige(1);
        Assert.assertEquals(true, teamService.setTeamPrivilige(teamMember));
    }
    @Test
    public void getTeamProjectsByTeamNameTest(){
        Assert.assertEquals("东东", teamService.getTeamProjectsByTeamName("队1").get(0).getCreateBy());
    }
    @Test
    public void getProjectInfoTest(){
        Assert.assertEquals(true, teamService.getProjectInfo(1));
    }
    @Test
    public void getProMemberByProID(){
        Assert.assertEquals(1, teamService.getProMemberByProID(4).size());
    }
    @Test
    public void updateaProjectTest() throws Exception {
        Project project = new Project.Builder()
                .projectName("项目1")
                .teamName("队2")
                .createAt("0000000000000")
                .createBy("东东")
                .projectInfo("项目信息111")
                .id(1)
                .build();
        Assert.assertEquals(true, teamService.updateaProject(project,"东东"));
    }
//    @Test
//    public void

}
