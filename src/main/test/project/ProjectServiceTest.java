package project;

import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.service.project.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class ProjectServiceTest {

    @Resource
    private ProjectService projectService;

    @Test
    public void addProjectTest(){
        Project project = new Project.Builder().projectInfo("我的项目").projectName("mei").createAt("now")
                .createBy("jie").teamName("yong").build();
        projectService.addProject(project);
    }

    //这个有问题
    @Test
    public void getAllProjectsTest(){
        List<Project> projects = projectService.getAllProjects();
        System.out.print(projects.get(0).getCreateBy());
    }

    @Test
    public void getProjectTest(){
        Project project = projectService.getProject(1);
        System.out.println(project.getCreateBy());
    }

    @Test
    public void addFileTest(){
        FileImpl file = new FileImpl();
        file.setCreateBy("mei");
        file.setCreateTime("now");
        file.setFileName("文件");
        file.setTeamName("梅");
        file.setUrl("www.baidu.com");
        file.setFileClass("class");
        if (projectService.addFile(file)){
            System.out.println("添加文件成功");
        }
    }


    ProjectMember projectMember = new ProjectMember.Builder().projectId(1).joinBy("mei")
            .joinTime("now").teamName("队名").userName("jie").projectRole("成员").build();

    @Test
    public void addProMemberTest() {
      /*  projectMember = new ProjectMember.Builder().projectId(1).joinBy("mei")
                .joinTime("now").teamName("队名").userName("jie").projectRole("成员").build();*/
        if (projectService.addProMember(projectMember)) {
            System.out.println("添加成员成功");
        }
    }

    @Test
    public void deleteProMemberTest() throws Exception{
        if(projectService.deleteProMember("jie", 1 , projectMember)){
            System.out.println("删除成员成功");
        }
    }
}
