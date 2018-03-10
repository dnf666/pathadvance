package com.path.controller.csv;

import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.awt.AWTAccessor;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author demo
 */
@Controller
@RequestMapping("download")
public class DownloadTemplateController {
    /**
     * 取得数据文件模板名
     * @param request request
     */
    @RequestMapping("gainFileName")
    public void gainFileName(HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/download") + File.separator ;
        File file = new File(path);
        List<String> list = new ArrayList<>();
        if(file.isDirectory())
        {
            File[] files = file.listFiles();

            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName();
                list.add(name);
            }
        }
        Map map = MapUtil.toMap(1,"模板名称",list);
        JsonUtil.toJSON(map);
    }

    /**
     * 下载文件
     * @param request
     * @param fileName
     * @return
     */
    @RequestMapping("downloadFile")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request,String fileName){
        byte[] bytes = null;
            String projectPath = request.getSession().getServletContext().getRealPath("WEB-INF/download");
            File file = new File(projectPath+File.separator+fileName);
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment",fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            bytes = FileUtils.readFileToByteArray(file);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.OK);
    }
//    @RequestMapping("download")
//    public void downloadZipResource(HttpServletRequest request,
//                                    HttpServletResponse response) {
//        ServletContext context = request.getSession().getServletContext();
//        String dataDirectory = context.getRealPath("/WEB-INF/download") + "/服务点模板.csv";
//        File file = new File(dataDirectory);
//        if (file.isFile()) {
//            String mimeType = context.getMimeType(dataDirectory);   //直接根据文件的路径名来获取它的MineType，这样就可以灵活的根据请求的文件类型来返回输出流了
//            response.setContentType("csv");
//            response.addHeader("Content-Disposition", "attachment; filename=服务点模板.csv");
//            try {
//                OutputStream os = response.getOutputStream();
//                ;
//                IOUtils.copy(new FileInputStream(file), os);
//                os.flush();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
}
