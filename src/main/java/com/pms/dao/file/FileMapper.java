package com.pms.dao.file;


import com.pms.model.file.File;
import com.sdicons.json.validator.impl.predicates.Str;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/1.
 */
@Repository
public interface FileMapper {
    public File selectByFileName(String fileName);   //通过文件名称得到文件相关信息

    public int deleteByFileName(String fileName);     //文件删除

    public boolean insertFileInfo(File file);    //文件上传

    public int updateFileInfo(File file);     //文件信息更改

}
