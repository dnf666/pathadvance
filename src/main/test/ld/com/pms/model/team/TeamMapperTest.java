package ld.com.pms.model.team;

import com.pms.dao.teamdao.TeamMapper;
import com.pms.model.project.ProjectMember;
import com.pms.model.team.*;
import org.junit.Assert;
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
    private TeamMapper teamMapper;
        @Test
        public void testRunWith() throws Exception {
            System.out.println("hello spring Junit");
}
        //团队方面的测试
        @Test
        public void addTeamTest(){//创建团队的接口测试
            Team team = new Team.Builder()
                    .teamName("队队")
                    .createBy("刘岽")
                    .createTime("0000")
                    .build();
            boolean re = teamMapper.addTeam(team);
            Assert.assertEquals(true,re);
        }
        @Test
        public void delTeamTest(){//有错误
                Team team = new Team.Builder()
                        .teamName("队1")
                        .createBy("刘岽")
                        .createTime("0000")
                        .delFlag(1)
                        .delRemarks("测试")
                        .delTime("4444")
                        .build();
                boolean re = teamMapper.delTeam(team);
                Assert.assertEquals(true,re);
        }
        @Test
        public void getTeamInfoTest() {
                String teamName = "队1";
                Assert.assertEquals(teamName , teamMapper.getTeamInfo(teamName).getTeamName());
        }
        @Test
        public void getAllTeamTest(){
                int number = 2;//没有被删除的团队的数目
                Assert.assertEquals(number , teamMapper.getAllTeam().size());
                Assert.assertEquals("队1" , teamMapper.getAllTeam().get(0).getTeamName());
        }
        //团队成员方面的测试
        @Test
        public void addTeamMemberTest(){
                TeamMember teamMember = new TeamMember.Builder()
                        .teamName("队1")
                        .teamPrivelige(1)
                        .joinBy("刘d")
                        .userName("刘岽")
                        .teamRole("负责人")
                        .joinTime("111")
                        .build();
                Assert.assertEquals(true , teamMapper.addTeamMember(teamMember));
        }
        @Test
        public void delTeamMemberTest(){
                TeamMember teamMember = new TeamMember.Builder()
                        .teamName("队1")
                        .teamPrivelige(1)
                        .joinBy("刘岽")
                        .userName("刘岽")
                        .teamRole("负责人")
                        .joinTime("111")
                        .delTime("2222")
                        .delFlag(1)
                        .delBy("刘岽")
                        .build();
                Assert.assertEquals(true , teamMapper.delTeamMember(teamMember));
        }
        @Test
        public void getTeamMembersByTeamNameTest(){
                String teamName = "队1";
                int number = 3; //当前团队的成员数量
                Assert.assertEquals(number,teamMapper.getTeamMembersByTeamName(teamName).size());
        }
        @Test
        public void getTeamMemberByTeamNameAndUserNameTest(){
                Assert.assertEquals("liudong",teamMapper.getTeamMemberByTeamNameAndUserName("队1","liudong").getUserName());
        }
        @Test
        public void getTeamMemberInfoByNameTest(){
                int number = 2;//表示当前用户加入了多少个团队
                Assert.assertEquals(number,teamMapper.getTeamInfoByUserName("刘岽").size());
        }
        @Test
        public void getDelTeamMemberTest(){
                Assert.assertNotNull(teamMapper.getDelTeamMember("队1","刘岽"));
        }
        @Test
        public void reAddTeamMemberTest(){
                Assert.assertEquals(true,teamMapper.reAddTeamMember(new TeamMember.Builder().teamName("队1").userName("刘岽").build()));
        }
        //团队公告的测试
        @Test
        public void  addNoticeTest(){
                TeamNotice teamNotice = new TeamNotice.Builder()
                        .teamName("队2")
                        .context("不知道什么内容")
                        .createBy("刘岽")
                        .createTime("5555")
                        .title("啦啦jjb ")
                        .build();
                Assert.assertEquals(true,teamMapper.addNotice(teamNotice));
        }
        @Test
        public void  delNoticeTest(){
                TeamNotice teamNotice = new TeamNotice.Builder()
                        .id(1)
                        .delFlag(1)
                        .delTime("666")
                        .build();
                Assert.assertEquals(true,teamMapper.delNotice(teamNotice));
        }
        @Test
        public void  updateNoticeTest(){
                TeamNotice teamNotice = new TeamNotice.Builder()
                        .id(1)
                        .teamName("队1")
                        .context("不知道什么内容")
                        .createBy("刘岽")
                        .createTime("5555")
                        .title("啦啦kkkk ")
                        .build();
                Assert.assertEquals(true,teamMapper.updateNotice(teamNotice));
        }
        @Test
        public void getNoticeByIdTest(){
                String title = "啦啦kkkk ";
                Assert.assertEquals(title,teamMapper.getNoticeById(1).getTitle());
        }
        @Test
        public void  getNoticeByteamNameTest(){
                int number = 2;//公告的数量
                Assert.assertEquals(number,teamMapper.getNoticeByteamName("队2").size());
        }
        @Test
        public void setPriviligeTest(){
                TeamMember teamMember = new TeamMember.Builder()
                        .teamName("队1")
                        .userName("LD")
                        .teamPrivelige(2)
                        .build();
                Assert.assertEquals(true,teamMapper.setPrivilege(teamMember));
        }
        @Test
        public void insertTeamMasterHistoryTest(){
                TeamMasterHistory teamMasterHistory = new TeamMasterHistory.Builder()
                        .teamName("队1")
                        .fromRole("成员")
                        .ModifyAt("111")
                        .toRole("管理员")
                        .modifyBy("刘岽")
                        .userName("jjj")
                        .build();
                Assert.assertEquals(true,teamMapper.insertTeamMasterHistory(teamMasterHistory));
        }
        @Test
        public void getTeamByIdTest(){
                int id = 1;
                Assert.assertEquals("队1",teamMapper.getTeamById(id).getTeamName());
        }
        @Test
        public void addTeamFileTest(){
                Assert.assertEquals(true, teamMapper.addTeamFile(1,2,3));
        }
        @Test
        public void getFRByFileIdTest(){
                int fileId = 1;
                Assert.assertEquals(2,teamMapper.getFRByFileId(fileId).getTeamId());
        }
        @Test
        public void getFRByTeamIdTest(){
                int teamId = 2;
                Assert.assertEquals(3, teamMapper.getFRByTeamId(teamId).get(0).getUserId());
        }

}
