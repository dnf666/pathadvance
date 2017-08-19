package com.pms.service.file;

import com.pms.model.file.File;

import java.util.List;

/**
 * Created by Chenmeiling on 2017/8/18.
 */
public interface FileService {
    /**
     * 上传文件
     * @param file
     * @return boolean
     */
    public boolean insertFileInfo(File file);

    /**
     * 查看文件信息
     * @return boolean
     */
    public File selectByFileName(List<File> list, String fileName);

    /**
     * 更新文件信息
     * @param file
     * @return boolean
     */
    public boolean updateFileInfo(File file);

    /**
     * 删除文件，只是修改删除标记
     * @param file
     * @return boolean
     */
    public boolean deleteByDelFlag(File file);

    /**
     * 删除文件恢复，即修改删除标记
     * @param file
     * @return boolean
     */
    public boolean recoverByDelFlag(File file);

    /**
     * 删除文件
     * @param fileName
     * @return boolean
     */
    public boolean deleteByFileName(String fileName);

}
