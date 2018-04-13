package com.path.controller.servicenode;

import com.alibaba.fastjson.JSONObject;
import com.path.model.CenterNode;
import com.path.model.ServiceNode;
import com.path.service.servicenode.ServiceNodeService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author demo
 */
@Controller
@RequestMapping("servicenode")
public class ServiceNodeController {
    @Resource
    private ServiceNodeService serviceNodeService;


    /**
     * 取得相关信息给前端算经纬度
     */
    @RequestMapping("getServiceAddress")
    public void getServiceAddress(HttpServletRequest request) {
//        String questionId = (String) request.getSession().getAttribute("questionId");
            String questionId = "1";
        List<ServiceNode> serviceNodes = serviceNodeService.selectAllServiceNodeAddress(questionId);
        Map map = MapUtil.toMap(1, "服务点地址", serviceNodes);
        JsonUtil.toJSON(map);
    }

    /**
     * 添加经纬度
     * @param postData
     */
    @RequestMapping("addlaandlo")
    public void addExtraMessage(@RequestParam Map<String,String> postData){
        List<ServiceNode> list = new ArrayList<>();
        Set<String> set1 = postData.keySet();
        Iterator iterator = set1.iterator();
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            String string1 = postData.get(string);
            JSONObject jsonObject = JSONObject.parseObject(string1);
            ServiceNode serviceNode = jsonObject.toJavaObject(ServiceNode.class);
            serviceNode.setSAddress(string);
            serviceNode.setSId(1);
            list.add(serviceNode);
        }
        boolean result = serviceNodeService.updateAdvance(list);
        if (result) {
            Map map = MapUtil.toMap(200, "添加成功", result);
            JsonUtil.toJSON(map);
        } else {
            Map map = MapUtil.toMap(403, "添加失败", result);
            JsonUtil.toJSON(map);
        }
    }
}
