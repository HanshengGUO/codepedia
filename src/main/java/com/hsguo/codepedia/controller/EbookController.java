package com.hsguo.codepedia.controller;

import com.hsguo.codepedia.req.EbookReq;
import com.hsguo.codepedia.resp.CommonResp;
import com.hsguo.codepedia.resp.EbookResp;
import com.hsguo.codepedia.resp.PageResp;
import com.hsguo.codepedia.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public CommonResp list(EbookReq req) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
