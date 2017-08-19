package cml.com.pms.file;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.File;
import com.pms.service.file.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Chenmeiling on 2017/8/19.
 */
@ContextConfiguration(locations = "classpath:springconfig.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FileServiceTest {
    @Resource
    FileService fileService;
    @Resource
    FileMapper fileMapper;

    @Test
    public void insertFileInfo() throws Exception {
        File file = new File();
        file.setFileName("file");
        file.setUrl("url");
        file.setSize(1);
        file.setCreateBy("createBy");
        file.setCreateTime("createTime");
        file.setFileClass("text");
        file.setDelFlag(true);
        file.setDelTime("delTime");
        file.setIsPrivater(true);
        fileService.insertFileInfo(file);

    }

    @Test
    public void selectByFileName() throws Exception {
        File file = new File();
        fileService.selectByFileName((List<File>) file,"file");

    }

    @Test
    public void updateFileInfo() throws Exception {
        File file1 = new File();
        file1.setFileName("file1");
        file1.setUrl("url1");
        file1.setSize(1);
        file1.setCreateBy("createBy1");
        file1.setCreateTime("createTime1");
        file1.setFileClass("text1");
        file1.setDelFlag(true);
        file1.setDelTime("delTime1");
        file1.setIsPrivater(true);
        fileService.updateFileInfo(file1);

    }

    @Test
    public void deleteByDelFlag() throws Exception {
        File file1 = new File();
        file1.setFileName("file1");
        file1.setUrl("url1");
        file1.setSize(1);
        file1.setCreateBy("createBy1");
        file1.setCreateTime("createTime1");
        file1.setFileClass("text1");
        file1.setDelFlag(true);
        file1.setDelTime("delTime1");
        file1.setIsPrivater(true);
        fileService.deleteByDelFlag(file1);

    }

    @Test
    public void recoverByDelFlag() throws Exception {
        File file2 = new File();
        file2.setFileName("file2");
        file2.setUrl("url2");
        file2.setSize(1);
        file2.setCreateBy("createBy2");
        file2.setCreateTime("createTime2");
        file2.setFileClass("text2");
        file2.setDelFlag(false);
        file2.setDelTime("delTime2");
        file2.setIsPrivater(true);
        fileService.recoverByDelFlag(file2);

    }

    @Test
    public void deleteByFileName() throws Exception{
        fileService.deleteByFileName("file2");

    }

}