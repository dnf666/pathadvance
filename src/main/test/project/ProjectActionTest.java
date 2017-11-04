package project;

import com.pms.model.project.Project;
import com.pms.service.project.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class ProjectActionTest {

    @Autowired
    ProjectService projectService;

    @Test
    public void testShowAllProjects(){
        Project project = projectService.getProject(1);
            System.out.println(project.getProjectName());
    }

    @Test
    public void getProMemsTest(){

    }

}
