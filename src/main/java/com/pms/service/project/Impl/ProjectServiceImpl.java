package com.pms.service.project.Impl;

import com.pms.dao.file.FileMapper;
import com.pms.dao.fileReference.FileReferenceMapper;
import com.pms.dao.project.ProjectMapper;
import com.pms.dao.teamdao.TeamMapper;
import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.model.team.TeamMember;
import com.pms.service.project.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private FileMapper fileMapper;
    @Resource
    private FileReferenceMapper fileReferenceMapper;
    @Resource
    private TeamMapper teamMapper;

    @Transactional
    public boolean addProject(Project project, String teamName, String userName) {
        if (projectMapper.addProject(project)){
            List<TeamMember> teamMembers = teamMapper.getTeamMembersByTeamName(teamName);
            if(teamMembers != null){
                for(TeamMember teamMember : teamMembers){
                    ProjectMember projectMember = new ProjectMember.Builder().projectId(project.getId())
                            .teamName(teamMember.getTeamName()).joinBy(userName).joinTime(new SimpleDateFormat("yyyy/MM/dd HH-mm-ss").format(new Date()))
                            .userName(teamMember.getUserName()).build();
                    if (teamMember.getUserName().equals(userName)){
                        projectMember.setProjectRole("负责人");
                    }else{
                        projectMember.setProjectRole("成员");
                    }

                    return projectMapper.addProjectMember(projectMember);
                }
            }
        }
/*        if (projectMapper.addProject(project)) {
            Project project1 =projectMapper.getProjectByCreateAtAndProjectName(project.getCreateAt(),project.getProjectName());
            if (project1 != null){
                ProjectMember projectMember = new ProjectMember.Builder().userName(userName).joinTime(joinTime).projectId(project1.getId())
                        .joinBy(joinBy).projectRole("负责人").teamName(project.getTeamName()).build();
                if (projectMapper.addProjectMember(projectMember)){
                    return true;
                }
            }
        }*/

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


    public boolean deleteFile(FileImpl fileImpl, int fileId) {
        if (fileId == fileImpl.getFileId()) {
            if (fileMapper.deleteFile(fileImpl, fileId)) {
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean addFile(FileImpl file) {
        return fileMapper.insertFileInfo(file);
    }


    public boolean deleteProMember(String userName, int projectId, ProjectMember projectMember) throws Exception {
        Project project = projectMapper.getProjectById(projectId);
        if (project != null) {
            String createPerson = project.getCreateBy();
            String projectRole = projectMember.getProjectRole();
            if (userName.equals(createPerson) && !projectRole.equals("负责人")) {
                if (projectMapper.delProjectMember(projectMember))
                    return true;
            }
            if (!userName.equals(project.getCreateBy())) {
                throw new Exception("只有负责人可以删除成员.");
            }
            if (projectMember.getProjectRole().equals("负责人")) {
                throw new Exception("负责人不能删除自己.");
            }
        }
        return false;

    }

    public boolean addProMember(ProjectMember projectMember) {
        return projectMember != null && projectMapper.addProjectMember(projectMember);
    }



    public List<ProjectMember> getProMembers(int projectId) {
        return  projectMapper.getProjectMembersByProjectId(projectId);
    }



    public boolean insertFile(FileImpl file) {
        return fileMapper.insertFileInfo(file);
    }
}
