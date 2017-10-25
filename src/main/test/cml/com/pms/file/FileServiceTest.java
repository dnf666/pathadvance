package cml.com.pms.file;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.FileImpl;
import com.pms.service.file.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
        FileImpl file = new FileImpl();
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
<<<<<<< HEAD
    public void selectByFileName() throws Exception {
        FileImpl file1 = new FileImpl();
        FileImpl file2 = new FileImpl();
        FileImpl file3 = new FileImpl();
        List list = new ArrayList();
        list.add(file1);
        list.add(file2);
        list.add(file3);
        fileService.selectByFileId(file1,2);
=======
    public void selectByFileId() throws Exception {
        FileImpl fileImpl = new FileImpl();
        fileImpl.setFileId(9);
        fileImpl.setFileName("wenjian");
        fileImpl.setUrl("weizhi");
        fileImpl.setFileClass("类型1");
        fileImpl.setSize(1);
        fileImpl.setCreateBy("createBy1");
        fileImpl.setCreateTime("createTime1");
        fileImpl.setDelFlag(true);
        fileImpl.setDelTime("delTime1");
        fileImpl.setIsPrivater(true);
//<<<<<<< HEAD
        fileService.selectByFileId(2);
//=======
//        fileService.selectByFileId(2);
//>>>>>>> 045825e6011a0c2f34bafa8296bd668f410f7e91
>>>>>>> master

    }

    @Test
    public void updateFileInfo() throws Exception {
        fileService.updateFileInfo("wenjian");

    }

    @Test
    public void deleteByDelFlag() throws Exception {
        FileImpl file1 = new FileImpl();
        file1.setFileId(3);
        file1.setFileName("file1");
        file1.setUrl("url1");
        file1.setSize(1);
        file1.setCreateBy("createBy1");
        file1.setCreateTime("createTime1");
        file1.setFileClass("text1");
        file1.setDelFlag(true);
        file1.setDelTime("delTime1");
        file1.setIsPrivater(true);
        fileService.deleteByDelFlag(3);

    }

    @Test
    public void recoverFile() throws Exception {
        //FileImpl file2 = new FileImpl();
        fileService.recoverFile(4);

    }

    @Test
    public void deleteFile() throws Exception{
        FileImpl fileImpl = new FileImpl();
        fileImpl.setFileId(14);
        fileService.deleteFile(14);
    }

}