package com.hsguo.codepedia.controller;

import com.hsguo.codepedia.req.UserQueryReq;
import com.hsguo.codepedia.req.UserSaveReq;
import com.hsguo.codepedia.resp.CommonResp;
import com.hsguo.codepedia.resp.PageResp;
import com.hsguo.codepedia.resp.UserQueryResp;
import com.hsguo.codepedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/*
RestController 返回一个字符串
Controller 返回一个页面
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    public CommonResp list(@Valid UserQueryReq req) {
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    // 如果是json方式的提交，就需要添加@RequestBody
    // 如果是form表单提交，就不需要添加这个注解
    // 更新类的一般用post
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req) {
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }
}
