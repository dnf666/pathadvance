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

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author MEI
 */
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


    @Override
    public boolean addProject(Project project) {
        String teamName = project.getTeamName();
        String userName = project.getCreateBy();
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


        return false;
    }
    @Override
    public boolean delProject(int projectId, String userName) {
        Project project = projectMapper.getProjectById(projectId);
        String createBy = project.getCreateBy();
        if (userName.equals(createBy)){
            return projectMapper.delProject(project);
        }
        return false;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectMapper.getAllProjects();
    }


    @Override
    public Project getProject(int id) {
        return projectMapper.getProjectById(id);
    }
//这个方法需要重写


    @Override
    public boolean deleteFile(FileImpl fileImpl, String fileName, String teamName) {
        /*   if (fileName.equals(fileImpl.getFileName()) && teamName.equals(fileImpl.getTeamName())) {
            if (fileMapper.deleteFile(fileImpl, fileName, teamName)) {
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }*/
        return false;
    }

    @Override
    public boolean addFile(FileImpl file) {
        return fileMapper.insertFileInfo(file);
    }



    @Override
    public boolean deleteProMember(String userName, ProjectMember projectMember) throws Exception {
        int projectId = projectMember.getProjectId();
        Project project = projectMapper.getProjectById(projectId);
        if (project != null) {
            String createPerson = project.getCreateBy();
            String projectRole = projectMember.getProjectRole();
            if (userName.equals(createPerson) && !projectRole.equals("负责人")) {
<<<<<<< HEAD
                if (projectMapper.delProjectMember(projectMember)){
=======
                if (projectMapper.delProjectMember(projectMember)) {
>>>>>>> 0dfdceb90815b69d636c49fa83dd0728c6be54a0
                    return true;
                }
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

    @Override
    public boolean addProMember(ProjectMember projectMember) {
        return projectMember != null && projectMapper.addProjectMember(projectMember);
    }



    @Override
    public List<ProjectMember> getProMembers(int projectId) {
        return  projectMapper.getProjectMembersByProjectId(projectId);
    }



    @Override
    public boolean insertFile(FileImpl file) {
        return fileMapper.insertFileInfo(file);
    }
}
