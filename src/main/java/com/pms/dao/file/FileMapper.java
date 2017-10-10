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
     * 得到文件相关信息
     * @param fileId
     * @return
     */
    List<FileImpl> selectByFileId(int fileId);

    /**
     * 文件删除
     * @param fileId
     * @return
     */
    boolean deleteFile(FileImpl fileImpl, @Param("fileId") int fileId);

    /**
     * 文件上传
     * @param file
     * @return
     */
    boolean  insertFileInfo(FileImpl file);


    /**
     * 文件信息更改
     * @param fileImpl
     * @return
     */
    boolean updateFileInfo(FileImpl fileImpl);


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
