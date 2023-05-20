package com.hsguo.codepedia.service;

import com.hsguo.codepedia.domain.Test;
import com.hsguo.codepedia.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    // 也可以用Autowired注解
    // Resource是JDK自带的
    // Autowired是来自Spring
    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }
}
