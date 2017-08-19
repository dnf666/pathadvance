package com.pms.dao.file;


import com.pms.model.file.File;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/1.
 */
@Repository
public interface FileMapper {
    /**
     * 通过文件名称得到文件相关信息
     * @param fileName
     * @return
     */
    public File selectByFileName(String fileName);

    /**
     * 文件删除
     * @param fileName
     * @return
     */
    public boolean deleteByFileName(String fileName);

    /**
     * 文件上传
     * @param file
     * @return
     */
    public boolean  insertFileInfo(File file);

    /**
     * 文件信息更改
     * @param file
     * @return
     */
    public boolean updateFileInfo(File file);


    /**
     * 文件删除，只是修改删除标记
     * @param file
     * @return
     */
    public boolean deleteByDelFlag(File file);

    /**
     * 删除文件的恢复，修改删除标记
     * @param file
     * @return
     */
    public boolean recoverByDelFlag(File file);

}
