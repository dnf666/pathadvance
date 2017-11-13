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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public boolean delProject(int projectId, String userName) throws Exception {
        Project project = projectMapper.getProjectById(projectId);
        project.setDelFlag(true);
        String createBy = project.getCreateBy();
        if (createBy.equals(project.getCreateBy())){
            return projectMapper.delProject(project);
        }else {
            throw new Exception("只有项目创建者可以删除项目");
        }
    }

    @Override
    public List<Project> getAllProjects() {
        return projectMapper.getAllProjects();
    }

    // TODO 需要测试
    @Override
    public boolean updateProject(Project project, String userName) throws Exception {
        List<ProjectMember> projectMembers = projectMapper.getProjectMembersByProjectId(project.getId());
        List<String> userNames = new ArrayList<String>();
        if (projectMembers != null){
            for (ProjectMember projectMember : projectMembers){
                userNames.add(projectMember.getUserName());
            }
            if (userNames.contains(userName)){
                return projectMapper.updateProject(project);
            }else {
                throw new Exception("不是项目成员不能够修改项目信息");
            }
        }
        return false;
    }


    @Override
    public Project getProject(int id) {
        return projectMapper.getProjectById(id);
    }

    @Override
    public List<Project> getProjectByTeamName(String teamName) {
        return projectMapper.getProjectsByTeamName(teamName);
    }


    @Override
    public boolean deleteFile(FileImpl fileImpl, String userName) throws Exception {
       /* List<TeamMember> teamMembers = fileReferenceMapper.getTeamMembersByFileId(fileImpl.getFileId());
        List<String> userNames = new ArrayList<String>();
        if (teamMembers!= null){
            for(TeamMember teamMember : teamMembers){
               userNames.add(teamMember.getUserName());
            }
            if (userNames.contains(userName)){
                return fileMapper.deleteByDelFlag(fileImpl) && fileReferenceMapper.deleteFileReferenceByFileId(fileImpl.getFileId());
            }else{
                throw new Exception("不是团队成员不能够删除团队文件");
            }
        }*/
        return false;
    }

    @Override
    public boolean addFile(MultipartFile file, String userName, int projectId) {
        FileImpl fileImpl = new FileImpl();
        fileImpl.setFileName(file.getOriginalFilename());
        fileImpl.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        fileImpl.setCreateBy(userName);
        fileImpl.setSize((int) file.getSize());
        return file != null && fileMapper.insertFileInfo(fileImpl) && fileReferenceMapper.addFileReferenceToProject(fileImpl.getFileId(),userName, projectId);
    }



    @Override
    public boolean deleteProMember(String userName, ProjectMember projectMember) throws Exception {
        String principal ="负责人";
        int projectId = projectMember.getProjectId();
        Project project = projectMapper.getProjectById(projectId);
        if (project != null) {
            String createPerson = project.getCreateBy();
            String projectRole = projectMember.getProjectRole();
            if (userName.equals(createPerson) && !principal.equals(projectRole)) {
                return projectMapper.delProjectMember(projectMember);
            }
            if (!userName.equals(project.getCreateBy())) {
                      throw new Exception("只有负责人可以删除成员.");
            }
            if (principal.equals(projectMember.getProjectRole())) {
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


}
