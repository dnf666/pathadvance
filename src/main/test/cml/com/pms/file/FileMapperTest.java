package cml.com.pms.file;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.File;
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
        File file = new File();
        file.setFileName("fileName");
        file.setUrl("位置");
        file.setFileClass("类型");
        file.setSize(1);
        file.setCreateBy("createBy");
        file.setCreateTime("createTime");
        file.setDelFlag(true);
        file.setDelTime("delTime");
        file.setIsPrivater(false);
        fileMapper.insertFileInfo(file);
    }

    @Test
    public void updateFileInfo() throws Exception {
        File file = new File();
        file.setFileName("文件1");
        file.setUrl("位置1");
        file.setFileClass("类型1");
        file.setSize(1);
        file.setCreateBy("createBy1");
        file.setCreateTime("createTime1");
        file.setDelFlag(true);
        file.setDelTime("delTime1");
        file.setIsPrivater(true);
        fileMapper.updateFileInfo(file);

    }

    @Test
    public void selectByFileName() throws Exception{
        System.out.println(fileMapper.selectByFileName("文件1"));
    }

    @Test
    public void deleteByFileName() throws Exception {
        fileMapper.deleteByFileName("文件");

    }

    @Test
    public void deleteByDelFlag() throws Exception{
        File file2 = new File();
        file2.setFileName("文件2");
        file2.setUrl("位置2");
        file2.setFileClass("类型2");
        file2.setSize(1);
        file2.setCreateBy("createBy2");
        file2.setCreateTime("createTime2");
        file2.setDelFlag(true);
        file2.setDelTime("delTime2");
        file2.setIsPrivater(false);
        fileMapper.deleteByDelFlag(file2);
    }

    @Test
    public void recoverByDelFlag() throws Exception{
        File file3 = new File();
        file3.setFileName("文件3");
        file3.setUrl("位置3");
        file3.setFileClass("类型3");
        file3.setSize(1);
        file3.setCreateBy("createBy3");
        file3.setCreateTime("createTime3");
        file3.setDelFlag(false);
        file3.setDelTime("delTime3");
        file3.setIsPrivater(false);
        fileMapper.recoverByDelFlag(file3);
    }

}