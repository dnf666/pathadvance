package com.path.controller.distance;

import com.alibaba.fastjson.JSONObject;
import com.path.model.CenterNode;
import com.path.model.Distance;
import com.path.service.distance.DistanceService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author demo
 */
@Controller
@RequestMapping("distance")
public class DistanceController {
    @Resource
    private DistanceService distanceService;

    @RequestMapping("addDistance")
    public void addDistance(@RequestParam Map<String, String> postData) {
        try {
            List<Distance> list = new ArrayList<>();
            Set<String> set1 = postData.keySet();
            Iterator iterator = set1.iterator();
            while (iterator.hasNext()) {
                String string = (String) iterator.next();
                String string1 = postData.get(string);
                JSONObject jsonObject = JSONObject.parseObject(string1);
                Distance distance = jsonObject.toJavaObject(Distance.class);
                distance.setDId(1);
                list.add(distance);
            }

            list.stream().forEach(e -> distanceService.updateByPrimaryKeySelective(e));
        } catch (Exception e) {
            Map map = MapUtil.toMap(0, "添加失败", false);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(200, "添加成功", true);
        JsonUtil.toJSON(map);
    }

    /**
     * 生成所有路径
     * @param request
     */
    @RequestMapping("produceallway")
    public void produceallway(HttpServletRequest request) {
        //Integer questionId = (Integer) request.getSession().getAttribute("questionId");
        Integer questionId = 1;
        List<Distance> list = distanceService.produceAllWay(questionId);
        try {
            list.parallelStream().forEach(e -> distanceService.insertSelective(e));
        } catch (Exception e) {
            Map map = MapUtil.toMap(0, "添加失败", false);
            JsonUtil.toJSON(map);
            return;
        }
        if (!list.isEmpty()) {
            Map map = MapUtil.toMap(200, "添加成功" + list.size() + "条数据", true);
            JsonUtil.toJSON(map);
        }else{
            Map map = MapUtil.toMap(404, "数据库没有数据", false);
            JsonUtil.toJSON(map);
        }

    }

    /**
     * 更新时间和距离
     * @param postData 时间和距离
     *   返回的剩余需要更新的条数
     */
    @RequestMapping("updatetimeanddis")
    public void updatetimeanddis(@RequestParam String postData){
            JSONObject jsonObject = JSONObject.parseObject(postData);
            Distance distance = jsonObject.toJavaObject(Distance.class);
        int result = 0;
        int remainCount = 0;
            distance.setDId(1);
            try {
                result = distanceService.updateByPrimaryKeySelective(distance);
                remainCount =  distanceService.checkRemainCount(1);
            }catch (Exception e){
                Map map = MapUtil.toMap(400, "更新失败"+e.getMessage(), result);
                JsonUtil.toJSON(map);
                return;
            }
        Map map = MapUtil.toMap(200, "更新成功", remainCount);
        JsonUtil.toJSON(map);

    }
}
