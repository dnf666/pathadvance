package com.pms.controller.Demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rhan on 2017/7/20.
 */
@Controller
public class DemoConroller {

    @RequestMapping("demo.do")
    public void demo(){
        System.out.println("hello world");
    }

}
