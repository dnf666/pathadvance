package ld.com.pms.model.team;

import com.pms.dao.teamdao.TeamMapper;
import com.pms.model.team.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudong on 2017/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class TeamMapperTest {
    @Resource
    TeamMapper teamMapper;
        @Test
        public void testRunWith() throws Exception {
            System.out.println("hello spring Junit");
        }
        //团队方面的测试
        @Test
        public void addTeamTest(){//创建团队的接口测试
            Team team=new Team();
            team.setTeamName("公测团队5");
            team.setCreateBy("刘岽");
            team.setCreateTime("2017810");
            team.setDelTime("2019555");
            teamMapper.addTeam(team);
        }
        @Test
        public void delTeamTest(){//有错误
            Team team=new Team();
            team.setTeamName("公测团队2");
            team.setCreateBy("刘岽");
            team.setDelRemarks("太帅");
            team.setDelFlag(1);
            team.setDelTime("2019666");
            teamMapper.delTeam(team);
        }
        @Test
        public void getTeamInfoTest() {
            String teamName = "公测团队";
            Team team1 = teamMapper.getTeamInfo(teamName);
            if (team1 != null) {
                System.out.println(team1.getTeamName());
                System.out.println(team1);
            }else{
                System.out.println("没有该团队");
            }
        }
        @Test
        public void getAllTeamTest(){
            System.out.println(teamMapper.getAllTeam().size());
        }
        //团队成员方面的测试
        @Test
        public void addTeamMemberTest(){
            TeamMember teamMember=new TeamMember();
            teamMember.setTeamName("公测团队2");
            teamMember.setTeamRole("boss");
            teamMember.setUserName("小小");
            teamMember.setJoinBy("小丁");
            teamMember.setJoinTime("2014252255");
            teamMapper.addTeamMember(teamMember);
        }
        @Test
        public void delTeamMemberTest(){
            TeamMember teamMember=new TeamMember();
            teamMember.setUserName("胖虎");
            teamMember.setTeamName("公测团队");
            teamMember.setDelFlag(1);
            teamMember.setDelRemarks("太胖");
            teamMember.setDelTime("44444");
            teamMapper.delTeamMember(teamMember);
        }
        @Test
        public void getTeamMembersByTeamNameTest(){
            String teamName="公测团队2";
            List<TeamMember> list=teamMapper.getTeamMembersByTeamName(teamName);
            System.out.println(list.get(3));
        }
        @Test
        public void getTeamMemberInfoByNameTest(){
            String userName="静香";
            List<TeamMember> list=teamMapper.getTeamInfoByUserName(userName);
            System.out.println(list.size());
        }
        //团队项目方面的测试
        @Test
        public void addProjectTest(){
            TeamProject teamProject=new TeamProject();
            teamProject.setTeamName("公测团队5");
            teamProject.setCreateAt("1545646");
            teamProject.setCreateBy("大熊");
            teamProject.setDelFlag(0);
            teamProject.setProjectInfo("这是最棒的项目了");
            teamProject.setProjectName("哆啦A梦大结局");
            teamMapper.addProject(teamProject);
        }
        @Test
        public void delProjectTest(){
            TeamProject teamProject=new TeamProject();
            teamProject.setTeamName("公测团队4");
            teamProject.setCreateAt("1545646");
            teamProject.setCreateBy("大熊");
            teamProject.setProjectInfo("这是最棒的项目了");
            teamProject.setProjectName("哆啦A梦大结局");
            teamProject.setDelFlag(1);
            teamMapper.delProject(teamProject);
        }
        @Test
        public void updateProjectTest(){
            TeamProject teamProject=new TeamProject();
            teamProject.setTeamName("公测团队5");
            teamProject.setProjectInfo("tomcat");
            teamProject.setProjectName("哆啦A梦大结局");
            teamMapper.updateProject(teamProject);
        }
        @Test
        public void getProjectInfoByTeamNameTest(){
            String teamName="公测团队5";
            List<TeamProject> list=teamMapper.getProjectInfoByTeamName(teamName);
            System.out.println(list.get(0).getProjectName());
        }
        @Test
        public void getProjectInfoByProjectIdTest(){
            int projectId=3;
            TeamProject teamProject=teamMapper.getProjectInfoByProjectId(projectId);
            System.out.println(teamProject.getProjectName());
        }
        //项目成员方面的测试
        @Test
        public void addProjectMemberTest(){
            ProjectMember projectMember=new ProjectMember();
            projectMember.setTeamRole("大佬");
            projectMember.setProjectName("123");
            projectMember.setJoinBy("小小");
            projectMember.setJoinTime("4545646");
            projectMember.setUserName("mingming");
            teamMapper.addProjectMember(projectMember);
        }
        @Test
        public void delProjectMemberTest(){
            ProjectMember projectMember=new ProjectMember();
            projectMember.setTeamRole("大佬");
            projectMember.setProjectName("公测团队");
            projectMember.setJoinBy("小小");
            projectMember.setJoinTime("4545646");
            projectMember.setUserName("加加");
            projectMember.setDelFlag(1);
            teamMapper.delProjectMember(projectMember);
        }

        @Test
        public void getaddProjectMembersByProjectTest(){
            String projectName="123";
            List<ProjectMember> projectMember=teamMapper.getProjectMembersByProject("44",projectName);
            System.out.println(projectMember.get(0).getTeamName());
        }
        //团队公告的测试
        @Test
        public void  addNoticeTest(){
            TeamNotice teamNotice=new TeamNotice();
            teamNotice.setTeamName("公测团队");
            teamNotice.setCreateBy("哆啦阿盟");
            teamNotice.setCreateTime("222524155");
            teamNotice.setContext("this is a notice");
            teamNotice.setTitle("超级公告");
            teamMapper.addNotice(teamNotice);
        }
        @Test
        public void  delNoticeTest(){
            TeamNotice teamNotice=new TeamNotice();
            teamNotice.setTeamName("公测团队");
            teamNotice.setCreateBy("哆啦阿盟");
            teamNotice.setCreateTime("222524155");
            teamNotice.setContext("this is a notice");
            teamNotice.setTitle("超级公告");
            teamNotice.setDelFlag(1);
            teamNotice.setId(2);
            teamMapper.delNotice(teamNotice);
        }
        @Test
        public void  updateNoticeTest(){
            TeamNotice teamNotice=new TeamNotice();
            teamNotice.setTeamName("公测团队");
            teamNotice.setCreateBy("哆122啦阿盟");
            teamNotice.setCreateTime("6666");
            teamNotice.setContext("this is a notice");
            teamNotice.setTitle("超级公告");
            teamNotice.setId(2);
            teamMapper.updateNotice(teamNotice);
        }
        @Test
        public void getNoticeByIdTest(){
            System.out.println(teamMapper.getNoticeById(3));
        }
        @Test
        public void  getNoticeByteamNameTest(){
            List<TeamNotice> list=teamMapper.getNoticeByteamName("公测团队");
            System.out.println(list.size());
        }
}
