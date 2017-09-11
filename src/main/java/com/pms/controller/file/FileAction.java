package com.pms.controller.file;

import com.pms.model.file.FileImpl;
import com.pms.service.file.FileService;
import com.pms.util.JsonUtil;
import com.pms.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chenmeiling on 2017/8/30.
 */
@Controller
public class FileAction {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH-mm-ss");

    @Autowired
    FileService fileService;

    @RequestMapping("insertFileInfo")
    public void insertFileInfo(MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
//            System.out.println("文件未上传!");
            String message = "文件未上传!";
            Map map = new HashMap();
            map.put("errMessage",message);
            JsonUtil.toJSON(map);
        } else {
            //得到上传的文件名
            String fileName = file.getOriginalFilename();
            //得到服务器项目发布运行所在地址
            String path1 = request.getSession().getServletContext().getRealPath("upload") + File.separator;
            //此处用日期做为标识
            String path = path1 + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileName;
            //查看文件上传路径,方便查找
            System.out.println(path);
            //把文件上传至path的路径
            File localFile = new File(path);
            file.transferTo(localFile);
            String message = "文件上传成功！";
            Map map = new HashMap();
            map.put("sucMessage", message);
            JsonUtil.toJSON(map);
        }
    }

    @RequestMapping("downloadFile")
    public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath("upload") + File.separator;
            InputStream inputStream = new FileInputStream(new File(path + fileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭
            os.close();
            inputStream.close();
            String message = "文件下载成功！";
            Map map = new HashMap();
            map.put("sucMessage",message);
            JsonUtil.toJSON(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            String message = "没有该文件!";
            Map map = new HashMap();
            map.put("errMessage",message);
            JsonUtil.toJSON(map);
        } catch (IOException e) {
            e.printStackTrace();
            String message = "文件下载失败!";
            Map map = new HashMap();
            map.put("errMessage",message);
            JsonUtil.toJSON(map);
        }
    }

    @RequestMapping("selectByFileName")
    public void selectByFileName(String fileName,HttpServletResponse response){
        List<FileImpl> list = fileService.selectByFileName(fileName);
        JsonUtil.toJSON(list,response);
    }

    @RequestMapping("updateFileInfo")
    public void updateFileInfo(FileImpl fileImpl,HttpServletResponse response){
        Map map;
        String res = null;
        if (fileService.updateFileInfo(fileImpl)){
            res = "操作成功";
            map = MapUtil.toMap(1,res,null);
        }else {
            res = "操作失败";
            map = MapUtil.toMap(0,res,null);
        }
        JsonUtil.toJSON(map,response);
    }

    @RequestMapping("deleteByDelFlag")
    public void deleteByDelFlag(FileImpl fileImpl,String fileName,String teamName,HttpServletResponse response){
        Map map;
        String date = simpleDateFormat.format(new Date());
        fileImpl.setDelTime(date);
        fileImpl.setDelFlag(true);
        System.out.println("时间格式："+date);
        String res = null;
        //boolean result = fileService.deleteByDelFlag(fileImpl,fileName,teamName);
        if (fileService.deleteByDelFlag(fileImpl,fileName,teamName)){
            res = "操作成功";
            map = MapUtil.toMap(1,res,null);
        }else {
            res = "操作失败";
            map = MapUtil.toMap(0,res,null);
        }
        JsonUtil.toJSON(map,response);

    }

    @RequestMapping("recoverFile")
    public void recoverFile(FileImpl fileImpl,String fileName,String teamName,HttpServletResponse response){
        Map map;
        String res = null;
        //boolean result = fileService.recoverFile(fileImpl,fileName,teamName);
        if (fileService.recoverFile(fileImpl,fileName,teamName)){
            res = "操作成功";
            map = MapUtil.toMap(1,res,null);
        }else {
            res = "操作失败";
            map = MapUtil.toMap(0,res,null);
        }
        JsonUtil.toJSON(map,response);
    }

    @RequestMapping("deleteFile")
    public void deleteFile(FileImpl fileImpl,String fileName,String teamName,HttpServletResponse response){
        Map map;
        String res = null;
        //boolean result = fileService.deleteFile(fileImpl,fileName,teamName);
        if (fileService.deleteFile(fileImpl,fileName,teamName)){
            res = "操作成功";
            map = MapUtil.toMap(1,res,null);
        }else {
            res = "操作失败";
            map = MapUtil.toMap(0,res,null);
        }
        JsonUtil.toJSON(map,response);
    }
}

