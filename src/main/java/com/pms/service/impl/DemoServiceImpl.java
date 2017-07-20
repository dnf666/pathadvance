package com.pms.service.impl;

import com.pms.service.DemoService;
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
