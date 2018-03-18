package com.path.controller.route;


import com.alibaba.fastjson.JSONObject;
import com.path.model.Route;
import com.path.service.route.RouteService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author demo
 */
@Controller
@RequestMapping("route")
public class RouteController {
    @Resource
    private RouteService routeService;
    @RequestMapping("addroute")
    public void addRoute(@RequestParam Map<String,String> postData){
        try {
            List<Route> list = new ArrayList<>();
            Set<String> set1 = postData.keySet();
            Iterator iterator = set1.iterator();
            while (iterator.hasNext()) {
                String string = (String) iterator.next();
                String string1 = postData.get(string);
                JSONObject jsonObject = JSONObject.parseObject(string1);
                Route Route = jsonObject.toJavaObject(Route.class);
                list.add(Route);
            }
            list.stream().forEach(e->routeService.insert(e));
        }catch (Exception e) {
            e.printStackTrace();
            Map map = MapUtil.toMap(0, "添加失败", false);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(200, "添加成功", true);
        JsonUtil.toJSON(map);
    }
}


