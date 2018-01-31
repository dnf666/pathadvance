package com.path.controller.csv;

import com.path.model.CenterNode;
import com.path.service.csv.CenterCsvService;
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
    @Resource
    private CenterCsvService csvService;
    /**
     * 这是批量导入
     */
@RequestMapping("/addcenternode.do")
    public void batchInsertApprovedUser(HttpServletRequest request,@RequestParam(required = false) String type) {
    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
    MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
    if(multipartFile.isEmpty())
    {
        Map<String,Object> map = MapUtil.toMap(0,"文件没有上传",null);
        JsonUtil.toJSON(map);
        return;
    }
    String fileName = multipartFile.getOriginalFilename();
String projectPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/");
    String path = request.getSession().getServletContext().getRealPath("/WEB-INF/download")+File.separator+fileName;
    File file = new File(path);
    try {
//        multipartFile.transferTo(file);
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
        System.out.println("确认类型");
        csvService.ensureType(file,projectPath);
    } catch (IOException e) {
        e.printStackTrace();
        return;
    } catch (Exception e) {
       Map<String,Object> map = MapUtil.toMap(0,e.getMessage(),null);
        JsonUtil.toJSON(map);
        return;
    }
    try {
        List<CenterNode> list = csvService.readCsv(path);
         list = csvService.removeDuplication(list);
           boolean result = csvService.storeDatabase(list);
        Map<String,Object> map = MapUtil.toMap(1,"导入成功",result);
        JsonUtil.toJSON(map);
    } catch (IOException e) {
        Map<String,Object> map = MapUtil.toMap(0,e.getMessage(),null);
        JsonUtil.toJSON(map);

    } catch (Exception e) {
        Map<String,Object> map = MapUtil.toMap(0,e.getMessage(),null);
        JsonUtil.toJSON(map);
    }

//        Map<String, Object> map = new HashMap<String, Object>();
//        System.out.println("批量導入開始");
//        // 首先检查文件类型是否被允许
//        if (!this.allowTypes(CsvFileContentType)) {
//            map.put("code", 0);
//            map.put("message", "文件类型错误");
//            JsonUtil.toJSON(map);
//            return;
//        }
//
//        String path = "WEB-INF/download/" + CsvFileFileName;
//        path = ServletActionContext.getServletContext().getRealPath(path);
//        FileUtil.copy(CsvFile, new File(path));
//        List<RegisterInfo> registerInfos = readCSVFile(path);
//        // 清理重复或已存在的数据(UserID相同就视为重复)
//        List<RegisterInfo> registers = null;
//        try {
//            registers = clearDuplicatedData(registerInfos,adminID);
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("code", 0);
//            JsonUtil.toJSON(map);
//            return;
//        }
//
//        // 遍历registerInfos取出数据插入到数据库
//        Iterator<RegisterInfo> it = registers.iterator();
//        while (it.hasNext()) {
//            RegisterInfo register = it.next();
//            approvedUserService.InsertApprovedUser(register);
//            //戴林甫修改
//            teacherAndStuService.insert(register.getStudentID(), adminID);
//        }
//
//        map.put("code", 1);
//        map.put("message", "批量导入");
//        System.out.println(111111);
//        JSONUtils.toJSON(map);// JSON返回
//    }
//
//    public List<RegisterInfo> readCSVFile(String fileName) {
//        char delimiter = ',';
//        List<RegisterInfo> registerInfos = new ArrayList();
//        CsvReader csvReader = null;
//
//        try {
//            csvReader = new CsvReader(fileName, delimiter, Charset.forName("GBK"));
//            csvReader.setSkipEmptyRecords(true);
//            csvReader.readHeaders();
//
//            while (csvReader.readRecord()) {
//                RegisterInfo registerInfo = new RegisterInfo();
//                registerInfo.setUserID(csvReader.get("学号"));
//                registerInfo.setName(csvReader.get("姓名"));
//                registerInfo.setPassword("123456");
//                registerInfo.setStudentID(csvReader.get("学号"));
//                registerInfo.setMajor(csvReader.get("专业"));
//                registerInfo.setClassName(csvReader.get("班级"));
//                registerInfo.setRem1(csvReader.get("学院"));
//
//                registerInfos.add(registerInfo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            csvReader.close();
//        }
//
//        return registerInfos;
//    }
//
//    // 清理重复userId的数据（删除）
//    public List<RegisterInfo> clearDuplicatedData(List<RegisterInfo> registers,String adminID) throws Exception {
//        List<ApprovedUserInfo> dbApprovedUsers = approvedUserService.showApprovedUserList();
//
//        Iterator<RegisterInfo> it = registers.iterator();
//        // 把不完整的数据列删除掉
//        while (it.hasNext()) {
//            RegisterInfo registerInfo = it.next();
//            if (registerInfo.getUserID() == null || "".equals(registerInfo.getUserID())) {
//                it.remove();
//                continue;
//            } else if (registerInfo.getName() == null || "".equals(registerInfo.getName())) {
//                it.remove();
//                continue;
//            } else if (registerInfo.getPassword() == null || "".equals(registerInfo.getPassword())) {
//                it.remove();
//                continue;
//            } else if (registerInfo.getStudentID() == null || "".equals(registerInfo.getStudentID())) {
//                it.remove();
//                continue;
//            }
//        }
//        //删除csv中重复的数据 userId相同就删除
//        //戴林甫修改
//        Iterator<RegisterInfo> iter = registers.iterator();
//        Set<String> set = new HashSet<String>();
//        while (iter.hasNext()) {
//            String userID = iter.next().getUserID();
//            if (set.contains(userID))
//                throw new Exception("有重复学号");
//            else
//                set.add(userID);
//        }
//        Iterator<RegisterInfo> iterator = registers.iterator();
//        // 和数据库数据进行匹配，得到重复的数据删除了
//        while (iterator.hasNext()) {
//            String userID = iterator.next().getUserID();
//
//            for (int j = 0; j < dbApprovedUsers.size(); j++) {
//                String dbUserID = dbApprovedUsers.get(j).getUserID();
//                if (userID.equals(dbUserID)) {
//                    iterator.remove();
//                    //这里应该做缓存的，或者重构
//                    //以下是为了更新之前在approveduser表中存在，但没有与老师关联的用户
//                    if(!teacherAndStuService.isExist(userID,adminID))
//                    {
//                        teacherAndStuService.insert(userID,adminID);
//                    }
//                }
//            }
//        }
//
//        return registers;
//    }
//
//    // 核对当前上传文件的类型是否被允许
//    private boolean allowTypes(String contentType) {
//        if ("".equals(contentType) || contentType == null) {
//            return false;
//        }
//
//        String[] types = allowTypes.split(",");
//        // 将以逗号分隔的上传为弩箭允许的类型转换成String数组，扫描所有的文件类型，以查找是否存在当前上传文件的类型
//        for (String type : types) {
//            // 当前文件上传的类型被允许，返回true
//            if (contentType.equals(type.trim())) {
//                return true;
//            }
//        }
//        return false;
//    }

}
}
