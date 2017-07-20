package com.pms.service.Demo.impl;

import com.pms.service.Demo.DemoService;
import org.springframework.stereotype.Service;

/**
 * Created by rhan on 2017/7/20.
 */
@Service
public class DemoServiceImpl implements DemoService {
    public void demoServiceTest() {
        System.out.println("hello world");
    }
}
