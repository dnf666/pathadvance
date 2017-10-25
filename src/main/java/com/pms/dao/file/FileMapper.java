package com.pms.dao.file;


import com.pms.model.file.FileImpl;
import org.springframework.stereotype.Repository;

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
    FileImpl selectByFileId(int fileId);

    /**
     * 文件删除
     * @param fileId
     * @return
     */
    boolean deleteFile(int fileId);

    /**
     * 文件上传
     * @param fileImpl
     * @return
     */
    boolean insertFileInfo(FileImpl fileImpl);


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
