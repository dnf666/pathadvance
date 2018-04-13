package com.path.controller.questionbasic;

import com.path.model.QuestionBasic;
import com.path.service.questionbasic.QuestionBasicService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author demo
 */
@Controller
@RequestMapping("/questionBasic")
public class QuestionBasicController {
    @Resource
    private QuestionBasicService questionBasicService;

    @RequestMapping("/addQuestion")
    public void addQuestion(QuestionBasic questionBasic) {

        int i = questionBasicService.insert(questionBasic);
        if (i == 0) {
            Map<String, Object> map = MapUtil.toMap(0, "添加失败", null);
            JsonUtil.toJSON(map);

        } else {
            Map<String, Object> map = MapUtil.toMap(0, "添加失败", null);
            JsonUtil.toJSON(map);
        }
    }
}
