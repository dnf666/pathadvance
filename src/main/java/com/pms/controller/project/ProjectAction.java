package com.pms.controller.project;

import com.pms.controller.file.FileAction;
import com.pms.model.file.FileImpl;
import com.pms.model.project.Project;
import com.pms.model.project.ProjectMember;
import com.pms.model.user.User;
import com.pms.service.FileReference.FileReferenceService;
import com.pms.service.project.ProjectService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author MEI
 */
@Controller
public class ProjectAction {
    @Resource
    private ProjectService projectService;
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
    public ResponseEntity<byte[]> downLoadFile(HttpServletRequest request, @RequestParam("fileName") String fileName){
        Map map;
        String message;
        byte[] bytes;
        try {
            String path = request.getSession().getServletContext().getRealPath("upload") + File.separator;
            File file = new File(path + File.separator + fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            if (file.exists()){
                bytes = FileUtils.readFileToByteArray(file);
                return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
            }else {
                message = "文件不存在";
                map = MapUtil.toMap(0, message, null);
                JsonUtil.toJSON(map);
            }
        }catch (IOException e) {
            message="文件传输失败";
            map = MapUtil.toMap(0, message, null);
            JsonUtil.toJSON(map);
        }
       return null;
    }

    @RequestMapping(value = "/project/delfile.do")
    public void deleteFile(FileImpl fileImpl,String userName){
        try {
            projectService.deleteFile(fileImpl, userName);
        } catch (Exception e) {
            String message = e.getMessage();
            Map map = MapUtil.toMap(0, message, null);
            JsonUtil.toJSON(map);
        }
    }

    //需要测试
    @RequestMapping(value = "/project/addfile.do", method = RequestMethod.POST)
    public void addFile(HttpServletRequest request, String userName, int projectId) throws IOException{
        Map map;
        String message;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator iterator = multipartHttpServletRequest.getFileNames();
            if (iterator.hasNext()){
                MultipartFile file1 = multipartHttpServletRequest.getFile(iterator.next().toString());
                projectService.addFile(file1, userName, projectId);
                if (!file1.isEmpty()){
                    String path = request.getSession().getServletContext().getRealPath("upload") + File.separator;
                    InputStream is = file1.getInputStream();
                    OutputStream os = new FileOutputStream(new File(path));
                    byte[] buf = new byte[2048];
                    while(is.read(buf) != -1){
                        os.write(buf);
                        os.flush();
                    }
                    is.close();
                    os.close();
                }else {
                    message = "请选择要上传的文件";
                    map = MapUtil.toMap(1,message, null);
                    JsonUtil.toJSON(map);
                }
            }
            message = "文件上传成功";
            map = MapUtil.toMap(1, message, null);
            JsonUtil.toJSON(map);
        }
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
    public void deleteMem(String name,  ProjectMember projectMember){
            Map map;
            try {
            boolean deleteFlag = projectService.deleteProMember(name, projectMember);
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
