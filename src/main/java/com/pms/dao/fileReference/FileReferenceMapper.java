package com.pms.dao.fileReference;

import com.pms.model.file.FileImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileReferenceMapper {

    /**
     * 通过项目id得到所有项目下的文件
     * @param projectId
     */
   public List<FileImpl> getFilesByProjectId(int projectId);

}
