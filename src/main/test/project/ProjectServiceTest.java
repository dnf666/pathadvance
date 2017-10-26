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
    public void delProjectTest(){

    }
}
