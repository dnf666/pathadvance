package com.pms.service.file;

//import com.pms.model.file.File;
import com.pms.model.file.FileImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<FileImpl> selectByFileName(String fileName);

    /**
     * 更新文件信息
     * @param fileImpl
     * @return boolean
     */
    boolean updateFileInfo(FileImpl fileImpl);

    /**
     * 删除文件，只是修改删除标记
     * @param fileImpl
     * @return boolean
     */
    boolean deleteByDelFlag(FileImpl fileImpl,String fileName,String teamName);

    /**
     * 删除文件恢复，即修改删除标记
     * @param fileName,teamName
     * @return boolean
     */
    boolean recoverFile(FileImpl fileImpl,String fileName,String teamName);

    /**
     * 删除文件
     * @param fileName
     * @return boolean
     */
    boolean deleteFile(FileImpl fileImpl,String fileName,String teamName);

    /**
     * 下载文件
     * @param fileName
     * @return boolean
     */
    boolean downloadFile(String fileName);

}
