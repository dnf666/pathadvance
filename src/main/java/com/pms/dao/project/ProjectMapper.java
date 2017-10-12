package com.pms.dao.project;

import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CreatedBy: liudong
 * On: 2017/9/11.
 * describle:
 */
@Repository
public interface ProjectMapper {

    /**
     * 得到所有项目
     * @param
     * @return list
     */
    List<Project> getAllProjects();

    /**
     * 添加项目
     * @param project 项目   project需要设值的参数：projectName,teamName,projectInfo,createBy,createAt
     * @return boolean
     */
    boolean addProject(Project project);


    /**
     * 删除项目
     * @param project 项目   project需要设值的参数：id (项目id) , delFlag
     * @return boolean
     */
    boolean delProject(Project project);

    /**
     * 更新项目信息
     * @param project 项目  project需要设值的参数：id (项目id) ，projectInfo , projectName
     * @return Boolean
     */
    boolean updateProject(Project project);
    /**
     * 通过id选择项目
     * @param id 项目id
     * @return project
     */
    Project getProjectById(int id);

    /**
     * 得到团队的项目
     * @param teamName 团队名称
     * @return list
     */
    List<Project> getProjectsByTeamName(String teamName);

    /**
     * 添加项目成员
     * @param projectMember 项目成员  projectMember需要设值的参数： projectName , userName , teamName , teamRole , joinTime , joinBy
     * @return boolean
     */
    boolean addProjectMember(ProjectMember projectMember);

    /**
     * 删除项目成员
     * @param projectMember 项目成员  projectMember需要设值的参数： delFlag , delTime , delBy , delRemarks , teamName , projectName , userName
     * @return boolean
     */
    boolean delProjectMember(ProjectMember projectMember);

    /**
     * 得到项目成员
     * @param project 项目 project需要设值的参数：teamName , projectName.
     * @return list
     */
    List<ProjectMember> getProjectMembersByProjectId(Project project);

    /**
     *
     * @param teamName
     * @return boolean
     */
    boolean getProjectMembersByTeamName(String teamName);



}
