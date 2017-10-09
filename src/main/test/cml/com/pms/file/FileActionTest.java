package cml.com.pms.file;

import com.pms.model.file.FileImpl;
import com.pms.service.file.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chenmeiling on 2017/9/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springconfig.xml")
public class FileActionTest {
    FileImpl fileImpl = new FileImpl();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");

    @Resource
    FileService fileService;

    @Test
    public void insertFileInfo() throws Exception {
        String date = simpleDateFormat.format(new Date());
        System.out.println("时间格式："+date);
        fileImpl.setCreateTime(date);
        fileImpl.setCreateBy("lalala");
        fileImpl.setFileName("fileName");

    }

    @Test
    public void downloadFile() throws Exception {

    }

    @Test
    public void selectByFileName() throws Exception {

    }

    @Test
    public void updateFileInfo() throws Exception {

    }

    @Test
    public void deleteByDelFlag() throws Exception {

    }

    @Test
    public void recoverFile() throws Exception {

    }

    @Test
    public void deleteFile() throws Exception {

    }

}