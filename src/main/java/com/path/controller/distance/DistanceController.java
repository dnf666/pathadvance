package com.path.controller.distance;

import com.path.model.Distance;
import com.path.service.distance.DistanceService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
            System.out.println(postData);
        }catch (Exception e) {
            e.printStackTrace();
            Map map = MapUtil.toMap(0, "添加失败", false);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(200, "添加成功", true);
        JsonUtil.toJSON(map);
    }
}
