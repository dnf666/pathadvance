package com.path.controller.centernode;

import com.path.model.CenterNode;
import com.path.service.centernode.CenterNodeService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author demo
 */
@Controller
@RequestMapping("centernode")
public class CenterNodeController {
    @Resource
    private CenterNodeService centerNodeService;
    @RequestMapping("/importByMap")
    public void importByMap(List<CenterNode> list){
        try {
            list.stream().forEach(e -> centerNodeService.insert(e));
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
    public void getCenterKey() {

    }
@RequestMapping("/addlaandlo")
    public void addExtraMessage(List<CenterNode> centerNodeList){
        boolean result = centerNodeService.updateAdvance(centerNodeList);
        if(result) {
           Map map = MapUtil.toMap(1, "添加成功", result);
           JsonUtil.toJSON(map);
        }else{
            Map map = MapUtil.toMap(0, "添加失败", result);
            JsonUtil.toJSON(map);

        }
   }
}
