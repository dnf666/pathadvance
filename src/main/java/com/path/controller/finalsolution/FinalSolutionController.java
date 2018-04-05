package com.path.controller.finalsolution;

import com.alibaba.fastjson.JSONObject;
import com.path.model.FinalSolution;
import com.path.service.finalsolution.FinalSolutionService;
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
@RequestMapping("finalsolution")
public class
FinalSolutionController {
    @Resource
    private FinalSolutionService finalSolutionService;

    @RequestMapping("addfinalsolution")
    public void addFinalSolution(@RequestParam Map<String, String> postData) {
        try {
            List<FinalSolution> list = new ArrayList<>();
            Set<String> set1 = postData.keySet();
            Iterator iterator = set1.iterator();
            while (iterator.hasNext()) {
                String string = (String) iterator.next();
                String string1 = postData.get(string);
                JSONObject jsonObject = JSONObject.parseObject(string1);
                FinalSolution finalSolution = jsonObject.toJavaObject(FinalSolution.class);
                finalSolution.setQId(1);
                list.add(finalSolution);
            }
            list.stream().forEach(e -> finalSolutionService.insert(e));
        } catch (Exception e) {
            e.printStackTrace();
            Map map = MapUtil.toMap(0, "添加失败", false);
            JsonUtil.toJSON(map);
        }
        Map map = MapUtil.toMap(200, "添加成功", true);
        JsonUtil.toJSON(map);
    }
}

