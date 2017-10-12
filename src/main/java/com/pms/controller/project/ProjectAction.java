package com.pms.controller.project;

import com.pms.controller.file.FileAction;
import com.pms.model.file.FileImpl;
import com.pms.model.project.FileReference;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.service.FileReference.FileReferenceService;
import com.pms.service.file.FileService;
import com.pms.service.project.ProjectService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectAction {
    @Autowired
    ProjectService projectService;
    @Autowired
    FileService fileService;
    @Autowired
    FileAction fileAction;
    @Autowired
    FileReferenceService fileReferenceService;

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

    @RequestMapping(value = "/project/showpro.do"   , method = RequestMethod.GET)
    public void showProject(int projectId){
        Map map;
        Project project = projectService.getProject(projectId);
        if (project != null){
            map = MapUtil.toMap(1, "success", project);
        }else {
            map = MapUtil.toMap(0, "false", null);
        }
        System.out.println("接收到了请求...");
        JsonUtil.toJSON(map);
    }

    @RequestMapping("/project/showfile.do")
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
    public void deleteFile(FileImpl fileImpl, String fileName, String teamName, HttpServletResponse response){
        if (fileImpl != null && fileName != null && teamName != null){
            fileAction.deleteFile(fileImpl, fileName, teamName, response);
        }
    }

    @RequestMapping(value = "/project/addfile.do")
    public void addFile(MultipartFile file, HttpServletRequest request) throws IOException{
        fileAction.insertFileInfo(file, request);
    }

    @RequestMapping(value = "/project/showmemes.do")
    public void showMems(Project project){
        Map map;
        List<ProjectMember> projectMembers = projectService.getProMembers(project);
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

    @RequestMapping(value = "/project/delmem.do")
    public void deleteMem(String userName, int projectId, ProjectMember projectMember){
        Map map;
        try {
            boolean deleteFlag = projectService.deleteProMember(userName, projectId, projectMember);
            if (deleteFlag){
                map = MapUtil.toMap(1, "success", null);
                JsonUtil.toJSON(map);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            map = MapUtil.toMap(0, "false", message);
            JsonUtil.toJSON(map);
        }


    }
}
