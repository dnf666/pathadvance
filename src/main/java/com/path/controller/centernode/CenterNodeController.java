package com.path.controller.centernode;

import com.path.model.CenterNode;
import com.path.service.centernode.CenterNodeService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import com.sdicons.json.model.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("centernode")
public class CenterNodeController {
    @Resource
    private CenterNodeService centerNodeService;

    @RequestMapping("importByMap")
    public void importByMap(List<CenterNode> list) {
        try {
            list.stream().forEach(e -> centerNodeService.insert(e));
        } catch (Exception e) {
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
    @RequestMapping("getCenterAddress")
    public void getCenterAddress(HttpServletRequest request) {
//        String questionId = (String) request.getSession().getAttribute("questionId");
        String questionId = "1";
        List<CenterNode> centerNodes = centerNodeService.selectAllCenterNodeAddress(questionId);
        Map map = MapUtil.toMap(200, "查询中心地址成功", centerNodes);
        System.out.println(map);
        JsonUtil.toJSON(map);
    }

    /**
     * 添加经纬度
     *
     * @param postData
     */
    @RequestMapping("addlaandlo")
    public void addExtraMessage(@RequestParam Map<String, String> postData) {
        List<CenterNode> list = new ArrayList<>();
        Set<String> set1 = postData.keySet();
        Iterator iterator = set1.iterator();
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            String string1 = postData.get(string);
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(string1);
            CenterNode centerNode = jsonObject.toJavaObject(CenterNode.class);
            centerNode.setCAddress(string);
            centerNode.setCId(1);
            list.add(centerNode);
        }

        boolean result = centerNodeService.updateAdvance(list);
        if (result) {
            Map map = MapUtil.toMap(200, "添加成功", result);
            JsonUtil.toJSON(map);
        } else {
            Map map = MapUtil.toMap(403, "添加失败", result);
            JsonUtil.toJSON(map);
        }

    }


}
