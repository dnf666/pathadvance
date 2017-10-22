package com.pms.service.project;

import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

public interface ProjectService {
    /**
     *添加项目
     * @param project 项目
     *@return boolean
     **/
    boolean addProject(Project project,String teamName, String userName);

    /**
     *得到所有项目
     *@param
     *@return list
    **/
    List<Project> getAllProjects();

    /**
     * 得到项目的数量
     *@param
     *@return int
    **/
    int getProjectsCount();

    /**
     * 得到单个项目
     *@param id 项目id
     *@return project
     */
    Project getProject(int id);

    /**
     *删除文件
     * @param
     * @return boolean
     */
    boolean deleteFile(FileImpl fileImpl, String fileName, String teamName);

    /**
     *添加文件
     * @param file 上传文件
     * @return boolean
     */
    boolean addFile(FileImpl file);


/*     *//**
     *下载文件
     *@param fileId 文件id
     *@return boolean
     *//*

    boolean downFile(int fileId);*/

    /**
     * 想项目展示中添加文件
     * @param file
     * @return boolean
     */
    boolean insertFile(FileImpl file);

    /**
     *删除项目成员
     * @param userName 用户名
     * @param projectId 项目id
     * @param projectMember 项目成员
     * @return boolean
     */
    boolean deleteProMember(String userName, int projectId, ProjectMember projectMember) throws Exception;

    /**
     * 添加项目成员
     * @param projectMember 项目成员
     * @return boolean
     */
    boolean addProMember(ProjectMember projectMember);

    /**
     *取得所有当前项目中的成员
     * @param projectId 项目id
     * @return list
     */
    List<ProjectMember> getProMembers(int projectId);


}
