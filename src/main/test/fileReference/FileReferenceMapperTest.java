package fileReference;

import com.pms.dao.fileReference.FileReferenceMapper;
import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.team.Team;
import com.pms.model.user.User;
import com.pms.service.FileReference.FileReferenceService;
import com.pms.service.project.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class FileReferenceMapperTest {

    @Autowired
    FileReferenceMapper fileReferenceMapper;

    @Test
    public void getFilesByProjectIdTest(){

    }

    //测试完成
    @Test
    public void addFileReferenceToProjectTest(){
        if (fileReferenceMapper.addFileReferenceToProject(4,"梅勇杰", 2)){
            System.out.println("测试成功...");
        }else{
            System.out.println("测试失败");
        }
    }

    //测试完成
    @Test
    public void getProjectByFileIdTest(){
        Project project = fileReferenceMapper.getProjectByFileId(1);
        if (project != null){
            System.out.println(project.toString());
        }
    }

    //测试完成
    @Test
    public void getTeamByFieId(){
        Team team = fileReferenceMapper.getTeamByFieId(1);
    }

    //测试成功
    @Test
    public void getUserByFileId(){
        User user = fileReferenceMapper.getUserByFileId(1);
        if(user != null ){
            System.out.println("测试成功");
        }else{
            System.out.println("测试失败");
        }
    }

}
