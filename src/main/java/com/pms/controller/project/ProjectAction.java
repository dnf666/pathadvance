package com.pms.controller.project;

import com.pms.controller.file.FileAction;
import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.service.FileReference.FileReferenceService;
import com.pms.service.project.ProjectService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectAction {
    @Resource
    private ProjectService projectService;
    @Resource
    private FileAction fileAction;
    @Resource
    private FileReferenceService fileReferenceService;

    @RequestMapping(value = "/project/showpros.do")
    public void showAllProjects(){
        Map map;
        List<Project> projects = projectService.getAllProjects();
        if (projects != null && projects.size() > 0){
            map = MapUtil.toMap(1,"success", projects);
        }else{
            map = MapUtil.toMap(0,"false", null);
        }
        JsonUtil.toJSON(map);
    }

    @RequestMapping(value = "/project/showpro.do")
    public void showProject(int projectId){
        Map map;
        Project project = projectService.getProject(projectId);
        if (project != null){
            map = MapUtil.toMap(1, "success", project);
        }else {
            map = MapUtil.toMap(0, "false", null);
        }
        JsonUtil.toJSON(map);
    }

    @RequestMapping("/project/showfiles.do")
    public void showFiles(int projectId){
        Map map;
        List<FileImpl> files = fileReferenceService.getFilesByProjectId(projectId);
        if (files != null && files.size()>0){
            map = MapUtil.toMap(1,"success", files);
        }else {
            map = MapUtil.toMap(0,"false",null);
        }
        JsonUtil.toJSON(map);
    }

    @RequestMapping(value = "/project/downloadFile.do")
    public void downLoadFile(String fileName, HttpServletRequest request, HttpServletResponse response){
        if (fileName != null)
        fileAction.downloadFile(fileName,request, response);
    }

    @RequestMapping(value = "/project/delfile.do")
    public void deleteFile(FileImpl fileImpl,int fileId, HttpServletResponse response){
        if (fileImpl != null){
            fileAction.deleteFile(fileImpl, fileId, response);
        }
    }

    @RequestMapping(value = "/project/addfile.do")
    public void addFile(MultipartFile file, HttpServletRequest request) throws IOException{
        fileAction.insertFileInfo(file, request);
    }

    @RequestMapping(value = "/project/showmemes.do")
    public void showMems(int projectId){
        Map map;
        List<ProjectMember> projectMembers = projectService.getProMembers(projectId);
        if(projectMembers != null && projectMembers.size() > 0){
            map = MapUtil.toMap(1,"success", projectMembers);
        }else{
            map = MapUtil.toMap(0, "false", null);
        }
        JsonUtil.toJSON(map);
    }

        @RequestMapping(value = "/project/addmem.do")
    public void addMem(ProjectMember projectMember){
        Map map;
        if (projectService.addProMember(projectMember)){
            map = MapUtil.toMap(1,"success", null);
        }else{
            map = MapUtil.toMap(0, "false", null);
        }
        JsonUtil.toJSON(map);
    }

    @RequestMapping(value = "/project/delmem.do",method = RequestMethod.POST)
    public void deleteMem(String name, int id,  ProjectMember projectMember){
        Map map;
        try {
            if (projectMember != null){
                System.out.println(projectMember);
            }
            if (name != null){
                System.out.println(name);
            }
            if (id == 1){
                System.out.println(id);
            }
            boolean deleteFlag = projectService.deleteProMember(name, id, projectMember);
            if (deleteFlag){
                map = MapUtil.toMap(1, "success", null);
                JsonUtil.toJSON(map);
            }else {
                map = MapUtil.toMap(0, "false", "deleteFlag is false");
            }
        } catch (Exception e) {
            String message = e.getMessage();
            e.printStackTrace();
            map = MapUtil.toMap(0, "false", "得到exception");
            JsonUtil.toJSON(map);
        }


    }
}
