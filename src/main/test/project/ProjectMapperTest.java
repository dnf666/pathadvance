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
    public void addPTest(){
        System.out.println("dddddddd");
        Project p = new Project.Builder().createAt("123")
                .createBy("bailixiang").projectInfo("xiangmuxiangmu")
                .teamName("hahah").projectName("projectanam").build();
        System.out.println(projectMapper.addProject(p));
        System.out.println("dddd");
    }
    @Test
    public void delPTest(){
        Project p = new Project.Builder().createAt("123").createBy("bailixiang").projectInfo("xiangmuxiangmu")
                .teamName("hahah").projectName("projectanam").id(2).delFlag(true).build();
        projectMapper.delProject(p);
    }
    @Test
    public void updatePTest(){
        Project p = new Project.Builder().createAt("123").createBy("bailixiang").projectInfo("xiangmuxiangmu")
                .teamName("hahah").projectName("projectanam").id(3).build();
        projectMapper.updateProject(p);
    }
    @Test
    public void getPTest(){
        Project project = projectMapper.getProjectById(3);
        System.out.println(project.getCreateBy());
    }
    @Test
    public void getPsTest(){
        List<Project> list = projectMapper.getProjectsByTeamName("ddd");
        System.out.println(list.get(0).getCreateBy());
    }

    @Test
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
        }
>>>>>>> fc958eaab499b1ccdc9dfaa67c75f8d681ed86a0
    }


}
