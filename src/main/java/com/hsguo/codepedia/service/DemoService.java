package com.hsguo.codepedia.service;

import com.hsguo.codepedia.domain.Demo;
import com.hsguo.codepedia.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {
    // 也可以用Autowired注解
    // Resource是JDK自带的
    // Autowired是来自Spring
    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list(){
        return demoMapper.selectByExample(null);
        // null 相当于 new DemoExample()
    }
}
