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

    /**
     * 添加计算后的路线
     * @param postData 添加路线
     */
    @RequestMapping("addroute")
    public void addRoute(@RequestParam Map<String, String> postData) {
        int id = 1;
        try {
            List<Route> list = new ArrayList<>();
            Set<String> set1 = postData.keySet();
            Iterator iterator = set1.iterator();
            while (iterator.hasNext()) {
                String string = (String) iterator.next();
                String string1 = postData.get(string);
                JSONObject jsonObject = JSONObject.parseObject(string1);
                Route route = jsonObject.toJavaObject(Route.class);
                String[] routes = route.getRoute().split(",");
                List<String> startAndEnd = new ArrayList<>();
                Collections.addAll(startAndEnd, routes);
                Route route1 = routeService.calculateTimeAndDistance(startAndEnd, id);
                route.setTotalTime(route1.getTotalTime());
                route.setTotalDis(route1.getTotalDis());
                list.add(route);
            }
            list.stream().forEach(e -> routeService.insert(e,"1"));
        } catch (Exception e) {
            e.printStackTrace();
            Map map = MapUtil.toMap(0, "添加失败", false);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(200, "添加成功", true);
        JsonUtil.toJSON(map);
    }

    /**
     * 寻找距离最短或时间最短的四条路线
     * @param postData 传过来的执行要求
     */
    @RequestMapping("findminforfour")
    public void findMinDistanceForFour(@RequestParam String postData) {
        String distance = "distance";
        String time = "time";
        List<Route> list = new ArrayList<>();
        if (distance.equals(postData)) {
            list = routeService.findMinDistanceForFour();
        }
        if (time.equals(postData)) {
            list = routeService.findMinTimeForFour();
        }
        if (list.isEmpty()) {
            Map map = MapUtil.toMap(404, "没有查到", list);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(200, "查询成功", list);
        JsonUtil.toJSON(map);
    }

    /**
     * 查到某方案的所有的路线
     * @param postData 方案id
     */
    @RequestMapping("findroute")
    public void findroute(@RequestParam int postData) {
        List<Route> list;
        list = routeService.findByFid(postData);
        List<List<String>> list3 = new ArrayList<>();
        Iterator iterator = list.iterator();
        if (list.isEmpty()) {
            Map map = MapUtil.toMap(404, "没有查到", list);
            JsonUtil.toJSON(map);
            return;
        }
        try {
            while (iterator.hasNext()) {
                Route route = (Route) iterator.next();
                String s1[] = route.getRoute().split(",");
                List<String> lists = new ArrayList<>();
                Collections.addAll(lists, s1);
                list3.add(lists);
            }

        } catch (Exception e) {
            Map map = MapUtil.toMap(500, "转换失败", list3);
            JsonUtil.toJSON(map);
            return;
        }
        Map map = MapUtil.toMap(200, "查询成功", list3);
        JsonUtil.toJSON(map);
    }

    /**
     * 执行遗传算法
     * @param questionId 问题的id ps： 巨烦
     * */
    @RequestMapping("geneticAlgorithm")
    public void geneticAlgorithm(@RequestParam("postData") String questionId){
        int result = routeService.geneticAlgorithm(questionId);
        if(result > 0) {
            Map map = MapUtil.toMap(200, "计算完成", result);
            JsonUtil.toJSON(map);
        }else{
            Map map = MapUtil.toMap(400, "计算失败", result);
            JsonUtil.toJSON(map);
        }
    }

}


