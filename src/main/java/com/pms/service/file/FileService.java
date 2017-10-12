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
<<<<<<< HEAD
    FileImpl selectByFileId(int fileId);
=======
    boolean selectByFileId(FileImpl fileImpl, int fileId);
>>>>>>> fc958eaab499b1ccdc9dfaa67c75f8d681ed86a0

    /**
     * 更新文件信息
     * @param fileImpl
     * @return boolean
     */
    boolean updateFileInfo(FileImpl fileImpl,String fileName);

    /**
     * 删除文件，只是修改删除标记
     * @param fileImpl
     * @return boolean
     */
    boolean deleteByDelFlag(FileImpl fileImpl,int fileId);

    /**
     * 删除文件恢复，即修改删除标记
     * @param fileId
     * @return boolean
     */
    boolean recoverFile(FileImpl fileImpl,int fileId);

    /**
     * 删除文件
     * @param fileId
     * @return boolean
     */
    boolean deleteFile(FileImpl fileImpl,int fileId);

    /**
     * 下载文件
     * @param fileName
     * @return boolean
     */
    boolean downloadFile(String fileName);

}
