package cml.com.pms.file;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.FileImpl;
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
public class FileMapperTest {
    @Resource
    FileMapper fileMapper;

    @Test
    public void insertFileInfo() throws Exception {
        FileImpl file = new FileImpl();
        file.setFileName("fileName");
        file.setUrl("位置");
        file.setFileClass("类型");
        file.setSize(1);
        file.setCreateBy("createBy");
        file.setCreateTime("createTime");
        file.setTeamName("teamName");
        file.setDelFlag(true);
        file.setDelTime("delTime");
        file.setIsPrivater(false);
        fileMapper.insertFileInfo(file);
        //Assert.assertEquals();
    }

    @Test
    public void updateFileInfo() throws Exception {
        FileImpl fileImpl = new FileImpl();
        fileImpl.setFileName("文件1");
        fileImpl.setUrl("位置1");
        fileImpl.setFileClass("类型1");
        fileImpl.setSize(1);
        fileImpl.setCreateBy("createBy1");
        fileImpl.setCreateTime("createTime1");
        fileImpl.setTeamName("teamName");
        fileImpl.setDelFlag(true);
        fileImpl.setDelTime("delTime1");
        fileImpl.setIsPrivater(true);
        fileMapper.updateFileInfo(fileImpl);

    }

    @Test
    public void selectByFileName() throws Exception{
        System.out.println(fileMapper.selectByFileName("文件1"));
    }

    @Test
    public void deleteFile() throws Exception {
        FileImpl fileImpl = new FileImpl();
        fileMapper.deleteFile(fileImpl,"fileName","teamName");

    }

    @Test
    public void deleteByDelFlag() throws Exception{
        FileImpl file2 = new FileImpl();
        file2.setFileName("文件2");
        file2.setUrl("位置2");
        file2.setFileClass("类型2");
        file2.setSize(1);
        file2.setCreateBy("createBy2");
        file2.setCreateTime("createTime2");
        file2.setTeamName("teamName");
        file2.setDelFlag(false);
        file2.setDelTime("delTime2");
        file2.setIsPrivater(false);
        fileMapper.deleteByDelFlag(file2);
    }

    @Test
    public void recoverFile() throws Exception{
        FileImpl file3 = new FileImpl();
        file3.setFileName("文件3");
        file3.setUrl("位置3");
        file3.setFileClass("类型3");
        file3.setSize(1);
        file3.setCreateBy("createBy3");
        file3.setCreateTime("createTime3");
        file3.setTeamName("teamName");
        file3.setDelFlag(true);
        file3.setDelTime("delTime3");
        file3.setIsPrivater(false);
        fileMapper.recoverFile(file3);
    }

}