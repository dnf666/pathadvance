package com.pms.service.FileReference;

import com.pms.model.file.FileImpl;

import java.util.List;

public interface FileReferenceService {

    /**
     * 通过项目id来获取所有文件
     * @param projectId
     * @return
     */
    public List<FileImpl> getFilesByProjectId(int projectId);
}
