package cml.com.pms.file;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.FileImpl;
import org.junit.Assert;
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
    //@Transactional
    //@Rollback
    public void insertFileInfo() throws Exception {
        FileImpl file = new FileImpl();
        file.setFileId(3);
        file.setFileName("fileName");
        file.setUrl("位置");
        file.setFileClass("类型");
        file.setSize(1);
        file.setCreateBy("createBy");
        file.setCreateTime("createTime");
        file.setDelTime("delTime");
        file.setIsPrivater(false);
        boolean bool = fileMapper.insertFileInfo(file);
        Assert.assertEquals(true,bool);
    }

    @Test
    public void updateFileInfo() throws Exception {
        FileImpl fileImpl = new FileImpl();
        fileImpl.setFileId(4);
        fileImpl.setFileName("文件1");
        fileImpl.setUrl("位置1");
        fileImpl.setFileClass("类型1");
        fileImpl.setSize(1);
        fileImpl.setCreateBy("createBy1");
        fileImpl.setCreateTime("createTime1");
        fileImpl.setDelFlag(0);
        fileImpl.setDelTime("delTime1");
        fileImpl.setIsPrivater(true);
        fileMapper.updateFileInfo(fileImpl);

    }

    @Test
    public void selectByFileId() throws Exception{
        FileImpl result = fileMapper.selectByFileId(1);
        Assert.assertTrue(1 == result.getFileId());
    }

    @Test
    public void deleteFile() throws Exception {
        FileImpl fileImpl = new FileImpl();
        fileImpl.setFileId(3);
        fileMapper.deleteFile(3);

    }

    @Test
    public void deleteByDelFlag() throws Exception{
        FileImpl file2 = new FileImpl();
        file2.setFileId(4);
        file2.setFileName("文件2");
        file2.setUrl("位置2");
        file2.setFileClass("类型2");
        file2.setSize(1);
        file2.setCreateBy("createBy2");
        file2.setCreateTime("createTime2");
        file2.setDelFlag(1);
        file2.setDelTime("delTime2");
        file2.setIsPrivater(false);
        boolean bool = fileMapper.deleteByDelFlag(file2);
        Assert.assertEquals(true,bool);
    }

    @Test
    public void recoverFile() throws Exception{
        FileImpl file3 = new FileImpl();
        file3.setFileId(5);
        file3.setFileName("文件3");
        file3.setUrl("位置3");
        file3.setFileClass("类型3");
        file3.setSize(1);
        file3.setCreateBy("createBy3");
        file3.setCreateTime("createTime3");
        file3.setDelFlag(0);
        file3.setDelTime("delTime3");
        file3.setIsPrivater(false);
        fileMapper.recoverFile(file3);
    }

}