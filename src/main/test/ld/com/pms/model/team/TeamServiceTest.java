package ld.com.pms.model.team;

import com.pms.dao.teamdao.TeamMapper;
import com.pms.model.project.ProjectMember;
import com.pms.model.team.*;
import com.pms.service.team.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by liudong on 2017/8/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class TeamServiceTest {
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
        System.out.println(teamService.createTeam(team));
    }
    @Test
    public void delTeamTest(){
        Team team=new Team();
        team.setTeamName("队1");
        team.setCreateBy("东东");
        team.setCreateTime("2017810");
        team.setDelFlag(1);
        team.setDelTime("13254124");
        team.setDelRemarks("不知道");
        System.out.println(  teamService.delTeam(team,team.getCreateBy()));

    }
    @Test
    public void inviteMemberTest(){
        TeamMember teamMember=new TeamMember();
        teamMember.setTeamName("队1");
        teamMember.setTeamRole("小菜鸟");
        teamMember.setJoinBy("东东");
        teamMember.setUserName("佳佳");
        teamMember.setJoinTime("455445555");
        //团队权限默认为0，在这里也默认为0
        teamMember.setTeamPrivelige(0);
        System.out.println(teamService.inviteMember(teamMember));
    }
    @Test
    public void delMemberTest(){
        TeamMember teamMember=new TeamMember();
        teamMember.setTeamName("队1");
        teamMember.setTeamRole("小菜鸟");
        teamMember.setJoinBy("东东");
        teamMember.setUserName("佳佳");
        teamMember.setJoinTime("455445555");
        teamMember.setDelFlag(1);
        teamMember.setDelRemarks("不知道");
        teamMember.setDelTime("212121");
        teamMember.setDelBy("东东");
    }
    @Test
    public void addProjectMemberTest(){
       /* ProjectMember projectMember=new ProjectMember();
        projectMember.setTeamName("队1");
        projectMember.setTeamRole("小菜鸟");
        projectMember.setUserName("小强");
        projectMember.setJoinTime("54545745");
        projectMember.setProjectName("项目1");
        projectMember.setJoinBy("东东");
        System.out.println(teamService.addProjectMember(projectMember,"东东"));
  */  }


    @Test
    public void createNoticeTest(){
        TeamNotice teamNotice=new TeamNotice();
        teamNotice.setTitle("标题");
        teamNotice.setTeamName("队1");
        teamNotice.setContext("公告内容");
        teamNotice.setCreateTime("155555555");
        teamNotice.setCreateBy("东东");
        System.out.println(teamService.createNotice(teamNotice));
    }
    @Test
    public void delNoticeTest(){
        TeamNotice teamNotice=new TeamNotice();
        teamNotice.setTitle("标题");
        teamNotice.setTeamName("队1");
        teamNotice.setContext("公告内容");
        teamNotice.setCreateTime("155555555");
        teamNotice.setCreateBy("东东");
        teamNotice.setDelFlag(1);
        teamNotice.setId(4);
        teamNotice.setDelTime("545454");
        System.out.println(teamService.delNotice(teamNotice,"东东"));
    }
    @Test
    public void updateNoticeTest(){
        TeamNotice teamNotice=new TeamNotice();
        teamNotice.setTitle("标题");
        teamNotice.setTeamName("队1");
        teamNotice.setContext("公告内容,开心点啊");
        teamNotice.setCreateTime("155555555");
        teamNotice.setCreateBy("东东");
        teamNotice.setId(4);
        System.out.println(teamService.updateNotice(teamNotice,"东东"));
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
        System.out.println(teamService.updateTeamRole(teamMasterHistory));
    }
    @Test
    public void getMyteamTest(){
        teamService.getMyteam("刘岽");
    }
    @Test
    public void delNotice(){
        TeamNotice teamNotice=new TeamNotice();
        teamNotice.setId(1);
        teamNotice.setCreateBy("哆122啦阿盟");
        teamNotice.setDelFlag(1);
        teamNotice.setDelTime("1122");
        teamService.delNotice(teamNotice,"哆122啦阿盟");
    }
}
