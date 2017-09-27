package com.pms.dao.file;


import com.pms.model.file.FileImpl;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<FileImpl> selectByFileName(String fileName);

    /**
     * 文件删除
     * @param fileName,teamName
     * @return
     */
    boolean deleteFile(FileImpl fileImpl, @Param("fileName") String fileName, @Param("teamName") String teamName);

    /**
     * 文件上传
     * @param file
     * @return
     */
    boolean  insertFileInfo(FileImpl file);


    /**
     * 文件信息更改
     * @param file
     * @return
     */
    boolean updateFileInfo(FileImpl file);


    /**
     * 文件删除，只是修改删除标记
     * @param fileImpl
     * @return
     */
    boolean deleteByDelFlag(FileImpl fileImpl);

    /**
     * 删除文件的恢复，修改删除标记
     * @param fileImpl
     * @return
     */
    boolean recoverFile(FileImpl fileImpl);

}
