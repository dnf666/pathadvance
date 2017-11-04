package com.pms.dao.fileReference;

import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.team.Team;
import com.pms.model.team.TeamMember;
import com.pms.model.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MEI
 */
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
     * @param userName 在项目中添加文件用户名
     * @param projectId 项目的id
     * @param fileId 文件id
     * @return boolean
     */
    boolean addFileReferenceToProject(@Param("fileId") int fileId,@Param("userName") String userName, @Param("projectId") int projectId);
    //需要测试
    /**
     * 通过文件ID得到项目对象
     * @param fileId 文件ID
     * @return 项目
     */
    Project getProjectByFileId(int fileId);
    //需要测试
    /**
     * 通过文件Id得到团队对象
     * @param fileId 文件ID
     * @return 团队对象
     */
    Team getTeamByFieId(int fileId);
    //需要测试
    /**
     * 通过文件Id得到上传文件用户对象
     * @param fileId 文件Id
     * @return 用户对象
     */
    User getUserByFileId(int fileId);
    //需要测试
    /**
     * 通过文件Id获得团队成员
     * @param fileId
     * @return
     */
    List<TeamMember> getTeamMembersByFileId(int fileId);
    //需要测试
    /**
     * 通过fileId删除文件与项目之间的关系
     * @param fileId 文件Id
     * @return 是否删除文件与项目之间的关系
     */
    boolean deleteFileReferenceByFileId(int fileId);
    //需要测试
    //记得把数据库的filereference表的user_id 改为 user_name

}
