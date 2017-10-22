package project;

import com.pms.dao.project.ProjectMapper;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.List;

/**
 * CreatedBy: liudong
 * On: 2017/9/11.
 * describle:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class ProjectMapperTest {
    @Resource
    private ProjectMapper projectMapper;

    @Test
    public void getPTest(){
        Project project = projectMapper.getProjectById(3);
        System.out.println(project.getCreateBy());
    }




    @Test
<<<<<<< HEAD
    public void getProMemsTest(){
        List<ProjectMember> members = projectMapper.getProjectMembersByProjectId(1);
        if (members.size() > 0){
            System.out.println("members 有成员");
        }else{
            System.out.println("members 没有成员");
        }

        if (members != null){
            System.out.println("连接到了数据库");
        }else{
            System.out.println("没有连接到数据库");
=======
<<<<<<< HEAD
    public void addPMTest(){
        ProjectMember pm = new ProjectMember.Builder().teamRole("成员啊啊啊").joinBy("jiajia").joinTime("999").teamName("ajja").userName("liudddd东").build();
        System.out.println(projectMapper.addProjectMember(pm));
    }
    @Test
    public void delPMTest(){
        ProjectMember pm = new ProjectMember
                .Builder().teamRole("成员啊啊啊").joinBy("jiajia")
                .delFlag(1).delBy("aaaa")
                .joinTime("999")
                .teamName("ajja").userName("aaa东东").build();
        System.out.println(projectMapper.delProjectMember(pm));
    }
    @Test
    public void getPMsTest(){
        List<ProjectMember> list = projectMapper.getProjectMembersByProjectId(1);
        System.out.println(list.get(0).getTeamName());
=======
    public void delProMemberTest(){
        ProjectMember projectMember = new ProjectMember.Builder().userName("MEI")
                .teamName("MeiYongJie").joinTime("2017-10-09").joinBy("ZhangShiRu").projectRole("成员").delFlag(true)
                .build();
        if(projectMapper.delProjectMember(projectMember)){
            System.out.println("通过改变删除标志删除成功！");
>>>>>>> d19daacc1b25671f68251eff4321e038d3917683
        }
>>>>>>> fc958eaab499b1ccdc9dfaa67c75f8d681ed86a0
    }


}
