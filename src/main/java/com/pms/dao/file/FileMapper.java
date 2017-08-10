package com.pms.dao.file;


import com.pms.model.file.File;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/1.
 */
@Repository
public interface FileMapper {
    public File selectByFileName(String fileName);   //通过文件名称得到文件相关信息

    public int deleteByFileName(File file);     //文件删除

    public int insertByFileName(File file);    //文件上传

    public int updateFileInfo(File file);     //文件信息更改

}
