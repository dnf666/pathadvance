package com.path.controller.servicenode;

import com.path.model.ServiceNode;
import com.path.service.servicenode.ServiceNodeService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author demo
 */
@Controller
@RequestMapping("servicenode")
public class ServiceNodeController {
    @Resource
    private ServiceNodeService serviceNodeService;
    @RequestMapping("/importByMap")
    public void importByMap(List<ServiceNode> list){
        try {
            list.stream().forEach(e -> serviceNodeService.insert(e));
        }catch (Exception e){
            e.printStackTrace();
            Map map = MapUtil.toMap(0, "添加失败", false);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(1, "添加成功", true);
        JsonUtil.toJSON(map);


    }

    /**
     * 取得相关信息给前端算经纬度
     */
    @RequestMapping("getServiceAddress")
    public void getCenterAddress(HttpServletRequest request) {
        String questionId = (String) request.getSession().getAttribute("questionId");
        List<String> ServiceNodes = serviceNodeService.selectAllServiceNodeAddress(questionId);
        Map map = MapUtil.toMap(1, "中心点地址", ServiceNodes);
        JsonUtil.toJSON(map);
    }
    @RequestMapping("/addlaandlo")
    public void addExtraMessage(List<ServiceNode> serviceNodeList){
        boolean result = serviceNodeService.updateAdvance(serviceNodeList);
        if(result) {
            Map map = MapUtil.toMap(1, "添加成功", result);
            JsonUtil.toJSON(map);
        }else{
            Map map = MapUtil.toMap(0, "添加失败", result);
            JsonUtil.toJSON(map);

        }
    }
}
