package cml;

import com.pms.dao.file.FileMapper;
import com.pms.model.file.FileImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Created by Chenmeiling on 2017/8/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class FileMapperTest {
    @Resource
    FileMapper fileMapper;
    @Test
    public void insertFileInfo() throws Exception {
        FileImpl file = new FileImpl();
        file.setFileName("文件");
        file.setUrl("位置");
        file.setFileClass("类型");
        file.setSize(1);
        file.setCreateBy("createBy");
        file.setCreateTime("createTime");
        file.setDelFlag(true);
        file.setDelTime("delTime");
        file.setIsPrivater(true);
        fileMapper.insertFileInfo(file);
    }

    @Test
    public void updateFileInfo() throws Exception {
        FileImpl file = new FileImpl();
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
        //      fileMapper.deleteByFileName("文件");

    }


}