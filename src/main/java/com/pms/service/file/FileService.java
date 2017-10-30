package com.pms.service.file;

//import com.pms.model.file.File;
import com.pms.model.file.FileImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by Chenmeiling on 2017/8/18.
 */
@Repository
public interface FileService {
    /**
     * 上传文件
     * @param fileImpl
     * @return boolean
     */
    boolean insertFileInfo(FileImpl fileImpl);

    /**
     * 查看文件信息
     * @return boolean
     */
    boolean selectByFileId(int fileId);

    /**
     * 更新文件信息
     * @param fileName
     * @return boolean
     */
    boolean updateFileInfo(String fileName);

    /**
     * 删除文件，只是修改删除标记
     * @param fileId
     * @return boolean
     */
    boolean deleteByDelFlag(int fileId);

    /**
     * 删除文件恢复，即修改删除标记
     * @param fileId
     * @return boolean
     */
    boolean recoverFile(int fileId);

    /**
     * 删除文件
     * @param fileId
     * @return boolean
     */
    boolean deleteFile(int fileId);

    /**
     * 下载文件
     * @param fileId
     * @return boolean
     */
    boolean downloadFile(int fileId);

}
