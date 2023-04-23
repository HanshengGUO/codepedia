package com.hsguo.codepedia.controller;

import com.hsguo.codepedia.domain.Demo;
import com.hsguo.codepedia.resp.CommonResp;
import com.hsguo.codepedia.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
RestController 返回一个字符串
Controller 返回一个页面
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private DemoService demoService;

    @GetMapping("/list")
    public CommonResp list(){
        CommonResp<List<Demo>> resp = new CommonResp<>();
        List<Demo> list = demoService.list();
        resp.setContent(list);
        return resp;
    }
}
