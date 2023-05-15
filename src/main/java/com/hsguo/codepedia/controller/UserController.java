package com.hsguo.codepedia.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsguo.codepedia.req.UserLoginReq;
import com.hsguo.codepedia.req.UserQueryReq;
import com.hsguo.codepedia.req.UserResetPasswordReq;
import com.hsguo.codepedia.req.UserSaveReq;
import com.hsguo.codepedia.resp.CommonResp;
import com.hsguo.codepedia.resp.PageResp;
import com.hsguo.codepedia.resp.UserLoginResp;
import com.hsguo.codepedia.resp.UserQueryResp;
import com.hsguo.codepedia.service.UserService;
import com.hsguo.codepedia.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;


/*
RestController 返回一个字符串
Controller 返回一个页面
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SnowFlake snowFlake;

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
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @PostMapping("/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.resetPassword(req);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

        // 生成单点登录token，并且放到redis中
        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token:{}，并且放到redis中", token);
        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);
        resp.setContent(userLoginResp);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }

    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable String token) {
        CommonResp resp = new CommonResp<>();
        redisTemplate.delete(token);
        LOG.info("Delete token from Redis: {}", token);
        return resp;
    }
}
