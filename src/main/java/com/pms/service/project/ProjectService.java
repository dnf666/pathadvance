package com.pms.service.project;

import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import java.util.List;

/**
 * @author MEI
 */
public interface ProjectService {
    /**
     *添加项目
     * @param project 项目
     *@return 添加项目是否成功
     **/
    boolean addProject(Project project);

    /**
     * 在用户与创建项目用户相同的情况下才能够删除项目
     * @param projectId 项目ID
     * @return 是否删除成功
     */
    boolean delProject(int projectId, String userName);

    /**
<<<<<<< HEAD
     * 得到项目的数量
     *@return int
=======
     *得到所有项目
     *@return 所有项目
>>>>>>> 0dfdceb90815b69d636c49fa83dd0728c6be54a0
    **/
    List<Project> getAllProjects();

    /**
     * 得到单个项目
     *@param id 项目id
     *@return project
     */
    Project getProject(int id);

    /**
     * 删除文件
     * @param fileImpl 文件
     * @param fileName 文件名
     * @param teamName 团队名称
     * @return 是否删除文件成功
     */
    boolean deleteFile(FileImpl fileImpl, String fileName, String teamName);

    /**
     *添加文件
     * @param file 上传文件
     * @return 是否添加文件成功
     */
    boolean addFile(FileImpl file);

    /**
     * 想项目展示中添加文件
     * @param file 文件
     * @return 是否添加文件成功
     */
    boolean insertFile(FileImpl file);

    /**
     * 删除项目成员
     * @param userName 用户名
     * @param projectMember 项目成员
     * @return 是否删除成员成功
     * @throws Exception  异常
     */
    boolean deleteProMember(String userName, ProjectMember projectMember) throws Exception;

    /**
     * 添加项目成员
     * @param projectMember 项目成员
     * @return 是否添加成员成功
     */
    boolean addProMember(ProjectMember projectMember);

    /**
     *取得所有当前项目中的成员
     * @param projectId 项目id
     * @return 所有项目成员
     */
    List<ProjectMember> getProMembers(int projectId);


}
