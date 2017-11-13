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
     * 通过文件id查找文件
     * @return boolean
     */
    FileImpl selectByFileId(int fileId);

    /**
     * 通过文件id来更新文件名称信息
     * @param fileName
     * @return boolean
     */
    boolean updateFileInfo(String fileName,int fileId);

    /**
     * 通过文件id来修改数据库中的文件删除标记，以实现文件暂时删除的目的
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
     * 通过文件id彻底删除数据库中的文件记录
     * @param fileId
     * @return boolean
     */
    boolean deleteFile(int fileId);

    /**
     * 通过文件id下载文件
     * @param fileId
     * @return boolean
     */
    boolean downloadFile(int fileId);

}
