package com.pms.dao.fileReference;

import com.pms.model.file.FileImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileReferenceMapper {

    /**
     * 通过项目id得到所有项目下的文件
     * @param projectId 项目id
     * @return list
     */
    List<FileImpl> getFilesByProjectId(int projectId);

    /**
     * 添加一列项目和file的关系
     * @param userId 在项目中添加文件用户的id
     * @param projectId 项目的id
     * @return boolean
     */
    boolean projectAddFileReference(@Param("userId") int userId, @Param("projectId") int projectId);


}
