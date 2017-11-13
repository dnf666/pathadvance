package project;

import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.service.project.ProjectService;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class ProjectServiceTest {

    @Resource
    private ProjectService projectService;


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
    public void getProjectMemsTest(){
        List<ProjectMember> projectMembers = projectService.getProMembers(1);
        if(projectMembers != null && projectMembers.size()>0){
            for(ProjectMember projectMem : projectMembers){
                System.out.println(projectMem);
            }
        }else{
            System.out.println("测试失败！");
        }
    }

    @Test
    public void addProjectTest(){
        Project project = new Project.Builder().teamName("我们的队伍").projectName("我们的项目").projectInfo("我们的项目的介绍...")
                .build();
        assertTrue( projectService.addProject(project,"May"));
    }

    @Test
    public void delProjectTest(){
        try {
            projectService.delProject(1,"MEI");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateProjectTest(){
        Project project = new Project.Builder().projectInfo("更新了项目介绍").projectName("我们的项目")
                .teamName("我们的队伍").id(9).build();
        try {
            assertTrue(projectService.updateProject(project,"May"));
        } catch (Exception e) {
            Log.getLog("mei","yong",1);
        }
    }



}
