package fileReference;

import com.pms.model.file.FileImpl;
import com.pms.service.FileReference.FileReferenceService;
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
    FileReferenceService fileReferenceService;

    @Test
    public void getFilesByProjectIdTest(){
        List<FileImpl> files = fileReferenceService.getFilesByProjectId(1);
        if (files!= null && files.size() > 0){
            for (int i = 0; i<files.size();i++){
                System.out.println(files.get(i).getCreateBy());
            }
        }
    }
}
