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
        file.setFileId(9);
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
        fileMapper.updateFileInfo("lalala",4);

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
        boolean bool = fileMapper.deleteByDelFlag(4);
        Assert.assertEquals(true,bool);
    }

    @Test
    public void recoverFile() throws Exception{
        fileMapper.recoverFile(4);
    }

}