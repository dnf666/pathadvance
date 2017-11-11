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
    public void getPTest() {
        Project project = projectMapper.getProjectById(3);
        System.out.println(project.getCreateBy());
    }


    @Test
    public void getProMemsTest() {
        List<ProjectMember> members = projectMapper.getProjectMembersByProjectId(1);
        if (members.size() > 0) {
            System.out.println("members 有成员");
        } else {
            System.out.println("members 没有成员");
        }

        if (members != null) {
            System.out.println("连接到了数据库");
        } else {
            System.out.println("没有连接到数据库");
        }
    }

}