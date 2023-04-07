package com.hsguo.codepedia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
RestController 返回一个字符串
Controller 返回一个页面
 */
@RestController
public class TestController {
    /**
     * GET, POST, PUT, DELETE
     * RequestMapping表示它支持任何一种请求
     * 还有GetMapping，PostMapping等
     * 使用错误的方法会返回错误405，Method Not Allowed
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
       return "Hello World!";
    }
}
