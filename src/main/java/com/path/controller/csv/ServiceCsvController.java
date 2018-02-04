package com.path.controller.csv;


import com.path.model.Path;
import com.path.model.ServiceNode;
import com.path.service.csv.CsvService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author demo
 */
@Controller
@RequestMapping("/csv")
public class ServiceCsvController {
    private boolean result = false;
    @Resource
    private CsvService<ServiceNode> csvService;
    /**
     * 这是批量导入
     */
    @RequestMapping("/addservicenode.do")
    public void batchInsertApprovedUser(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
        if(multipartFile.isEmpty())
        {
            Map map = MapUtil.toMap(0,"文件没有上传",result);
            JsonUtil.toJSON(map);
            return;
        }
        String fileName = multipartFile.getOriginalFilename();
        Integer questionId =(Integer)request.getSession().getAttribute("questionId");
        String projectPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/");
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/download")+ File.separator+fileName;
        Path pathObject = new Path(projectPath,path,questionId);


        File file = new File(path);
        try {
//              multipartFile.transferTo(file);
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
            System.out.println("确认类型");
            csvService.ensureType(file,projectPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            Map map = MapUtil.toMap(0,e.getMessage(),result);
            JsonUtil.toJSON(map);
            return;
        }
        try {
            List<ServiceNode> list = csvService.readCsv(pathObject);
            list = csvService.removeDuplication(list);
            result = csvService.storeDatabase(list);
        } catch (IOException e) {
            Map map = MapUtil.toMap(0,e.getMessage(),result);
            JsonUtil.toJSON(map);
            return;

        } catch (Exception e) {
            Map map = MapUtil.toMap(0,e.getMessage(),result);
            JsonUtil.toJSON(map);
            return;
        }
        Map map = MapUtil.toMap(1,"中心点导入成功",result);
        JsonUtil.toJSON(map);


    }

}
