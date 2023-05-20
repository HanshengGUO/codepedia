package com.hsguo.codepedia.controller;

import com.hsguo.codepedia.req.EbookQueryReq;
import com.hsguo.codepedia.req.EbookSaveReq;
import com.hsguo.codepedia.resp.CommonResp;
import com.hsguo.codepedia.resp.EbookQueryResp;
import com.hsguo.codepedia.resp.PageResp;
import com.hsguo.codepedia.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/*
RestController 返回一个字符串
Controller 返回一个页面
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    // 如果是json方式的提交，就需要添加@RequestBody
    // 如果是form表单提交，就不需要添加这个注解
    // 更新类的一般用post
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
