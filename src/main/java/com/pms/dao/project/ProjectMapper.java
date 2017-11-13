package com.pms.dao.project;

import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CreatedBy: liudong
 * On: 2017/9/11.
 * describle:
 * @author MEI
 */
@Repository
public interface ProjectMapper {

    /**
     * 得到所有项目
     * @return list
     */
    List<Project> getAllProjects();

    /**
     * 添加项目
     * @param project 项目   project需要设值的参数：projectName,teamName,projectInfo,createBy,createAt
     * @return 项目的id
     */
    int addProject(Project project);


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
     * @return Project
     */
    Project getProjectById(int id);

    /**
     * 通过创建时间和项目名称得到项目
     * @param createAt
     * @param projectName
     * @return Project
     */
    Project getProjectByCreateAtAndProjectName(@Param("createAt") String createAt, @Param("projectName") String projectName);

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
     * 通过项目id得到项目成员
     * @param projectId 项目id
     * @return list
     */
    List<ProjectMember> getProjectMembersByProjectId(int projectId);

    /**
     *通过团队名称获得团队成员
     * @param teamId 团队Id
     * @return boolean
     */
    boolean getProjectMembersByTeamName(String teamId);

}
