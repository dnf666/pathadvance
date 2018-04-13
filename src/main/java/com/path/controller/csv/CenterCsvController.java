package com.path.controller.csv;

import com.path.model.CenterNode;
import com.path.model.Path;
import com.path.service.csv.CsvService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author demo
 */
@Controller
@RequestMapping("/csv")
public class CenterCsvController {
    private boolean result = false;
    private String message = null;
    @Resource
    private CsvService<CenterNode> csvService;

    /**
     * 检查中心点数据是否合理
     */
    @RequestMapping("/checkCenterFile.do")
    public void checkFile(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) {
       String fileName = multipartFile.getOriginalFilename();
        Integer questionId = (Integer) request.getSession().getAttribute("questionId");
        String projectPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/properties");
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/download") + File.separator + fileName;
        Path pathObject = new Path(projectPath, path, questionId);
        File file = new File(path);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            System.out.println("确认类型");
            csvService.ensureType(file, projectPath);
        } catch (IOException e) {
            Map map = MapUtil.toMap(0, e.getMessage(), result);
            JsonUtil.toJSON(map);
            return;
        } catch (Exception e) {
            Map map = MapUtil.toMap(0, e.getMessage(), result);
            JsonUtil.toJSON(map);
            return;
        }
        try {
            List<CenterNode> list = csvService.readCsv(pathObject);
            csvService.checkFile(list);
            result = true;
        } catch (IOException e) {
            Map map = MapUtil.toMap(0, e.getMessage(), result);
            JsonUtil.toJSON(map);
            return;

        } catch (Exception e) {
            Map map = MapUtil.toMap(0, e.getMessage(), result);
            JsonUtil.toJSON(map);
            return;
        }
        Map map = MapUtil.toMap(1, "确认成功，请点击上传", result);
        JsonUtil.toJSON(map);

    }

    /**
     * 导入中心点数据
     * @param request http请求
     * @param multipartFile 中心点文件
     */
    @RequestMapping("importCenterNode")
    public void insertCenterNode(HttpServletRequest request,@RequestParam("info-fileselect") MultipartFile multipartFile)  {
        String fileName = multipartFile.getOriginalFilename();
        //Integer questionId = (Integer) request.getSession().getAttribute("questionId");
        Integer questionId = 1;
        String projectPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/properties");
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/download") + File.separator + fileName;
        Path pathObject = new Path(projectPath, path, questionId);
        File file = new File(path);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            Map map = MapUtil.toMap(0, e.getMessage(), result);
            JsonUtil.toJSON(map);
            return;
        } catch (Exception e) {
            Map map = MapUtil.toMap(0, e.getMessage(), result);
            JsonUtil.toJSON(map);
            return;
        }
        try {
            List<CenterNode> list = csvService.readCsv(pathObject);
            result = csvService.storeDatabase(list);
            message = "导入" + list.size() + "条数据成功";
        } catch (IOException e) {
            Map map = MapUtil.toMap(0, e.getMessage(), result);
            JsonUtil.toJSON(map);
            return;

        } catch (Exception e) {
            Map map = MapUtil.toMap(0, e.getMessage(), result);
            JsonUtil.toJSON(map);
            return;
        }
        Map map = MapUtil.toMap(200, message, result);
        JsonUtil.toJSON(map);
    }
}
