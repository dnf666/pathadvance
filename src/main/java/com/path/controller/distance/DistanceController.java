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
    public void addDistance(@RequestParam Map<String,String> postData){
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
            list.stream().forEach(e->distanceService.updateByPrimaryKeySelective(e));
        }catch (Exception e) {
            e.printStackTrace();
            Map map = MapUtil.toMap(0, "添加失败", false);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(200, "添加成功", true);
        JsonUtil.toJSON(map);
    }


}
