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
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
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
                String routes[] = route.getRoute().split(",");
                List<String> startAndEnd = new ArrayList<String>();
                Collections.addAll(startAndEnd,routes);
                Route route1 = routeService.calculateTimeAndDistance(startAndEnd,id);
                route.setTotalTime(route1.getTotalTime());
                route.setTotalDis(route1.getTotalDis());
                list.add(route);
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

    @RequestMapping("findminforfour")
    public void findMinDistanceForFour(@RequestParam String postData){
  List<Route> list = new ArrayList<>();
        System.out.println(postData);
        if (postData.equals("distance")){
          list = routeService.findMinDistanceForFour();
        }
        if (postData.equals("time")){
             list = routeService.findMinTimeForFour();
        }
        if (list.isEmpty()){
            Map map = MapUtil.toMap(404, "没有查到", list);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(200, "查询成功", list);
        JsonUtil.toJSON(map);
    }

    @RequestMapping("findroute")
    public void findroute(@RequestParam int postData){
                List<Route> list = new ArrayList<>();
                list = routeService.findByFid(postData);
        List<List<String>> list3 = new ArrayList<>();
        Iterator iterator = list.iterator();
        if (list.isEmpty()){
            Map map = MapUtil.toMap(404, "没有查到", list);
            JsonUtil.toJSON(map);
            return;
        }
        try {
            while(iterator.hasNext()){
                Route route =(Route)iterator.next();
               String s1[] = route.getRoute().split(",");
                List<String> lists = new ArrayList<>();
                Collections.addAll(lists,s1);
                list3.add(lists);
            }
//            list.stream().forEach(e -> {
//                String s[] = e.getRoute().split(",");
//                Collections.addAll(lists,s);
//                list3.add(lists);
//
//            });
        }catch (Exception e){
            Map map = MapUtil.toMap(500, "转换失败", list3);
            JsonUtil.toJSON(map);
            return;
        }
        Map map = MapUtil.toMap(200, "查询成功", list3);
        JsonUtil.toJSON(map);
    }


}


