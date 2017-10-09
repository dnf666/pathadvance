package com.pms.service.project.Impl;

import com.pms.dao.file.FileMapper;
import com.pms.dao.project.ProjectMapper;
import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.service.project.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Resource
    ProjectMapper projectMapper;
    @Resource
    FileMapper fileMapper;

    public boolean addProject(Project project){
        if (projectMapper.addProject(project)){
            return true;
        }
        return false;
    }

    public List<Project> getAllProjects() {
        return projectMapper.getAllProjects();
    }

    public int getProjectsCount() {
        return projectMapper.getAllProjects().size();
    }

    public Project getProject(int id) {
        return projectMapper.getProjectById(id);
    }

    public boolean deleteFile(FileImpl fileImpl,String fileName,String teamName) {
        if (fileName.equals(fileImpl.getFileName()) && teamName.equals(fileImpl.getTeamName()))
            if (fileMapper.deleteFile(fileImpl,fileName,teamName))
                return true;
        return false;
    }

    public boolean addFile(FileImpl file) {
        if (fileMapper.insertFileInfo(file)){
            return true;
        }
        return false;
    }


    public boolean deleteProMember(String userName, int projectId, ProjectMember projectMember) throws Exception {
        Project project = projectMapper.getProjectById(projectId);
        if (project != null){
            String createPerson = project.getCreateBy();
            String projectRole = projectMember.getProjectRole();
                if(userName.equals(createPerson) && !projectRole.equals("负责人")){
                if(projectMapper.delProjectMember(projectMember))
                    return true;
            }if (!userName.equals(project.getCreateBy())){
                throw new Exception("只有项目创建者可以删除成员.");
            }if (projectMember.getProjectRole().equals("负责人")){
                throw new Exception("负责人不能删除自己.");
            }
        }
        return false;
    }

    public boolean addProMember(ProjectMember projectMember){
        if (projectMember != null){
            if(projectMapper.addProjectMember(projectMember)){
                return true;
            }
        }
        return false;
    }

    public List<ProjectMember> getProMembers(Project project) {
        List<ProjectMember> projectMembers = projectMapper.getProjectMembersByProjectId(project);
        return projectMembers;
    }

}
