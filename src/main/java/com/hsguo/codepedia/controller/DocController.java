package com.hsguo.codepedia.controller;

import com.hsguo.codepedia.req.DocSaveReq;
import com.hsguo.codepedia.resp.CommonResp;
import com.hsguo.codepedia.resp.DocQueryResp;
import com.hsguo.codepedia.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/*
RestController 返回一个字符串
Controller 返回一个页面
 */
@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private DocService docService;

    @GetMapping("/find-content/{id}")
    public CommonResp findContent(@PathVariable Long id) {
        CommonResp<String> resp = new CommonResp<>();
        String content = docService.findContent(id);
        resp.setContent(content);
        return resp;
    }

    @GetMapping("/all")
    public CommonResp all() {
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.all();
        resp.setContent(list);
        return resp;
    }

    // 如果是json方式的提交，就需要添加@RequestBody
    // 如果是form表单提交，就不需要添加这个注解
    // 更新类的一般用post
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req) {
        CommonResp resp = new CommonResp<>();
        docService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{idsStr}")
    public CommonResp delete(@PathVariable String idsStr) {
        CommonResp resp = new CommonResp<>();
        List<String> list = Arrays.asList(idsStr.split(","));
        docService.delete(list);
        return resp;
    }
}
