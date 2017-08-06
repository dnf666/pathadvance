package com.pms.dao.file;


import com.pms.model.file.File;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/1.
 */
@Repository
public interface FileMapper {
    public File selectByFileName(String fileName);
    public int deleteByFileName(File file);
    public int insertByFileName(File file);
    public int updateFileInfo(File file);

}
